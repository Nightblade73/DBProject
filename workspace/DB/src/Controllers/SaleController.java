package Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Tables.InterfaceTable;
import Tables.Sale;

public class SaleController extends AbstractController {

	@Override
	public ResultSet selectTable(Statement s) throws SQLException {
		return s.executeQuery("SELECT S.sale_num, P.model, B.surname, S.count, S.sale_date, S.price "
				+ "FROM sale S JOIN buyer B ON S.buyer_num = B.buyer_num "
				+ "JOIN product P ON S.product_num= P.product_num order by S.sale_num;");
	}

	@Override
	public void insertRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("INSERT INTO sale values(nextval('seq_sale')," + list.get(0) + "," + list.get(1) + ","
				+ list.get(2) + ",'" + list.get(3) + "'," + list.get(4) + ")");
	}

	@Override
	public void updateRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("Update sale set product_num = " + list.get(0) + " , buyer_num = " + list.get(1) + " , count = "
				+ list.get(2) + " , sale_date = '" + list.get(3) + "', price = " + list.get(4) + " WHERE sale_num = "
				+ list.get(5) + ";");
	}

	@Override
	public void deleteRow(Statement s, int num) throws SQLException {
		s.executeUpdate("DELETE FROM sale WHERE sale_num = " + num + ";");
	}

	@Override
	public ArrayList<InterfaceTable> loadTable(Statement s) throws SQLException {
		ArrayList<InterfaceTable> list = new ArrayList<InterfaceTable>();
		ResultSet rs = selectTable(s);
		list.clear();
		while (rs.next()) {
			Sale sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5),
					rs.getDouble(6));
			list.add(sale);
		}
		return list;
	}
}
