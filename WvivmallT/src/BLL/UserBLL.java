package BLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import DAL.ProductTypeDAL;
import DAL.UserDAL;
import Helper.ErrorMesage;
import Model.UserModel;

public class UserBLL {
	public Map<String, String> isLogin_admin(String username, String pass) {
		Map<String, String> result = new HashMap<String, String>();
		if (!UserDAL.isUsername(username) || !UserDAL.isPass(username, pass)) {
			result.put("f", "-1");
			result.put("mes", "Tài khoản hoặc mật khẩu không đúng");
		} else {
			UserModel us = new UserModel();
			us = UserDAL.getuser(username);
			if (UserDAL.isAdmin(us.getUser_id())) {
				result.put("f", "0");
				result.put("user_id", us.getUser_id());
				result.put("user_name", us.getUser_name());
				result.put("email", us.getEmail());
			} else {
				result.put("f", "-1");
				result.put("mes", "bạn không phải là thành viên quản trị!");
			}
		}
		return result;
	}

	public Map<String, String> islogin_user(String username, String pass) {
		Map<String, String> result = new HashMap<String, String>();
		if (!UserDAL.isUsername(username) || !UserDAL.isPass(username, pass)) {
			result.put("f", "-1");
			result.put("mes", "Tài khoản hoặc mật khẩu không đúng");
		} else {
			UserModel us = new UserModel();
			us = UserDAL.getuser(username);
			result.put("f", "0");
			result.put("user_id", us.getUser_id());
			result.put("user_name", us.getUser_name());
		}
		return result;
	}

	public String get_user_infopass(String user_id) {
		UserModel user_info = new UserModel();
		user_info = UserDAL.get_user_infopass(user_id);
		String json = new Gson().toJson(user_info);
		return json;
	}

	public String change_password(String Json_Data_client, String passold) {
		UserModel a = new UserModel();
		a = new Gson().fromJson(Json_Data_client, UserModel.class);
		int result = UserDAL.change_password(a, passold);
		Map<String, String> m1 = new HashMap<String, String>();
		if (result == 0) {
			m1.put("_error", "success");

		} else {
			m1.put("_error", "fail");
			m1.put("mes", ErrorMesage.getMesageError(result));
		}
		String json = new Gson().toJson(m1);
		return json;
	}

	public static String insert_user(String type, String creator, String user,
			String pass, String cate_type) {
		Map<String, String> m1 = new HashMap<String, String>();
		
		
		String[] arr =cate_type.split(",");
		for (int i = 0; i < arr.length; i++) {
			int f = UserDAL.check_exists_type(type,user,arr[i]);
			if (f == 2) {
				m1.put("_error", String.valueOf(f));
				m1.put("mes", arr[i]);
				String json = new Gson().toJson(m1);
				return json;
			}
		}
		
		int result = UserDAL.insert_user(type, creator, user, pass, cate_type);
		if (result == 0) {
			m1.put("_error", String.valueOf(result));
		} else {
			m1.put("_error", String.valueOf(result));
			m1.put("mes", ErrorMesage.getMesageError(result));
		}
		String json = new Gson().toJson(m1);
		return json;
	}
	

	public static String get_list_user() {
		ArrayList<UserModel> list = new ArrayList<UserModel>();
		list = UserDAL.get_list_user();
		String _json = new Gson().toJson(list);
		return _json;
	}

	public static String get_info_user(String userid) {
		UserModel item = new UserModel();
		item = UserDAL.get_info_user(userid);
		String _json = new Gson().toJson(item);
		return _json;
	}

	public static String remove_user(String str) {
		 Map<String, Object> result = new HashMap<String, Object>();	
		Map<String, String> obj = new HashMap<String, String>();
		result = UserDAL.remove_user(str);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		return json;
	}
}
