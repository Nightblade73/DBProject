package Controllers;

import java.util.ArrayList;

import Tables.Buyer;
import Tables.InterfaceTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyerController extends AbstractController {

	@Override
	public ResultSet selectTable(Statement s) throws SQLException {
		return s.executeQuery("Select * from buyer order by buyer_num");
	}

	@Override
	public void insertRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("INSERT INTO buyer values(" + list.get(0) + ",'" + list.get(1) + "','" + list.get(2) + "','"
				+ list.get(3) + "','" + list.get(4) + "')");
	}

	@Override
	public void updateRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate(
				"Update buyer set surname = '" + list.get(0) + "' , name = '" + list.get(1) + "' , patronymic = '"
						+ list.get(2) + "' , telephone = '" + list.get(3) + "' WHERE buyer_num = " + list.get(4) + ";");
	}

	@Override
	public void deleteRow(Statement s, int num) throws SQLException {
		s.executeUpdate("DELETE FROM buyer WHERE buyer_num = " + num + ";");
	}

	@Override
	public ArrayList<InterfaceTable> loadTable(Statement s) throws SQLException {		
		ArrayList<InterfaceTable> list = new ArrayList<InterfaceTable>();
		ResultSet rs = selectTable(s);
		list.clear();
		while (rs.next()) {
			Buyer buyer = new Buyer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			list.add(buyer);
		}
		return list;
	}
}
