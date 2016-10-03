package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import BLL.UserBLL;
import Helper.responseUtf8;
import Service.ReadServiceVmall;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "Admin_loginController")
public class Admin_loginController {
	UserBLL bll;
	@RequestMapping(value = "login_admin", method = RequestMethod.GET)
	@ResponseBody
	public void login_admin(@RequestParam("email") String email,@RequestParam("pass") String pass,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		// check again
		
	}// end login_admin
	

	@RequestMapping(value = "getSession_admin", method = RequestMethod.GET)
	@ResponseBody
	public void _getSession_admin(HttpServletResponse response, HttpServletRequest request) throws IOException {

		String is_session = "error_getsession_profile_admin";
		String adminemail = "u1";
		String username = "u1";
		HttpSession session = request.getSession();
		if (session.getAttribute("adminlogin") != null) {
			is_session = session.getAttribute("adminlogin").toString();
			adminemail = session.getAttribute("adminemail").toString();
			username = session.getAttribute("username").toString();
		}
		////System.out.println("is_session: "+is_session);
		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("isSessionAdmin", is_session);
		m1.put("adminemail", adminemail);
		m1.put("username", username);
		String json = new Gson().toJson(m1);
		responseUtf8.response_Utf8(response, json);

	}// end getSession

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String is_session = null;
		HttpSession session = request.getSession();
		Map<String, String> m1 = new HashMap<String, String>();
		if (request.getParameter("logout").equals("LOG_OUT")) {
			session.invalidate();
			is_session = "logout_success";
		}
		m1.put("isSessionAdmin", is_session);
		String json = new Gson().toJson(m1);
		responseUtf8.response_Utf8(response, json);
	}// end logout

	@RequestMapping(value = "change_language", method = RequestMethod.GET)
	@ResponseBody
	public void change_language(@RequestParam("lang") String lang, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Map<String, String> m1 = new HashMap<String, String>();
		HttpSession session = request.getSession();
		session.setAttribute("language", lang);
		m1.put("lang", lang);
		String json = new Gson().toJson(m1);
		responseUtf8.response_Utf8(response, json);

	}//

	@RequestMapping(value = "get_Test", method = RequestMethod.GET)
	@ResponseBody
	public void get_Test(HttpServletResponse response, HttpServletRequest request) throws IOException {

		Map<String, String> m1 = new HashMap<String, String>();
		m1.put("test", "ta day nbe");
		String json = new Gson().toJson(m1);
		responseUtf8.response_Utf8(response, json);

	}// end getSession

}// end lass
