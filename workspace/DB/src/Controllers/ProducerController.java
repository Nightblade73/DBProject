package Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Tables.InterfaceTable;
import Tables.Producer;

public class ProducerController extends AbstractController {

	@Override
	public ResultSet selectTable(Statement s) throws SQLException {
		return s.executeQuery("select * from producer");
	}

	@Override
	public void insertRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("INSERT INTO producer values(" + list.get(0) + ",'" + list.get(1) + "','" + list.get(2) + "','"
				+ list.get(3) + "','" + list.get(4) + "')");
	}

	@Override
	public void updateRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("Update prosucer set producer_name = '" + list.get(0) + "' , e-mail = '" + list.get(1)
				+ "' , telephone = '" + list.get(2) + "' , address = '" + list.get(3) + "' WHERE producer_num = "
				+ list.get(4) + ";");
	}

	@Override
	public void deleteRow(Statement s, int num) throws SQLException {
		s.executeUpdate("DELETE FROM producer WHERE producer_num = " + num + ";");
	}

	@Override
	public ArrayList<InterfaceTable> loadTable(Statement s) throws SQLException {
		ArrayList<InterfaceTable> list = new ArrayList<InterfaceTable>();
		ResultSet rs = selectTable(s);
		list.clear();
		while (rs.next()) {
			Producer producer = new Producer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));
			list.add(producer);
		}
		return list;
	}
}
