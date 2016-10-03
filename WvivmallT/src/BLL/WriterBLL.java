package BLL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;

import DAL.WriterDAL;


import Helper.ErrorMesage;
import Helper.Extra;
import Model.ItemTemplateCate;
import Model.ItemTopWriter;
import Model.WriterItem;



import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;

public class WriterBLL {
	public static  String save_writer(String json_str) throws JsonParseException, JsonMappingException, IOException{
		// ////System.out.println("json_subject--------"+json_subject);
		 Map<String, String> Json_decore= new Gson().fromJson(json_str,  Map.class);
		 
		 String s=Json_decore.get("str");
		 ////System.out.println("tầng 1--------"+s);
		 String type=Json_decore.get("type");
		 String lang=Json_decore.get("lang");
		 WriterItem a=new WriterItem();
		 ////System.out.println("tầng 1--------:"+lang);
		 a=new Gson().fromJson( s, WriterItem.class);
		 //////System.out.println("tầng 2--------"+a.getSubject_des());
		 String se=Extra.replace_origin_html(a.getContent());
		 a.setContent(se);
		// ////System.out.println("origin--------"+se);
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=WriterDAL.save_writer(a,type,lang);
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
	public static  String get_list_writer(String lang){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_list_writer(lang);
		 String json = new Gson().toJson(a); 
		 return json;
	}
	public static  String get_writer_by_id(String writerid,String lang){
		WriterItem a =new WriterItem();
		a= WriterDAL.get_writer_by_id( writerid,lang);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static String delete_writer(String str_writer){
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=WriterDAL.delete_writer(str_writer);
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
	public static  String publish_writer(String str_writer,String publish,String lang){
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=WriterDAL.publish_writer(str_writer,publish,lang);
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
	public static  String get_writer_client(String lang) throws IOException {
		ArrayList<WriterItem> a =new 	ArrayList<WriterItem>();
		a =WriterDAL.get_writer_client(lang);
		String json = new Gson().toJson(a);
		return json; 
	}
	public static  String get_writer_client_all(String lang) throws IOException {
		ArrayList<WriterItem> a =new 	ArrayList<WriterItem>();
		a =WriterDAL.get_writer_client_all(lang);
		String json = new Gson().toJson(a);
		return json; 
	}
	public static  String get_list_category() throws IOException {
		ArrayList<Object> a =new 	ArrayList<Object>();
		a =WriterDAL.get_list_category();
		String json = new Gson().toJson(a);
		return json; 
	}
	public static  String get_list_top_writer(String lang){
		ArrayList<Object> a =new ArrayList<Object> ();
		a= WriterDAL.get_list_top_writer(lang);
		String json = new Gson().toJson(a); 
		return json;
	}
	
	public static  String get_list_top_writer_home(String lang){
		ArrayList<Object> a =new ArrayList<Object> ();
		a= WriterDAL.get_list_top_writer_home(lang);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_read_most(String lang){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_read_most(lang);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_read_most_category(String lang,String cate){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_read_most_category(lang,cate);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_read_more_category(String lang,String cate){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_read_more_category(lang,cate);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String search_basic(String lang,String cate,String key){
		ArrayList<ItemTopWriter> a =new ArrayList<ItemTopWriter> ();
		a= WriterDAL.search_basic(lang,cate,key);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_news(String lang){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_news(lang);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_news_via_cate(String lang,String cate){
		ArrayList<WriterItem> a =new ArrayList<WriterItem> ();
		a= WriterDAL.get_news_via_cate(lang,cate);
		String json = new Gson().toJson(a); 
		return json;
	}
	public static  String get_news_id(String lang,String id){
		WriterItem item = new WriterItem();
		item= WriterDAL.get_news_id(lang,id);
		String json = new Gson().toJson(item); 
		return json;
	}
	
}
