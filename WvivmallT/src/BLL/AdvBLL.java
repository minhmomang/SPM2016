package BLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import DAL.AdvDAL;
import Helper.ErrorMesage;
import Model.SlideModel;

public class AdvBLL {
	
	
	public  String get_list_slide(){
		ArrayList<SlideModel> list = new ArrayList<SlideModel>();		
		list = AdvDAL.get_list_slide();
		String jsontext = new Gson().toJson(list);
		 return jsontext;
	}
	public  String get_list_slide_active(){
		ArrayList<SlideModel> list = new ArrayList<SlideModel>();		
		list = AdvDAL.get_list_slide_active();
		String jsontext = new Gson().toJson(list);
		 return jsontext;
	}
	public  String save_slide(String str){
		SlideModel a=new SlideModel();
		a=new Gson().fromJson(str,  SlideModel.class);
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=AdvDAL.save_slide(a);
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
	public  String delete_slide(String str){
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=AdvDAL.delete_slide(str);
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
	public  String lock(String str){
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=AdvDAL.lock(str);
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
	public  String unlock(String str){
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=AdvDAL.unlock(str);
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
}
