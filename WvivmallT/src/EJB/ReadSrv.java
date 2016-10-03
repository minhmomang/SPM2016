package EJB;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import Service.ModelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadSrv {
	
	private static URI getBaseURI() {
		////System.out.println("SERVICE");
		return UriBuilder.fromUri(ExtraJBOSS.urlservicedb).build();
	}

	private static String ExecGetDataReturnResultSet(String query) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());

		String data = target.path("GetDataReturnResultSet").queryParam("query", query).request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		return data;
	}

	public static List<Map<String, Object>> GetDataReturnResultSet(String query) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try{
			String jsonArrayString = ExecGetDataReturnResultSet(query);		
			////System.out.println(jsonArrayString);
			JSONObject objm = new JSONObject(jsonArrayString);		
			JSONArray jsonarr_data = new JSONArray(objm.get("data").toString());
			JSONArray jsonarr_key = new JSONArray(objm.get("key").toString());
			
			if (jsonarr_data != null) {
				int len = jsonarr_data.length();
				for(int i=0;i<len;i++){
					JSONObject objs = new JSONObject(jsonarr_data.get(i).toString());
					JSONObject objr = new JSONObject(objs.get("map").toString());
					Map<String, Object> row = new HashMap<String, Object>();
					for(int j=0;j<jsonarr_key.length();j++){
						String key = jsonarr_key.get(j).toString();
						row.put(key, objr.get(key));				
					}
					rows.add(row);			
				}			
			}
		}catch(JSONException e){
			
		}		
		return rows;
	}
	private static String ExecExecUpdate(String query) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());

		String data = target.path("ExecUpdate")
				.queryParam("query", query).request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);

		return data;
	}
	public static Map<String,String> ExecUpdate(String query){
		Map<String, String> result = new HashMap<String, String>();
		try{
			String jsonstr = ExecExecUpdate(query);		
			JSONObject objm = new JSONObject(jsonstr);	
			String _rs = objm.getString("result").toString();
			result.put("result", String.valueOf(_rs));
			
		}catch(Exception ex){
			
		}
		return result;
	}
	private static String  Exec_ExecBoFunctionReturnResutlSet(String spname, String[] p_field,
			Object[] p_values){
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());

		String strpf = ExtraJBOSS.ConvertArrayToJson(p_field);
		String strpv = ExtraJBOSS.ConvertArrayObjToJson(p_values);
		String data = target.path("ExecBoFunctionReturnResutlSet")
				.queryParam("spname", spname)
				.queryParam("p_field", strpf).
				queryParam("p_values", strpv).request().accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		return data;
	}
	public static List<Map<String, Object>>  ExecBoFunctionReturnResutlSet(String spname, String[] p_field,
			Object[] p_values){
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try{
			String jsonArrayString = Exec_ExecBoFunctionReturnResutlSet(spname,p_field,p_values);		
			JSONObject objm = new JSONObject(jsonArrayString);		
			JSONArray jsonarr_data = new JSONArray(objm.get("data").toString());
			JSONArray jsonarr_key = new JSONArray(objm.get("key").toString());
			
			if (jsonarr_data != null) {
				int len = jsonarr_data.length();
				for(int i=0;i<len;i++){
					JSONObject objs = new JSONObject(jsonarr_data.get(i).toString());
					JSONObject objr = new JSONObject(objs.get("map").toString());
					Map<String, Object> row = new HashMap<String, Object>();
					for(int j=0;j<jsonarr_key.length();j++){
						String key = jsonarr_key.get(j).toString();
						row.put(key, objr.get(key));				
					}
					rows.add(row);			
				}			
			}
		}catch(JSONException e){
			
		}		
		return rows;
	}

	private static String  ExecBoFunctionReturnResutlSetNonParam(String spname) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		String data = target.path("ExecBoFunctionReturnResutlSetNonParam")
				.queryParam("spname", spname)
				.request().accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		return data;
	}
	public static List<Map<String, Object>>  ExecBoFunctionReturnResutlSet(String spname) {
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		try{
			String jsonArrayString = ExecBoFunctionReturnResutlSetNonParam(spname);		
			JSONObject objm = new JSONObject(jsonArrayString);		
			JSONArray jsonarr_data = new JSONArray(objm.get("data").toString());
			JSONArray jsonarr_key = new JSONArray(objm.get("key").toString());
			
			if (jsonarr_data != null) {
				int len = jsonarr_data.length();
				for(int i=0;i<len;i++){
					JSONObject objs = new JSONObject(jsonarr_data.get(i).toString());
					JSONObject objr = new JSONObject(objs.get("map").toString());
					Map<String, Object> row = new HashMap<String, Object>();
					for(int j=0;j<jsonarr_key.length();j++){
						String key = jsonarr_key.get(j).toString();
						row.put(key, objr.get(key));				
					}
					rows.add(row);			
				}			
			}
		}catch(JSONException e){
			
		}		
		return rows;
	}
	private static String Exec_ExecBoFunction(String spname, String[] p_field,
			Object[] p_values) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());

		String strpf = ExtraJBOSS.ConvertArrayToJson(p_field);
		String strpv = ExtraJBOSS.ConvertArrayObjToJson(p_values);
		String data = target.path("ExecBoFunction")
				.queryParam("spname", spname)
				.queryParam("p_field", strpf).
				queryParam("p_values", strpv).request().accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		return data;
	}
	public static Map<String,String> ExecBoFunction(String spname, String[] p_field,
			Object[] p_values) {
		Map<String, String> result = new HashMap<String, String>();
		try{
			String jsonstr = Exec_ExecBoFunction(spname,p_field,p_values);		
			JSONObject objm = new JSONObject(jsonstr);	
			String _rs = objm.getString("result").toString();
			result.put("result", String.valueOf(_rs));
			
		}catch(Exception ex){
			
		}
		return result;
	}
	public static String Exec_ExecBoFunctionReturnList(String spname, String[] p_field,
			String[] p_type, Object[] p_values, int[] p_direction) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		String strpf = ExtraJBOSS.ConvertArrayToJson(p_field);
		String strpt = ExtraJBOSS.ConvertArrayToJson(p_type);
		String strpv = ExtraJBOSS.ConvertArrayObjToJson(p_values);
		String strpd = ExtraJBOSS.ConvertArrayObjToInt(p_direction);
		////System.out.println(strpd);
		String data = target.path("ExecBoFunctionReturnList")
					.queryParam("spname",spname)
					.queryParam("pfield", strpf)
					.queryParam("ptype", strpt)
					.queryParam("pvalues", strpv)
					.queryParam("pdirection", strpd)
					.request().accept(MediaType.APPLICATION_JSON)
					.get(String.class);

		return data;
	}
	
	public static Map<String, Object> ExecBoFunctionReturnList(String spname, String[] p_field,
			String[] p_type, Object[] p_values, int[] p_direction) {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		try {
			String jsonArrayString = Exec_ExecBoFunctionReturnList(spname,p_field,p_type,p_values,p_direction);		
			JSONObject objm = new JSONObject(jsonArrayString);		
			JSONObject jsonarr_data = new JSONObject(objm.get("data").toString());
			JSONArray jsonarr_key = new JSONArray(objm.get("key").toString());
			
			if (jsonarr_key != null) {
				int len = jsonarr_key.length();
				for(int i=0;i<len;i++){
					String key = jsonarr_key.get(i).toString();
					mapOfObjects.put(key, jsonarr_data.get(key));
								
				}		
				
			}
		
		} catch (Exception ex) {

		}
		return mapOfObjects;
	}
}
