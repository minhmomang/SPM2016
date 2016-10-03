package BLL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import DAL.Sale_acticleDAL;
import Helper.ErrorMesage;
import Helper.JsonHelper;
import Model.Sale_Acticle;

import com.google.gson.Gson;

public class Sale_acticlell {
	public static String insert_Sale_acticle(String strjson){
	 	 Map<String, String> Json_decore= new Gson().fromJson(strjson,  Map.class);		 
		 String s=Json_decore.get("str");
		 String type=Json_decore.get("type");
		 Sale_Acticle itema =new Sale_Acticle();
		 itema=new Gson().fromJson( s, Sale_Acticle.class);
		 
		 itema.setContent(replace_origin_html(itema.getContent()));
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=Sale_acticleDAL.insert_Sale_acticle(type,itema);
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
	
	public static String get_data(String p_id) throws JsonGenerationException, JsonMappingException, IOException{
		Sale_Acticle item = new Sale_Acticle();
		item = Sale_acticleDAL.get_Sale_acticle(p_id);
		String json = JsonHelper.ConvertObjectToJson(item);
		return json;
	}
	
}
