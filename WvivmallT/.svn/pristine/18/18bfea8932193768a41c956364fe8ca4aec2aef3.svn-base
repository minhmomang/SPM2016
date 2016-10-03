package BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAL.ProductTypeDAL;
import DAL.WriterCategoryDAL;
import DAL.WriterDAL;
import Helper.ErrorMesage;
import Model.ItemModel;

import com.google.gson.Gson;

public class WriterCategoryBLL {
	public static  String get_list_category() throws IOException {
		ArrayList<Object> a =new 	ArrayList<Object>();
		a =WriterDAL.get_list_category();
		String json = new Gson().toJson(a);
		////System.out.println(json);
		return json; 
	}
	public static String insert_cate(String type,String id,String name){
		Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=WriterCategoryDAL.insert_cate(type, id, name);
		 if(result!=null){
			 if(result.size()>0){
				 String f =result.get("f").toString();
				// String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				 obj.put("result",f);
				// obj.put("message",mes);
				
			 }
		 }
		 String json = new Gson().toJson(obj);
		 return json;
	}
	public static String get_cateogry_by_id(String id) throws ClassNotFoundException, InstantiationException, SQLException
	{
		ItemModel item = new ItemModel();
		item = WriterCategoryDAL.get_cateogry_by_id(id);
		Gson gson = new Gson();
		String json = gson.toJson(item);
		return json;
	}
	public static String remove_category(String str)
	{
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=WriterCategoryDAL.remove_category(str);
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
	public static  String get_related_news() throws IOException {
		ArrayList<Object> a =new 	ArrayList<Object>();
		a =WriterDAL.get_related_news();
		String json = new Gson().toJson(a);
		////System.out.println(json);
		return json; 
	}
}
