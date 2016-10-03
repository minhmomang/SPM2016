package Controller;

import java.io.IOException;



import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonParseException;
import BLL.Contactbll;
import Helper.responseUtf8;
import Model.ItemContact;



@Controller
@RequestMapping(value="ContactController")
public class ContactController {
	ObjectMapper mapper = new ObjectMapper();
	@RequestMapping(value="insert_contact",method=RequestMethod.GET)
	@ResponseBody
	public void insert_contact(@RequestParam("str") String str,HttpServletResponse response) throws JSONException, IOException{
	
		ItemContact data = new ItemContact();
		data = mapper.readValue(str, ItemContact.class);
		String result=Contactbll.insert_contact(data);
		responseUtf8.response_Utf8(response, result);
	}
	@RequestMapping(value="insert_support",method=RequestMethod.GET)
	@ResponseBody
	public void insert_support(@RequestParam("str") String str,HttpServletResponse response) throws JSONException, IOException{
		ItemContact data = new ItemContact();
		data = mapper.readValue(str, ItemContact.class);
		String result=Contactbll.insert_support(data);
		responseUtf8.response_Utf8(response, result);
	}
	@RequestMapping(value="get_contact",method=RequestMethod.GET)
	@ResponseBody
	public void get_contact(@RequestParam("contactid") String contactid,HttpServletResponse response) throws IOException{
		String  result=Contactbll.get_contact(contactid);
		responseUtf8.response_Utf8(response,result);
	}//end get_user_info
	
	@RequestMapping(value="updatestate",method=RequestMethod.GET)
	@ResponseBody
	public void updatestate(@RequestParam("contactid") String contactid,
										HttpServletResponse response) throws IOException{
		 String  result=Contactbll.updatestate(contactid);
		responseUtf8.response_Utf8(response, result);

	}
	
	@RequestMapping(
			value="send_mail",
			method=RequestMethod.POST,	
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public void send_mail(@RequestBody final String json_contact,
			HttpServletRequest request,HttpServletResponse response) throws IOException, AddressException, JsonParseException, MessagingException{
				
		ServletContext servl  = request.getServletContext();		
		String user= servl.getInitParameter("email");
		String pass=servl.getInitParameter("password");
		
		String  result=Contactbll.send_mail(json_contact,user,pass);
		responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value="get_list_contact",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_contact(@RequestParam("option") String option,@RequestParam("value") String value,HttpServletResponse response) throws IOException{
	
		String json=Contactbll.get_list_contact(option,value);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="delete_contact",method=RequestMethod.GET)
	@ResponseBody
	public void delete_contact(			
			@RequestParam("str_contact") String str_contact,
			HttpServletResponse response) throws IOException{
		
		
		String json = Contactbll.remove_contact(str_contact);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value="send_contact",method=RequestMethod.GET)
	@ResponseBody
	public void send_contact(@RequestParam("str") String str,
			HttpServletRequest request,
			HttpServletResponse response) throws JSONException, IOException{
		ItemContact item = new ItemContact();
		item = mapper.readValue(str, ItemContact.class);
		String result=Contactbll.send_contact(item, request);
		responseUtf8.response_Utf8(response, result);
	}
}
