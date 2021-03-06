package Controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import BLL.WriterBLL;

import Helper.responseUtf8;

@Controller
@RequestMapping(value="WriterController")
public class WriterController {
	@RequestMapping(
			value="save_writer",
			method=RequestMethod.POST,	
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public void save_writer(@RequestBody final String json_service,
					HttpServletResponse response) throws IOException{				
				String  result=WriterBLL.save_writer(json_service);
				responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value = "upload_feature_image",method=RequestMethod.POST)
	@ResponseBody
	public String  upload_feature_image(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsontext = "";		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl  = request.getServletContext();
		//String savePath =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("save_image_writer");
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
						DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
						Calendar cal = Calendar.getInstance();
						String name =dateFormat.format(cal.getTime())+"_"+ new File(item.getName()).getName();
						filename = name;
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
	@RequestMapping(value="get_list_writer",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_writer(@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_list_writer(lang);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_writer_by_id",method=RequestMethod.GET)
	@ResponseBody
	public void get_writer_by_id(@RequestParam("Writerid") String Writerid,@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_writer_by_id(Writerid,lang);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="delete_writer",method=RequestMethod.GET)
	@ResponseBody
	public void delete_Service(@RequestParam("str_writer") String str_writer,
										HttpServletResponse response) throws IOException{
		
		String  result=WriterBLL.delete_writer(str_writer);
		responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value="publish_writer",method=RequestMethod.GET)
	@ResponseBody
	public void publish_Service(@RequestParam("str_writer") String str_writer,@RequestParam("publish") String publish,
			@RequestParam("lang") String lang,
										HttpServletResponse response) throws IOException{
		
		String  result=WriterBLL.publish_writer(str_writer,publish,lang);
		responseUtf8.response_Utf8(response, result);
	}
	@RequestMapping(value="get_writer_client",method=RequestMethod.GET)
	@ResponseBody
	public void GET_SERVICE_CLIENT(HttpServletResponse response,@RequestParam("lang") String lang) throws IOException, JSONException{
		
		 String json = WriterBLL.get_writer_client(lang);		 
		 responseUtf8.response_Utf8(response, json);
		
	}
	@RequestMapping(value="get_writer_client_all",method=RequestMethod.GET)
	@ResponseBody
	public void get_writer_client_all(HttpServletResponse response,@RequestParam("lang") String lang) throws IOException, JSONException{
		
		 String json = WriterBLL.get_writer_client_all(lang);		 
		 responseUtf8.response_Utf8(response, json);
		
	}
	@RequestMapping(value="get_list_category",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_category(HttpServletResponse response) throws IOException, JSONException{
		
		String json=WriterBLL.get_list_category();
	//	System.out.print(json);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_list_top_writer",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_top_writer(@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_list_top_writer(lang);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_list_top_writer_home",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_top_writer_home(@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_list_top_writer_home(lang);
		responseUtf8.response_Utf8(response, json);
	}
	
	@RequestMapping(value="get_read_most",method=RequestMethod.GET)
	@ResponseBody
	public void get_read_most(@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_read_most(lang);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_read_most_category",method=RequestMethod.GET)
	@ResponseBody
	public void get_read_most_category(@RequestParam("lang") String lang,@RequestParam("cate") String cate,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_read_most_category(lang,cate);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_read_more_category",method=RequestMethod.GET)
	@ResponseBody
	public void get_read_more_category(@RequestParam("lang") String lang,@RequestParam("cate") String cate,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_read_more_category(lang,cate);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="search_basic",method=RequestMethod.GET)
	@ResponseBody
	public void search_basic(@RequestParam("lang") String lang,@RequestParam("cate") String cate,@RequestParam("key") String key,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.search_basic(lang,cate,key);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_news",method=RequestMethod.GET)
	@ResponseBody
	public void get_news(@RequestParam("lang") String lang,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_news(lang);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_news_via_cate",method=RequestMethod.GET)
	@ResponseBody
	public void get_news_via_cate(@RequestParam("lang") String lang,@RequestParam("cate") String cate,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_news_via_cate(lang,cate);
		responseUtf8.response_Utf8(response, json);
	}
	@RequestMapping(value="get_news_id",method=RequestMethod.GET)
	@ResponseBody
	public void get_news_id(@RequestParam("lang") String lang,@RequestParam("id") String id,HttpServletResponse response) throws IOException{
		
		String json=WriterBLL.get_news_id(lang,id);
		responseUtf8.response_Utf8(response, json);
	}
}
