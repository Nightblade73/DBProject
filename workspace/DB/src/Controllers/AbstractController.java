package Controllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public abstract class AbstractController implements InterfaceController {

	public Vector<String> getColumnName(Statement s) throws SQLException {
		Vector<String> columnNames = new Vector<String>();
		ResultSetMetaData md = selectTable(s).getMetaData();
		int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			columnNames.addElement(md.getColumnName(i));
		}
		return columnNames;
	}

	public Vector<Vector<String>> getRowData(Statement s) throws SQLException {
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		ResultSet rs = selectTable(s);
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		while (rs.next()) {
			Vector<String> row = new Vector<String>(columns);
			for (int i = 1; i <= columns; i++) {
				row.addElement(String.valueOf(rs.getObject(i)));
			}
			data.addElement(row);
		}
		rs.close();
		return data;
	}
}
