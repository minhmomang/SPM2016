package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("UploadImageController")
public class UploadImageController {

	@RequestMapping(value = "upload_image",method=RequestMethod.POST)
	@ResponseBody
	public void  upload_image(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		ServletContext servl  = request.getServletContext();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String savePathr = servl.getInitParameter("pathsaveimgckeditor");
		
		String folder_save = servl.getInitParameter("save_image");
		String savePath =  servl.getRealPath(folder_save);
		if (isMultipart) {			
			FileItemFactory factory = new DiskFileItemFactory();
			////System.out.println(savePath);	
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);				
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						
						DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
						Calendar cal = Calendar.getInstance();
						String name =dateFormat.format(cal.getTime())+"_"+ new File(item.getName()).getName();	
						item.write(new File(savePath + File.separator + name));
						//save into database
						
						savePathr+=name;
					}
				}				
				response.setContentType("text/html; charset=UTF-8");  
		        response.setHeader("Cache-Control", "no-cache");  
		        PrintWriter out = response.getWriter();  
				String callback = request.getParameter("CKEditorFuncNum");				
			    out.println("<script type=\"text/javascript\">");   
			    out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
			                + ",'" + savePathr + "',''" + ")");  
			        out.println("</script>");  
			        out.flush();  
			        out.close();  
			} 
			catch (Exception e) 
			{
			  e.printStackTrace();
			}
		}
	}
}
