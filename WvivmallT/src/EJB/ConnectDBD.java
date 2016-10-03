/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDBD {
	static String url = "jdbc:mysql://10.0.10.41:3306/";
	static String dbName = "dbvivmallt";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "vinhsang";
	static String password = "1234";
	static Connection conn = null;
	static CallableStatement callstore = null;

	public ConnectDBD() {
	}

	public static void LoadDriver() throws SQLException, ClassNotFoundException, InstantiationException {
		try {
			////System.out.println("DIRECT");
			Class.forName(driver).newInstance();
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ConnectDBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		String jdbc = url + dbName + "?user=" + userName + "&password=" + password + "";
		String jdbcutf8 = jdbc + "&useUnicode=true&characterEncoding=UTF-8";
		// conn = DriverManager.getConnection(url+dbName,userName,password);
		conn = DriverManager.getConnection(jdbcutf8);
	}

	public static ResultSet GetDataReturnResultSet(String query)
			throws SQLException, ClassNotFoundException, InstantiationException {
		ResultSet rs = null;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int ExecUpdate(String query) {
		int result = -1;
		try {
			if (conn == null || conn.isClosed()) {
				LoadDriver();
			}
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			result = 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}

	public static ResultSet ExecBoFunctionReturnResutlSet(String spname, String[] p_field, Object[] p_values)
			throws SQLException, ClassNotFoundException, InstantiationException {
		ResultSet rs = null;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return null;
			// set field
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";

			callstore = conn.prepareCall(query);
			// set value
			for (int i = 0; i < p_field.length; i++) {
				callstore.setObject(p_field[i], p_values[i]);
			}
			////System.out.println(query);
			rs = callstore.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rs;
	}

	public static ResultSet ExecBoFunctionReturnResutlSet(String spname)
			throws SQLException, ClassNotFoundException, InstantiationException {
		String query = null;
		ResultSet rs = null;
		query = "{call " + spname + "}";
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			callstore = conn.prepareCall(query);
			rs = callstore.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int ExecBoFunction(String spname, String[] p_field, Object[] p_values)
			throws SQLException, ClassNotFoundException, InstantiationException {
		int _result = 0;
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return -1;
			// set field
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";

			callstore = conn.prepareCall(query);
			// set value
			for (int i = 0; i < p_field.length; i++) {
				callstore.setObject(p_field[i], p_values[i]);
			}
			callstore.executeQuery();// insert,update,delete

		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}

		return _result;
	}

	public static Map<String, Object> ExecBoFunctionReturnList(String spname, String[] p_field, String[] p_type,
			Object[] p_values, int[] p_direction) throws SQLException, ClassNotFoundException, InstantiationException {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		try {
			String query = null;
			query = "{call " + spname;
			if (conn == null || conn.isClosed()) {
				LoadDriver();
			}
			if (p_field.length != p_values.length)
				return null;
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";
			////System.out.println(query);
			callstore = conn.prepareCall(query);
			for (int i = 0; i < p_field.length; i++) {
				if (p_direction[i] == 0) {
					callstore.setObject(p_field[i], p_values[i]);
				} else if (p_direction[i] == 1) {
					switch (p_type[i]) {
					case "BIGINT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BIGINT);
						break;
					case "BINARY":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BINARY);
						break;
					case "CHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.CHAR);
						break;
					case "DATE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DATE);
						break;
					case "DECIMAL":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DECIMAL);
						break;
					case "DOUBLE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DOUBLE);
						break;
					case "FLOAT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.FLOAT);
						break;
					case "INT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.INTEGER);
						break;
					case "VARCHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.VARCHAR);
						break;
					default:
						callstore.registerOutParameter(p_field[i], java.sql.Types.NULL);
						break;
					}
				}
			}
			ResultSet rs = null;
			callstore.executeQuery();
			for (int i = 0; i < p_direction.length; i++) {
				if (p_direction[i] == 1) {
					//
					mapOfObjects.put(p_field[i], callstore.getObject(p_field[i]));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapOfObjects;
	}

	public static Connection getConnection() throws ClassNotFoundException, InstantiationException, SQLException {
		if (conn == null || conn.isClosed()) {
			LoadDriver();
		}
		return conn;
	}

	public static Map<String, Object> ExecBoFunctionReturnList(Connection conn, String spname, String[] p_field,
			String[] p_type, Object[] p_values, int[] p_direction)
			throws SQLException, ClassNotFoundException, InstantiationException {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		try {
			String query = null;
			query = "{call " + spname;
			if (p_field.length != p_values.length)
				return null;
			for (int i = 0; i < p_field.length; i++) {
				if (i == 0) {
					query += "(";
				}
				query += "?";
				if (i + 1 != p_field.length) {
					query += ",";
				}
			}
			query += ")}";
			callstore = conn.prepareCall(query);
			for (int i = 0; i < p_field.length; i++) {
				if (p_direction[i] == 0) {
					callstore.setObject(p_field[i], p_values[i]);
				} else if (p_direction[i] == 1) {
					switch (p_type[i]) {
					case "BIGINT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BIGINT);
						break;
					case "BINARY":
						callstore.registerOutParameter(p_field[i], java.sql.Types.BINARY);
						break;
					case "CHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.CHAR);
						break;
					case "DATE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DATE);
						break;
					case "DECIMAL":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DECIMAL);
						break;
					case "DOUBLE":
						callstore.registerOutParameter(p_field[i], java.sql.Types.DOUBLE);
						break;
					case "FLOAT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.FLOAT);
						break;
					case "INT":
						callstore.registerOutParameter(p_field[i], java.sql.Types.INTEGER);
						break;
					case "VARCHAR":
						callstore.registerOutParameter(p_field[i], java.sql.Types.VARCHAR);
						break;
					default:
						callstore.registerOutParameter(p_field[i], java.sql.Types.NULL);
						break;
					}
				}
			}
			callstore.executeUpdate();
			for (int i = 0; i < p_direction.length; i++) {
				if (p_direction[i] == 1) {
					//
					mapOfObjects.put(p_field[i], callstore.getObject(p_field[i]));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return mapOfObjects;
	}

	public static int ExecUpdate(Connection conn, String query) {
		int result = -1;
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			result = 0;
		} catch (Exception ex) {
			result = -1;
		}
		return result;
	}
}
