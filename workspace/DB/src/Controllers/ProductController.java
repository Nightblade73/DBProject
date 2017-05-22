package Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Tables.InterfaceTable;
import Tables.Product;

public class ProductController extends AbstractController {

	@Override
	public ResultSet selectTable(Statement s) throws SQLException {
		return s.executeQuery("SELECT Pr.product_num, P.producer_name, Pa.party_date, Pr.model, Pr.price_for_one "
				+ "FROM product Pr JOIN producer P ON Pr.producer_num = P.producer_num JOIN party Pa "
				+ "ON Pr.party_num = Pa.party_num order by Pr.product_num");
	}

	@Override
	public void insertRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("INSERT INTO product values( nextval('seq_producer')," + list.get(0) + "," + list.get(1) + ",'"
				+ list.get(2) + "'," + list.get(3) + ")");
	}

	@Override
	public void updateRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("Update product set producer_num = " + list.get(0) + " , party_num = " + list.get(1)
				+ " , model = '" + list.get(2) + "' , price_for_one = " + list.get(3) + " WHERE product_num = "
				+ list.get(4) + ";");
	}

	@Override
	public void deleteRow(Statement s, int num) throws SQLException {
		s.executeUpdate("DELETE FROM product WHERE product_num = " + num + ";");
	}

	@Override
	public ArrayList<InterfaceTable> loadTable(Statement s) throws SQLException {
		ArrayList<InterfaceTable> list = new ArrayList<InterfaceTable>();
		ResultSet rs = selectTable(s);
		list.clear();
		while (rs.next()) {
			Product product = new Product(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
					rs.getDouble(5));
			list.add(product);
		}
		return list;
	}
}
