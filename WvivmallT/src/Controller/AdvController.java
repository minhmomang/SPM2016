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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import BLL.AdvBLL;
import Helper.responseUtf8;

@Controller
@RequestMapping(value="AdvController")
public class AdvController {
	AdvBLL bll;
	
	@RequestMapping(value="save_slide",method=RequestMethod.GET)
	@ResponseBody
	public void save_slide(@RequestParam("str") String str,HttpServletResponse response) throws  IOException{
		 bll= new AdvBLL();
		
		String jsontext = bll.save_slide(str);
		 responseUtf8.response_Utf8(response, jsontext);
	}
	
	@RequestMapping(value="delete_slide",method=RequestMethod.GET)
	@ResponseBody
	public void delete_slide(@RequestParam("str") String str,
			HttpServletResponse response) throws IOException{
		bll= new AdvBLL();	
	
		 String  result=bll.delete_slide(str);
		responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value="lock",method=RequestMethod.GET)
	@ResponseBody
	public void lock(@RequestParam("str") String str,
			HttpServletResponse response) throws IOException{
		bll= new AdvBLL();	
	
		 String  result=bll.lock(str);
		responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value="unlock",method=RequestMethod.GET)
	@ResponseBody
	public void unlock(@RequestParam("str") String str,
			HttpServletResponse response) throws IOException{
		bll= new AdvBLL();	
	
		 String  result=bll.unlock(str);
		responseUtf8.response_Utf8(response, result);

	}
	@RequestMapping(value="get_list_slide",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_slide(HttpServletResponse response) throws  IOException{
		 bll= new AdvBLL();
		String jsontext = bll.get_list_slide();
	
		 responseUtf8.response_Utf8(response, jsontext);
	}
	@RequestMapping(value="get_list_slide_active",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_slide_active(HttpServletResponse response) throws  IOException{
		 bll= new AdvBLL();
		String jsontext = bll.get_list_slide_active();
	
		 responseUtf8.response_Utf8(response, jsontext);
	}
	@RequestMapping(value = "upload_image",method=RequestMethod.POST)
	@ResponseBody
	public String  upload_image(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String jsontext = "";		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl  = request.getServletContext();
		//String savePath =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("saveadvimage");
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
						item.write(new File(savePath + File.separator + name));
						filename = name;
						//item.write(new File(savePath , name));
					
						 
					}
				}				
			} 
			catch (Exception e) 
			{
			  e.printStackTrace();
			}
		}
		//////System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}
}
