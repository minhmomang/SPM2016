package Controller;

import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





import com.google.gson.Gson;

import BLL.Sale_acticlell;
import DAL.Sale_acticleDAL;
import Model.Sale_Acticle;
import Helper.ErrorMesage;
import Helper.responseUtf8;

@Controller
@RequestMapping(value="Sale_acticleController")
public class Sale_acticleController {
	@RequestMapping(
			value="save_Sale_acticle",
			method=RequestMethod.POST,	
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public void save_Sale_acticle(@RequestBody final String json_subject,
					HttpServletResponse response) throws IOException{
			
		String  result=Sale_acticlell.insert_Sale_acticle(json_subject);
		responseUtf8.response_Utf8(response, result);
	}
	
	@RequestMapping(value="get_Sale_acticle",method=RequestMethod.GET)
	@ResponseBody
	public void get_Sale_acticle(@RequestParam("p_id") String p_id,HttpServletResponse response) throws IOException{
		String  result=Sale_acticlell.get_data(p_id);
		responseUtf8.response_Utf8(response,result);
	}//end get_user_info
	@RequestMapping(value="get_list_saleacticle",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_saleacticle(
			HttpServletResponse response) throws IOException{
		ArrayList<Sale_Acticle> list = new ArrayList<Sale_Acticle>();
		list = Sale_acticleDAL.get_list_saleacticle();
		String json = new Gson().toJson(list);

		responseUtf8.response_Utf8(response,json );
	}
	@RequestMapping(value="get_list_saleacticle_client",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_saleacticle_client(
			HttpServletResponse response) throws IOException{
		ArrayList<Sale_Acticle> list = new ArrayList<Sale_Acticle>();
		list = Sale_acticleDAL.get_list_saleacticle_client();
		String json = new Gson().toJson(list);

		responseUtf8.response_Utf8(response,json );
	}
	@RequestMapping(value="get_saleacticle_clientbyid",method=RequestMethod.GET)
	@ResponseBody
	public void get_saleacticle_clientbyid(@RequestParam("khuyenmaiid") String id,
			HttpServletResponse response) throws IOException{
	Sale_Acticle l = new Sale_Acticle();
		l = Sale_acticleDAL.get_saleacticle_clientbyid(id);
		String json = new Gson().toJson(l);
		responseUtf8.response_Utf8(response,json );
	}
	@RequestMapping(value="delete_sale",method=RequestMethod.GET)
	@ResponseBody
	public void delete_sale(@RequestParam("str") String str,
										HttpServletResponse response) throws IOException{
		
		
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=Sale_acticleDAL.delete_sale(str);
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
	@RequestMapping(value="visible1_sale",method=RequestMethod.GET)
	@ResponseBody
	public void visible1_sale(@RequestParam("str") String str,
										HttpServletResponse response) throws IOException{
		
		
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=Sale_acticleDAL.visible1_sale(str);
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
	@RequestMapping(value="visible0_sale",method=RequestMethod.GET)
	@ResponseBody
	public void visible0_sale(@RequestParam("str") String str,
										HttpServletResponse response) throws IOException{
		
		
		 Map<String, Object> result = new HashMap<String, Object>();	
		 Map<String,String> obj = new HashMap<String,String>();
		 result=Sale_acticleDAL.visible0_sale(str);
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
	@RequestMapping(value = "upload_feature_image",method=RequestMethod.POST)
	@ResponseBody
	public String  upload_feature_image(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsontext = "";		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl  = request.getServletContext();
		//String savePath =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("save_image_sale_acticle");
		String savePath =servl.getRealPath(folder_save);
		//String savePath = servl.getContextPath()+File.separator+"Upload"+File.separator+"Feature_Image";
		//String savePath2 = request.getServletContext().getInitParameter("savepath2");
		//////System.out.println(savePath);
		String filename = "";
		if (isMultipart) {			
			FileItemFactory factory = new DiskFileItemFactory();						
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload.parseRequest( request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						////System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
						String name =dateFormat.format(cal.getTime())+"_"+ new File(item.getName()).getName();		
						filename+=name+";";						
						item.write(new File(savePath , name));
						/*
						 * String name = new File(item.getName()).getName();	
						filename+=name+";";						
						item.write(new File(savePath2 + "\\" + name));
						 */
						 
					}
				}				
			} 
			catch (Exception e) 
			{
			  e.printStackTrace();
			}
		}
		////System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}
}