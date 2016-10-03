package BLL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import Controller.CommentController;
import Model.Helpermail;
import Model.ItemMember;
import Service.ReadServiceMail;
import DAL.*;
import Helper.ErrorMesage;
import Helper.Extra;
import Helper.JsonHelper;

public class Memberbll {

	public static String register(ItemMember item, HttpServletRequest request) throws JSONException, IOException {
		String str = "";
		JSONObject obj = new JSONObject();
		if (MemberDAL.checkemailexist(item.getEmail()) == 0) {
			int result = MemberDAL.add_memeber_dao(item.getEmail(), item.getPassword(), item.getFullname(),
					item.getBirthday(), item.getPhone());
			if(result==0){
				ServletContext servl  = request.getServletContext();							
				String url = servl.getInitParameter("urlconfirmregister");
				url = url.replace("@email",item.getEmail());
				url = url.replace("??","&");
				String urltext = request.getRealPath("/upload/txt");	
				urltext +="/confirm_register.txt";
				String content_text = Extra.readFile(urltext);
				content_text = content_text.replace("@username",item.getEmail());
				content_text = content_text.replace("@url ",url);				
				////System.out.println(content_text);					
				int rs = ReadServiceMail.SendingFromgmail(item.getEmail(), "Confirm Register", content_text, request);
				if(rs == 1){
					MemberDAL.remove_user(item.getEmail());
				}
				result =rs;
			}
			obj.put("result", result);
		} else {
			obj.put("result", -2);
		}
		str = JsonHelper.ConvetJSONObjectToJson(obj);
		return str;
	}

	public static String check_login(String email, String pass, HttpServletRequest request) throws JSONException {
		String str = "";
		////System.out.println("AAAAAAAAAAAAAA"+email);
		JSONObject obj = new JSONObject();
		obj = MemberDAL.check_login(email, pass);
		int check = Integer.parseInt(obj.get("result").toString());		
		if (check == 1) {
			HttpSession session = request.getSession();
			String branch = obj.get("branch").toString();
			String fullname = obj.get("fullname").toString();
			String id = obj.get("id").toString();			
			session.setAttribute("branch", branch);
			session.setAttribute("email", email);
			session.setAttribute("username", fullname);
			session.setAttribute("fullname", fullname);
			obj.put("shortname",CommentController.get_short_fullname(fullname.toUpperCase()));
			//write log 
			
		}
		obj.put("error", String.valueOf(check));
		str = JsonHelper.ConvetJSONObjectToJson(obj);
		return str;
	}
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj = MemberDAL.check_login("minhchau2611%40gmail.com","123456");
		////System.out.println(new Gson().toJson(obj));
	}

	public static String update_memeber(String email, String fullname, String phone, String address)
			throws JSONException {
		String str = "";
		JSONObject obj = new JSONObject();

		int result = MemberDAL.update_memeber_dao(email, fullname, phone, address);
		obj.put("result", result);

		str = JsonHelper.ConvetJSONObjectToJson(obj);
		return str;
	}

	public static String get_info_member(String email) {
		ItemMember item = new ItemMember();
		item = MemberDAL.get_info_member(email);
		String _json = new Gson().toJson(item);
		return _json;
	}

	public static String change_info_acc(String strjson) {
		Map<String, String> Json_decore = new Gson().fromJson(strjson, Map.class);
		String s = Json_decore.get("str");
		ItemMember item = new ItemMember();
		item = new Gson().fromJson(s, ItemMember.class);
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = MemberDAL.change_info_acc(item);
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
	public static String confirm_change_password(String email) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = MemberDAL.confirm_change_password(email);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);
			}
		}
		
		String json = new Gson().toJson(obj);
		////System.out.println(json);
		return json;
	}

	public static String confirm_register(String email) {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = MemberDAL.confirm_register(email);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);
			}
		}

		String json = new Gson().toJson(obj);
		////System.out.println(json);
		return json;
	}

	public static String check_login_comment(String email, String pass,String subid, HttpServletRequest request) throws JSONException {
		String str = "";
		JSONObject obj = new JSONObject();
		obj = MemberDAL.check_login(email, pass);
		int check = Integer.parseInt(obj.get("result").toString());
		if (check == 1) {
			HttpSession session = request.getSession();
//			String fullname = MemberDAL.Getfullname(email);
//			String id = MemberDAL.get_id_by_email(email);
			String fullname = obj.get("fullname").toString();
			String id = obj.get("id").toString();
			String branch = obj.get("branch").toString();
			session.setAttribute("email", email);
			session.setAttribute("username", fullname);
			obj.put("email", String.valueOf(email));
			obj.put("fullname", String.valueOf(fullname));
			obj.put("id", id);
			obj.put("branch", branch);
			obj.put("subid", subid);
			obj.put("shortname", CommentController.get_short_fullname(fullname.toUpperCase()));
		}
		obj.put("error", String.valueOf(check));
		str = JsonHelper.ConvetJSONObjectToJson(obj);
		return str;
	}
	public static String check_login_aft(String email,String subid, HttpServletRequest request) throws JSONException {
		String str = "";
		JSONObject obj = new JSONObject();
		obj = MemberDAL.get_info_login(email);
		int check = Integer.parseInt(obj.get("result").toString());
		if (check == 1) {			
			String fullname = obj.get("fullname").toString();
			String id = obj.get("id").toString();
			String branch = obj.get("branch").toString();
			obj.put("email", String.valueOf(email));
			obj.put("fullname", String.valueOf(fullname));
			obj.put("id", id);
			obj.put("branch", branch);
			obj.put("subid", subid);
			obj.put("shortname", CommentController.get_short_fullname(fullname.toUpperCase()));
		}
		str = JsonHelper.ConvetJSONObjectToJson(obj);
		return str;
	}
}
