package BLL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import Helper.ErrorMesage;
import Helper.JsonHelper;

import Model.ItemRec;

import com.google.gson.Gson;

import DAL.RecDAL;

public class Recbll {
	public static String insert_rec(String strjson,String lang){
	 	 Map<String, String> Json_decore= new Gson().fromJson(strjson,  Map.class);		 
		 String s=Json_decore.get("str");		
		 ItemRec item =new ItemRec();
		 item=new Gson().fromJson( s, ItemRec.class);
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=RecDAL.insert_rec(item, lang);
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
	public static String get_list(String lang) throws JSONException{
		return JsonHelper.ConvertListObjectToJson(RecDAL.get_list(lang));
	}
	public static String get_rec_id(String id,String lang) throws JSONException, JsonGenerationException, JsonMappingException, IOException{
		return JsonHelper.ConvertObjectToJson(RecDAL.get_rec_id(id, lang));
	}
	public static String get_list_(String option,String value,String lang) throws JSONException{
		return JsonHelper.ConvertListObjectToJson(RecDAL.get_list_(option,value,lang));
	}
	public static String delete_rec(String id,String lang){
		Map<String,String> obj = new HashMap<String,String>();
		int result = RecDAL.delete_rec(id, lang);		
		String mes = ErrorMesage.getMesageError(result);
		obj.put("result",String.valueOf(result));
		obj.put("message",mes);
		String json = new Gson().toJson(obj);
		return json; 
	}
}
