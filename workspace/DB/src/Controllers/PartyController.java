package Controllers;

import java.util.ArrayList;
import Tables.InterfaceTable;
import Tables.Party;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartyController extends AbstractController {

	@Override
	public ResultSet selectTable(Statement s) throws SQLException {
		return s.executeQuery("Select * from party order by party_num");
	}

	@Override
	public void insertRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("INSERT INTO party values( nextval('seq_party'),'" + list.get(0) + "','" + list.get(1) + "')");
	}

	@Override
	public void updateRow(Statement s, ArrayList<String> list) throws SQLException {
		s.executeUpdate("Update party set party_date = '" + list.get(0) + "' , provider_name = '" + list.get(1)
				+ "' WHERE party_num = " + list.get(2) + ";");
	}

	@Override
	public void deleteRow(Statement s, int num) throws SQLException {
		s.executeUpdate("DELETE FROM party WHERE party_num = " + num + ";");
	}

	@Override
	public ArrayList<InterfaceTable> loadTable(Statement s) throws SQLException {
		ArrayList<InterfaceTable> list = new ArrayList<InterfaceTable>();
		ResultSet rs = selectTable(s);
		list.clear();
		while (rs.next()) {
			Party party = new Party(rs.getInt(1), rs.getDate(2), rs.getString(3));
			list.add(party);
		}
		return list;
	}
}
