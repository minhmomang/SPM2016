package DAL;


import EJB.IConnectEJBS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Model.UserModel;

public class UserDAL {
	public static UserModel getuser(String username) {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		UserModel item = new UserModel();
		try {
			query = "select user_id,user_name,pass,email FROM tb_user where user_name ='"
					+ username + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			 for(int i=0;i<listob.size();i++){
				 Map<String, Object> temp=listob.get(i);
					item.setUser_id(temp.get("user_id").toString());
					item.setUser_name(temp.get("user_name").toString());
					item.setPass(temp.get("pass").toString());
					item.setEmail(temp.get("email").toString());	
					break;
			 }
			
			

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return item;
	}

	public static boolean isAdmin(String _user_id) {
		String query = "select count(*) as count from tb_user where user_id='"
				+ _user_id + "' and role='AD'";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int count = 0;
		try {
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			 Map<String, Object> temp=listob.get(0);
			 count = Integer.parseInt(temp.get("count").toString());
			
		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		if (count > 0) {
			return true;
		}

		return false;

	}

	public static boolean isUsername(String user_name) {
		String query = "select count(*) as count from tb_user where user_name='"
				+ user_name + "'";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int count = 0;
		try {
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			
			 Map<String, Object> temp=listob.get(0);
			 count = Integer.parseInt(temp.get("count").toString());
			
		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		if (count > 0) {
			return true;
		}

		return false;
	}

	public static boolean isPass(String username, String pass) {
		String query = "select pass from tb_user where user_name='" + username
				+ "'";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String select_pass = null;
		try {
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			
			 Map<String, Object> temp=listob.get(0);
			 select_pass = (temp.get("pass").toString());
			
		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		if (pass.equals(select_pass)) {
			return true;
		}

		return false;

	}

	public static UserModel get_user_infopass(String user_id) {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		UserModel item = new UserModel();
		try {
			query = "select user_id,user_name from tb_user where user_id ='"
					+ user_id + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			
			 Map<String, Object> temp=listob.get(0);
				item.setUser_id(temp.get("user_id").toString());
				item.setUser_name(temp.get("user_name").toString());
			
		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return item;
	}

	public static int change_password(UserModel a, String passold) {
		int _result = -1;
		try {
			String spname = "sp_user_change_pass";
			String[] pfield = { "p_user_id", "p_passold", "p_passnew",
					"p_modifier", "f" };
			String[] ptype = { "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR",
					"INT" };
			Object[] pvalues = { a.getUser_id(), passold, a.getPass(),
					a.getModifier(), _result };
			int[] direc = { 0, 0, 0, 0, 1 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			IConnectEJBS con = new IConnectEJBS();
			mapOfObjects = con.ExecBoFunctionReturnList(spname, pfield,
					ptype, pvalues, direc);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public static int insert_user(String type, String creator, String user,
			String pass, String cate_type) {
		int _result = -1;
		try {
			String spname = "sp_tbuser";
			String[] pfield = { "f", "p_type", "p_creator", "p_username",
					"p_pass", "p_cate_type" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR",
					"VARCHAR", "VARCHAR"};
			Object[] pvalues = { "", type, creator, user, pass, cate_type };
			int[] direc = { 1, 0, 0, 0, 0, 0};
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			IConnectEJBS con = new IConnectEJBS();
			mapOfObjects = con.ExecBoFunctionReturnList(spname, pfield,
					ptype, pvalues, direc);
			_result = Integer.parseInt(mapOfObjects.get("f").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			_result = -1;
		}
		return _result;
	}

	public static ArrayList<UserModel> get_list_user() {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ArrayList<UserModel> list = new ArrayList<UserModel>(); 
		try {
			query = "select user_id,user_name,creator,create_date,manager_cate_type FROM tb_user";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			 
			 
			 for(int i=0;i<listob.size();i++){
				 Map<String, Object> temp=listob.get(i);
				 UserModel item = new UserModel();
				 String cate_name="";
					item.setUser_id(temp.get("user_id").toString());
					item.setUser_name(temp.get("user_name").toString());
					item.setCreate_date(temp.get("create_date").toString());
					item.setCreator(temp.get("creator").toString());
					// Transfer ID --> name
					String type = (temp.get("manager_cate_type").toString());
					String[] arr_type = type.split(",");
					for (String arr_id : arr_type) {
						String query_2= "select product_type_name FROM tb_product_type WHERE product_type_id='"+arr_id+"'";
						List<Map<String, Object>> lstid = new ArrayList<Map<String, Object>>();
						lstid=con.GetDataReturnResultSet(query_2);
						cate_name+=(lstid.get(0).get("product_type_name".toString())+",");
						
					}
					//
					cate_name=cate_name.substring(0,cate_name.length()-1);
					item.setCate_type(cate_name);		
					list.add(item);
				
			 }

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
	
		return list;
	}
	public static UserModel get_info_user(String userid) {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		UserModel item = new UserModel();
		try {
			query = "select user_id,user_name,pass,email,manager_cate_type FROM tb_user where user_id ='"
					+ userid + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			 Map<String, Object> temp=listob.get(0);
				item.setUser_id(temp.get("user_id").toString());
				item.setUser_name(temp.get("user_name").toString());
				item.setPass(temp.get("pass").toString());
				item.setCate_type(temp.get("manager_cate_type").toString());
				item.setEmail(temp.get("email").toString());
				
			

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return item;
	}
	public static Map<String, Object> remove_user(String str){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tbuser_delete";
			String[]pfield = {
					"str","f"

			};
			String[]ptype={
					"TEXT","INT"
			};
			Object[]pvalue={
					str,""
								
			};
			int[]pdirec = {
						0,1
					};
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}	
	
	public static int check_exists_type(String type,String username,String cate_type){
		
		int _result = -1;
			try{
				String spname = "sp_check_exist_type";
				String[] pfield = {"f","p_type","p_username","p_cate_type"};
				String[] ptype = {"INT","VARCHAR","VARCHAR","VARCHAR"};
				String[] pvalues={"",type,username,cate_type};
				int[] pdirec = {1,0,0,0};
				
				IConnectEJBS con = new IConnectEJBS();
				Map<String, Object> mapOfObjects = new HashMap<String, Object>();
				mapOfObjects=con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
				
				if(mapOfObjects.size()>0){
					_result= Integer.parseInt(mapOfObjects.get("f").toString());	
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
				_result = -1;
			}
		return _result;
	}
}
