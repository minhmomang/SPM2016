package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import DAL.CatgoryDAL;
import Helper.ErrorMesage;
import Helper.responseUtf8;

@Controller
@RequestMapping(value="Catgory_controller")
public class Catgory_controller {

	
	@RequestMapping(value="unvisible_catgory",method=RequestMethod.GET)
	@ResponseBody
	public void unvisible_catgory(@RequestParam("str_catgory") String str_catgory,
										HttpServletResponse response) throws IOException{
		
		
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=CatgoryDAL.Unvisible_Catgory(str_catgory);
		 if(result!=null){
			 if(result.size()>0){
				 String f =result.get("f").toString();
				 String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				 obj.put("result",f);
				 obj.put("message",mes);
				
			 }
		 }
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value="visible_catgory",method=RequestMethod.GET)
	@ResponseBody
	public void visible_catgory(@RequestParam("str_catgory") String str_catgory,
										HttpServletResponse response) throws IOException{
		
		
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=CatgoryDAL.visible_Catgory(str_catgory);
		 if(result!=null){
			 if(result.size()>0){
				 String f =result.get("f").toString();
				 String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				 obj.put("result",f);
				 obj.put("message",mes);
				
			 }
		 }
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}
	
	
}
