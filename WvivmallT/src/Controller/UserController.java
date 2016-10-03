package Controller;

import java.io.IOException;

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

@Controller
@RequestMapping(value="UserController")
public class UserController {
	@RequestMapping(value="insert_user",method=RequestMethod.GET)
	@ResponseBody
	public void insert_user(
			@RequestParam("type") String type,			
			@RequestParam("user") String user,
			@RequestParam("pass") String pass,
			@RequestParam("cate_type") String cate_type,
			HttpServletRequest request,
			HttpServletResponse response) throws  IOException{		
		HttpSession session = request.getSession();
		String username="";
		if(session.getAttribute("adminlogin")!=null){			
			username=session.getAttribute("username").toString();
		}
		String jsontext = UserBLL.insert_user(type,username, user, pass, cate_type);
		responseUtf8.response_Utf8(response, jsontext);
		//////System.out.println(jsontext);
	}
	@RequestMapping(value="get_list_user",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_user(			
			HttpServletResponse response) throws  IOException{		
		
		String jsontext = UserBLL.get_list_user();
		responseUtf8.response_Utf8(response, jsontext);
		//////System.out.println(jsontext);
	}
	@RequestMapping(value="get_info_user",method=RequestMethod.GET)
	@ResponseBody
	public void get_info_user(		
			@RequestParam("userid") String userid,
			HttpServletResponse response) throws  IOException{		
		
		String jsontext = UserBLL.get_info_user(userid);
		responseUtf8.response_Utf8(response, jsontext);
		//////System.out.println(jsontext+" : json");
	}
	@RequestMapping(value="remove_user",method=RequestMethod.GET)
	@ResponseBody
	public void remove_user(		
			@RequestParam("str") String str,
			HttpServletResponse response) throws  IOException{		
		
		String jsontext = UserBLL.remove_user(str);
		responseUtf8.response_Utf8(response, jsontext);
	}
	
}
