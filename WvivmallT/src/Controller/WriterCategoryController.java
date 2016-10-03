package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import BLL.WriterCategoryBLL;
import DAL.ProductTypeDAL;

import Helper.responseUtf8;

@Controller
@RequestMapping(value="WriterCategoryController")
public class WriterCategoryController {
	@RequestMapping(value="get_list_product_category_manager",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_product_category_manager(
			HttpServletResponse response) throws IOException{
		String result = WriterCategoryBLL.get_list_category();
		responseUtf8.response_Utf8(response, result);
	}
	@RequestMapping(value="insert_category_writer",method=RequestMethod.GET)
	@ResponseBody
	public void insert_category_writer(
			@RequestParam("type") String type,
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			HttpServletResponse response) throws IOException{
		String json = WriterCategoryBLL.insert_cate(type, id, name);
		responseUtf8.response_Utf8(response, json);

	}
	@RequestMapping(value="get_category_by_id",method=RequestMethod.GET)
	@ResponseBody
	public void get_cateogry_by_id(
			@RequestParam("id") String id,
			HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		String json = WriterCategoryBLL.get_cateogry_by_id(id);
		responseUtf8.response_Utf8(response, json);

	}
	@RequestMapping(value="remove_category",method=RequestMethod.GET)
	@ResponseBody
	public void remove_category(
			@RequestParam("str") String str,
			HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
		String json = WriterCategoryBLL.remove_category(str);
		responseUtf8.response_Utf8(response, json);

	}
	@RequestMapping(value="get_related_news",method=RequestMethod.GET)
	@ResponseBody
	public void get_related_news(
			HttpServletResponse response) throws IOException{
		String result = WriterCategoryBLL.get_related_news();
		responseUtf8.response_Utf8(response, result);
	}
}
