package EJB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class IConnectDBD {

	public static List<Map<String, Object>> GetDataReturnResultSet(String query) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			ResultSet rs = null;
			rs = ConnectDBD.GetDataReturnResultSet(query);
			while (rs.next()) {
				int total_rows = rs.getMetaData().getColumnCount();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < total_rows; i++) {
					String value = "";
					if (rs.getObject(i + 1) == null) {
						value = "";
					} else {
						value = rs.getObject(i + 1).toString();
					}
					row.put(rs.getMetaData().getColumnLabel(i + 1), value);
				}
				rows.add(row);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}

	public static Map<String, String> ExecUpdate(String query) {
		Map<String, String> result = new HashMap<String, String>();
		try {

			int _rs = ConnectDBD.ExecUpdate(query);

			// obj.put("result",_rs);
			result.put("result", String.valueOf(_rs));

		} catch (Exception ex) {
		}
		return result;
	}
	public static List<Map<String, Object>>  ExecBoFunctionReturnResutlSet(String spname, String[] p_field,
			Object[] p_values){
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			ResultSet rs = null;
			rs = ConnectDBD.ExecBoFunctionReturnResutlSet(spname,p_field,p_values);
			while (rs.next()) {
				int total_rows = rs.getMetaData().getColumnCount();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < total_rows; i++) {
					
					row.put(rs.getMetaData().getColumnLabel(i + 1), rs.getObject(i + 1));
				}
				rows.add(row);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}
	public static List<Map<String, Object>>  ExecBoFunctionReturnResutlSet(String spname) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try {
			ResultSet rs = null;
			rs = ConnectDBD.ExecBoFunctionReturnResutlSet(spname);
			while (rs.next()) {
				int total_rows = rs.getMetaData().getColumnCount();
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 0; i < total_rows; i++) {
					
					row.put(rs.getMetaData().getColumnLabel(i + 1), rs.getObject(i + 1));
				}
				rows.add(row);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
	}
	public static Map<String,String> ExecBoFunction(String spname, String[] p_field,
			Object[] p_values) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			
			int _rs = -1;
			_rs = ConnectDBD.ExecBoFunction(spname, p_field,
					p_values);
			result.put("result", String.valueOf(_rs));
		} catch (Exception ex) {

		}
		return result;
	}
	public static Map<String, Object> ExecBoFunctionReturnList(String spname, String[] p_field,
			String[] p_type, Object[] p_values, int[] p_direction) {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		try {
			mapOfObjects = ConnectDBD.ExecBoFunctionReturnList(spname, p_field, p_type, p_values, p_direction);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapOfObjects;
	}
}
