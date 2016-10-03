package BLL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import DAL.AboutDAL;

import Helper.ErrorMesage;
import Helper.JsonHelper;
import Model.ItemAboutModel;


import com.google.gson.Gson;

public class Aboutbll {

	public static String insert_about(String strjson){
	 	 Map<String, String> Json_decore= new Gson().fromJson(strjson,  Map.class);		 
		 String s=Json_decore.get("str");
		 String lang=Json_decore.get("lang");
		 ItemAboutModel itemabout =new ItemAboutModel();
		 itemabout=new Gson().fromJson( s, ItemAboutModel.class);
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=AboutDAL.insert_about(replace_origin_html(itemabout.getDesc()),lang);
		 if(result!=null){
			 if(result.size()>0){
				 String f =result.get("f").toString();
				 String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				 obj.put("result",f);
				 obj.put("message",mes);
			 }
		 }
		 String json = new Gson().toJson(obj);
		return json; 		
	}
	private static String replace_origin_html(String str_html){
		return str_html.replace( "&amp;","&").replace("&lt;","<").replace("&gt;",">").replace( "&quot;","\"").replace("&blink;","'");
	}
	public static String get_data(String lang) throws JsonGenerationException, JsonMappingException, IOException{
		ItemAboutModel item = new ItemAboutModel();
		item = AboutDAL.get_about(lang);
		String json = JsonHelper.ConvertObjectToJson(item);
		return json;
	}
	public static String get_about_client(String lang) throws JsonGenerationException, JsonMappingException, IOException{
		ItemAboutModel item = new ItemAboutModel();
		item = AboutDAL.get_about_client(lang);
		String json = JsonHelper.ConvertObjectToJson(item);
		return json;
	}
}
