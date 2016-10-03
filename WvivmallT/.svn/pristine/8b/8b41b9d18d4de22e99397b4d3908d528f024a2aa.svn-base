package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import BLL.Aboutbll;
import BLL.Recbll;
import Helper.Extra;
import Helper.responseUtf8;
@Controller
@RequestMapping(value="RecController")
public class RecController {
	@RequestMapping(
			value="insert_rec",
			method=RequestMethod.POST,	
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public void insert_rec(@RequestBody final String _json,
					HttpServletResponse response,HttpServletRequest request) throws IOException{
				
		HttpSession session = request.getSession();
		String lang = "VN";
		if(session.getAttribute("language")!=null){
			lang = session.getAttribute("language").toString();
		}
		String  result=Recbll.insert_rec(_json, lang);
		responseUtf8.response_Utf8(response, result);
	}
	@RequestMapping(value="get_list",method=RequestMethod.GET)
	@ResponseBody
	public void get_list(HttpServletResponse response,HttpServletRequest request) throws IOException, JSONException{
		HttpSession session = request.getSession();
		String lang = "VN";
		if(session.getAttribute("language")!=null){
			lang = session.getAttribute("language").toString();
		}
		responseUtf8.response_Utf8(response, Recbll.get_list(lang));
	}//end get_user_info
	@RequestMapping(value="get_rec_id",method=RequestMethod.GET)
	@ResponseBody
	public void get_rec_id(@RequestParam("id") String id,HttpServletResponse response,HttpServletRequest request) throws IOException, JSONException{
		HttpSession session = request.getSession();
		String lang = "VN";
		if(session.getAttribute("language")!=null){
			lang = session.getAttribute("language").toString();
		}
		responseUtf8.response_Utf8(response, Recbll.get_rec_id(id,lang));
	}//end get_user_info
	@RequestMapping(value="get_list_",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_(
			@RequestParam("option") String option,
			@RequestParam("value") String value,
			HttpServletResponse response,
			HttpServletRequest request) throws IOException, JSONException{
		HttpSession session = request.getSession();
		String lang = "VN";
		if(session.getAttribute("language")!=null){
			lang = session.getAttribute("language").toString();
		}
		responseUtf8.response_Utf8(response, Recbll.get_list_(option,value,lang));
	}//end get_user_info
	@RequestMapping(value = "get_optionsearch",method=RequestMethod.GET)
	@ResponseBody
	public void  get_optionsearch(HttpServletResponse response) throws  IOException{
//		String json = Extra.get_allcode("REC", "SEARCH");
//		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="delete_rec",method=RequestMethod.GET)
	@ResponseBody
	public void delete_rec(@RequestParam("id") String id,HttpServletResponse response,HttpServletRequest request) throws IOException, JSONException{
		HttpSession session = request.getSession();
		String lang = "VN";
		if(session.getAttribute("language")!=null){
			lang = session.getAttribute("language").toString();
		}
		responseUtf8.response_Utf8(response, Recbll.delete_rec(id,lang));
	}//end get_user_info
}
