package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import DAL.ProductDAL;
import DAL.ProductTypeDAL;

import Helper.ErrorMesage;
import Helper.Extra;
import Helper.JsonHelper;
import Helper.responseUtf8;
import Model.ItemBranch;
import Model.ItemColor;
import Model.ItemHistoryProduct;
import Model.ItemHistorySearchAdvanceProduct;
import Model.ItemHistorySearchProduct;
import Model.ItemModel;
import Model.ItemProductType;
import Model.ItemProperty;
import Model.Product.ProductData;
import Model.ProductType;
import Service.ProductTypeService;

@Controller
@RequestMapping(value = "ProductController")
public class ProductController {
	@RequestMapping(value = "get_list_product", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product(@RequestParam("fromrecode") String p_fromrecode,
			@RequestParam("recordperpage") String p_recordperpage, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		int fromrecord = Integer.parseInt(p_fromrecode);
		int recordperpage = Integer.parseInt(p_recordperpage);
		// default get top 10
		try {
			list = ProductDAL.getListProduct(fromrecord, recordperpage);
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_category", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_category(HttpServletResponse response) throws IOException {
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		list = ProductTypeDAL.get_list_producttype_client();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		String json = jsonArray.toString();

		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_list_product_sell_best", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_sell_best(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_sell_best();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}
	public static void main(String[] args) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_sell_best();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		System.out.println(jsonArray.toString());
	}

	/*
	 * @RequestMapping(value="get_list_product_popup",method=RequestMethod.GET)
	 * 
	 * @ResponseBody public void get_list_product_popup( @RequestParam("catid")
	 * String catid, HttpServletResponse response) throws IOException{
	 * response.setCharacterEncoding("UTF-8");
	 * response.setContentType("application/json"); ArrayList<ProductData> list
	 * = new ArrayList<ProductData>(); String json="";
	 * 
	 * //default get top 10 try {
	 * 
	 * json = ProductDAL.get_list_product_popup(catid); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * responseUtf8.response_Utf8(response,json); }
	 */
	@RequestMapping(value = "get_list_product_rand", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_rand(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		String json = "";

		// default get top 10
		try {

			json = ProductDAL.get_list_product_rand();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_list_product_popup2", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_popup2(@RequestParam("catid") String catid, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		String json = "";

		// default get top 10
		try {

			json = ProductDAL.get_list_product_popup2(catid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_list_product_popup3", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_popup3(@RequestParam("cate") String cate, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		String json = "";

		// default get top 10
		try {

			json = ProductDAL.get_list_product_popup3(cate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_all_rand_product_forindex", method = RequestMethod.GET)
	@ResponseBody
	public void get_all_rand_product_forindex(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		String json = "";

		// default get top 10
		try {

			json = ProductTypeDAL.get_all_rand_product_forindex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_list_product_cate", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_cate(@RequestParam("cate") String cate,
			@RequestParam("fromrecode") String p_fromrecode, @RequestParam("recordperpage") String p_recordperpage,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		int fromrecord = Integer.parseInt(p_fromrecode);
		int recordperpage = Integer.parseInt(p_recordperpage);
		// default get top 10
		try {
			list = ProductDAL.getListProductCate(cate, fromrecord, recordperpage);
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_product_top", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_top(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_top();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_product_adv", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_adv(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_adv();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "search_basic", method = RequestMethod.GET)
	@ResponseBody
	public void search_basic(@RequestParam("key") String key, @RequestParam("category") String category,
			@RequestParam("fromrecode") String p_fromrecode, @RequestParam("recordperpage") String p_recordperpage,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String str = "";
		// default get top 10
		try {
			////// System.out.println("search_basic");
			str = ProductDAL.search_basic(key, p_fromrecode, p_recordperpage, category);
			//// System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "search_group", method = RequestMethod.GET)
	@ResponseBody
	public void search_group(@RequestParam("fromrecode") String p_fromrecode,
			@RequestParam("recordperpage") String p_recordperpage, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ////System.out.println("Use Log4jWrapper");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String str = "";
		// default get top 10

		try {

			str = ProductDAL.search_group(p_fromrecode, p_recordperpage);
		} catch (Exception e) {

			e.printStackTrace();
		}
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "search_adv", method = RequestMethod.GET)
	@ResponseBody
	public void search_product_advand(@RequestParam("key") String key, @RequestParam("cate") String cate,
			@RequestParam("fromprice") String fromprice, @RequestParam("toprice") String toprice,
			@RequestParam("fromrecode") String p_fromrecode, @RequestParam("recordperpage") String p_recordperpage,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String str = "";
		// default get top 10
		try {
			str = ProductDAL.search_product_advand(key, cate, fromprice, toprice, p_fromrecode, p_recordperpage);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "get_info_product", method = RequestMethod.GET)
	@ResponseBody
	public void get_info_product(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ProductData item = new ProductData();
		item = ProductDAL.getInfoProduct(id);
		Gson gson = new Gson();
		String str = gson.toJson(item);
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "rating_product", method = RequestMethod.GET)
	@ResponseBody
	public void rating_product(@RequestParam("id") String id, @RequestParam("rating") String rating,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		HttpSession session = request.getSession();
		Object branchObject = session.getAttribute("branch");
		Object emailObject = session.getAttribute("email");
		String email = "Guest";
		String branch = "";
		if (emailObject != null) {
			email = emailObject.toString();
			//// System.out.println(email);
		} else {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

			email = email + sdf.format(cal.getTime());
			//// System.out.println(email);
		}
		if (branchObject != null) {
			branch = branchObject.toString();
		}

		int result = ProductDAL.Addrating(id, rating, email, branch);
		responseUtf8.response_Utf8(response, String.valueOf(result));
	}

	@RequestMapping(value = "get_list_product_compare", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_compare(@RequestParam("id") String id, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_compare(id);
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_image_product", method = RequestMethod.GET)
	@ResponseBody
	public void get_image_product(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.getlistimagebyid(id);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "upload_image_product", method = RequestMethod.POST)
	@ResponseBody
	public void upload_image(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext servl = request.getServletContext();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		String folder_save = servl.getInitParameter("save_image_product");
		String savePath = servl.getRealPath(folder_save);
		PrintWriter out = response.getWriter();
		String name = "";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
						Calendar cal = Calendar.getInstance();
						name = dateFormat.format(cal.getTime()) + "_" + new File(item.getName()).getName();
						item.write(new File(savePath + File.separator + name));
						// save into database
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.write(name);
	}

	@RequestMapping(value = "insert_product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void insert_product(@RequestBody final String json_str, HttpServletResponse response) throws IOException {
		Map<String, String> Json_decore = new Gson().fromJson(json_str, Map.class);
		String s = Json_decore.get("str");
		//// System.out.println("tầng 1--------"+s);
		String action = Json_decore.get("action");
		String lang = Json_decore.get("lang");
		ProductData item = new ProductData();
		//// System.out.println("tầng 1--------:"+lang);
		item = new Gson().fromJson(s, ProductData.class);
		int result = ProductDAL.insertproduct(action, item.getProductId(), item.getProductName(),
				item.getProducTypeId(), item.getProductImage(), Extra.replace_origin_html(item.getProductDes()),
				item.getProductPrice(), item.getProductProviderId(), Float.parseFloat(item.getProductquantity()),
				Extra.replace_origin_html(item.getMoreinfo()), Extra.replace_origin_html(item.getProductguide()),
				Extra.replace_origin_html(item.getProductDescShort()), item.getProductimglarg(), item.getTypeimglarg(),
				item.getProperty(),item.getColor(),item.getBranch());
		responseUtf8.response_Utf8(response, String.valueOf(result));

	}

	@RequestMapping(value = "check_product_exists", method = RequestMethod.GET)
	@ResponseBody
	public void check_product_exists(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ProductData item = new ProductData();
		try {
			item = ProductDAL.getInfoProduct(id);
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (item.getProductId() == null)
			responseUtf8.response_Utf8(response, "0");
		else
			responseUtf8.response_Utf8(response, "1");
	}

	@RequestMapping(value = "get_info_product_1", method = RequestMethod.GET)
	@ResponseBody
	public void get_info_product_1(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		request.setCharacterEncoding("UTF-8");
		ProductData item = new ProductData();
		item = ProductDAL.getInfoProduct(id);
		Gson gson = new Gson();
		String str = gson.toJson(item);
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "get_list_product_manager", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_manager(@RequestParam("f_row") String f_row, @RequestParam("record") String record,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.getlistproductmanager("", "", "", "", Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());

	}

	@RequestMapping(value = "count_get_list_product_manager", method = RequestMethod.GET)
	@ResponseBody
	public void count_get_list_product_manager(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		int rs = 0;
		rs = ProductDAL.count_get_list_product_manager("", "", "", "");
		Gson gson = new Gson();
		responseUtf8.response_Utf8(response, String.valueOf(rs));
	}

	@RequestMapping(value = "delete_product", method = RequestMethod.GET)
	@ResponseBody
	public void delete_product(@RequestParam("str_product") String str_product, HttpServletResponse response)
			throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = ProductDAL.delete_product(str_product);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value = "visible_product", method = RequestMethod.GET)
	@ResponseBody
	public void visible_product(@RequestParam("str_product") String str_product, HttpServletResponse response)
			throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = ProductDAL.visible_product(str_product);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value = "unvisible_product", method = RequestMethod.GET)
	@ResponseBody
	public void unvisible_product(@RequestParam("str_product") String str_product, HttpServletResponse response)
			throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = ProductDAL.Unvisible_product(str_product);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value = "change_num_order", method = RequestMethod.GET)
	@ResponseBody
	public void change_num_order(@RequestParam("id") String id, @RequestParam("num") String num,
			HttpServletResponse response) throws IOException {

		int result = ProductDAL.change_num_order(id, Integer.parseInt(num));

		responseUtf8.response_Utf8(response, String.valueOf(result));

	}

	@RequestMapping(value = "get_list_image_by_id", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_image_by_id(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.getlistimagebyid(id);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "delete_img_product", method = RequestMethod.GET)
	@ResponseBody
	public void visible_product(@RequestParam("id") String id, @RequestParam("img") String img,
			HttpServletResponse response) throws IOException {

		int result = ProductDAL.delete_image_product_by_id(id, img);
		responseUtf8.response_Utf8(response, String.valueOf(result));

	}

	@RequestMapping(value = "delete_img_product_sub", method = RequestMethod.GET)
	@ResponseBody
	public void delete_img_product_sub(@RequestParam("id") String id, @RequestParam("str_img") String str_img,
			HttpServletResponse response) throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = ProductDAL.delete_img_product(id, str_img);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value = "upload_image_product_sub", method = RequestMethod.POST)
	@ResponseBody
	public void upload_image_product_sub(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext servl = request.getServletContext();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String productid = request.getParameter("id");
		String folder_save = servl.getInitParameter("save_image_product");
		String savePath = servl.getRealPath(folder_save);
		PrintWriter out = response.getWriter();
		String name = "";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {

						DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
						Calendar cal = Calendar.getInstance();
						name = dateFormat.format(cal.getTime()) + "_" + new File(item.getName()).getName();
						item.write(new File(savePath + File.separator + name));
						int result = ProductDAL.insert_image_product_by_id(productid, name);
						// save into database
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.write(name);
	}

	@RequestMapping(value = "get_list_category_manager", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_category_manager(HttpServletResponse response) throws IOException {
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		list = ProductTypeDAL.get_list_producttype_manager();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "insert_category_product", method = RequestMethod.GET)
	@ResponseBody
	public void insert_category_product(
			@RequestParam("type") String type, 
			@RequestParam("id") String id,
			@RequestParam("name") String name, 
			@RequestParam("groupcategory") String groupcategory,
			@RequestParam("groupcategory_name") String groupcategory_name,
			@RequestParam("groupcategorysub") String groupcategorysub,
			@RequestParam("groupcategorysub_name") String groupcategorysub_name,
			@RequestParam("category_img") String category_img,
			HttpServletResponse response) throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		
		// check
		ArrayList<ItemProductType> list_group_category = new ArrayList<ItemProductType>();
		list_group_category = ProductTypeService.get_list_product_type_parent();
		
		String mes="";
		if(!check_exists_group_category(list_group_category,groupcategory)){
			mes = ErrorMesage.getMesageError(20);
			obj.put("result","2");
			obj.put("message",mes);
		}
		else{
			ArrayList<ItemProductType> list_group_category_sub = new ArrayList<ItemProductType>();
			list_group_category_sub = ProductTypeService.get_list_product_type_sub(groupcategory);
			if(!check_exists_group_category(list_group_category_sub,groupcategorysub)){
				mes = ErrorMesage.getMesageError(21);
				obj.put("result","2");
				obj.put("message",mes);
			}
			else{
				result = ProductTypeDAL.insert_cate(type, id, name, category_img,groupcategory,groupcategory_name,groupcategorysub,groupcategorysub_name);
				if (result != null) {
					if (result.size() > 0) {
						String f = result.get("f").toString();
						mes = ErrorMesage.getMesageError(Integer.parseInt(f));
						obj.put("result", f);
						obj.put("message",mes);
					}
				}
			}
		}
		
		
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}
	public static boolean check_exists_group_category(ArrayList<ItemProductType> list,String group){
		for(ItemProductType item : list){
			if(item.getProducttype().equals(group)){
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "get_category_by_id", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_category_manager(@RequestParam("id") String id, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ProductType item = new ProductType();
		item = ProductTypeDAL.get_cateogry_by_id(id);
		Gson gson = new Gson();
		String json = gson.toJson(item);
		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "delete_cate", method = RequestMethod.GET)
	@ResponseBody
	public void delete_cate(@RequestParam("str") String str, HttpServletResponse response) throws IOException {

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		result = ProductTypeDAL.remove_category(str);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message", mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}

	@RequestMapping(value = "get_list_product_manager_cate", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_manager_cate(@RequestParam("cate") String cate, @RequestParam("f_row") String f_row,
			@RequestParam("record") String record, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_manager_cate(cate, Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "count_get_list_product_manager_cate", method = RequestMethod.GET)
	@ResponseBody
	public void count_get_list_product_manager_cate(@RequestParam("cate") String cate, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		int rs = 0;
		rs = ProductDAL.count_get_list_product_manager_cate(cate);
		responseUtf8.response_Utf8(response, String.valueOf(rs));
	}

	@RequestMapping(value = "get_list_new", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_new(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_new();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		String json = jsonArray.toString();
		responseUtf8.response_Utf8(response, json);
	}

	@RequestMapping(value = "get_list_sell_best", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_sell_best(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_sell_best();
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_product_cate", method = RequestMethod.GET)
	@ResponseBody
	public void get_product_cate(@RequestParam("cate") String cate, @RequestParam("fromrecode") String p_fromrecode,
			@RequestParam("recordperpage") String p_recordperpage, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String str = "";
		// default get top 10
		try {
			str = ProductDAL.get_product_cate(cate, p_fromrecode, p_recordperpage);
			//// System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "upload_image_category", method = RequestMethod.POST)
	@ResponseBody
	public String upload_image_category(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.print("AAA");
		String jsontext = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		ServletContext servl = request.getServletContext();
		// String savePath
		// =servl.getRealPath(File.separator+"Upload"+File.separator+"Feature_Image");
		String folder_save = servl.getInitParameter("save_image_cate");
		String savePath = servl.getRealPath(folder_save);

		// String savePath =
		// servl.getContextPath()+File.separator+"Upload"+File.separator+"Feature_Image";
		// String savePath2 =
		// request.getServletContext().getInitParameter("savepath2");
		////// System.out.println(savePath);
		String filename = "";
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<org.apache.commons.fileupload.FileItem> multiparts = upload.parseRequest(request);
				for (org.apache.commons.fileupload.FileItem item : multiparts) {
					if (!item.isFormField()) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						////// System.out.println(dateFormat.format(cal.getTime()));
						////// //2014/08/06 16:00:22
						String name = dateFormat.format(cal.getTime()) + "_" + new File(item.getName()).getName();
						filename += name;
						item.write(new File(savePath, name));
						/*
						 * String name = new File(item.getName()).getName();
						 * filename+=name+";"; item.write(new File(savePath2 +
						 * "\\" + name));
						 */
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		////// System.out.println(filename);
		jsontext = filename;
		return jsontext;
	}

	@RequestMapping(value = "get_list_product_related", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_related(@RequestParam("productid") String productid, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		// default get top 10
		try {
			list = ProductDAL.get_list_product_related(productid);
		} catch (ClassNotFoundException | InstantiationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_product_most_view", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_most_view(@RequestParam("f_row") String f_row, @RequestParam("record") String record,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_most_view(Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "count_get_list_product_most_view", method = RequestMethod.GET)
	@ResponseBody
	public void count_get_list_product_most_view(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		int rs = 0;
		rs = ProductDAL.count_get_list_product_most_view();
		responseUtf8.response_Utf8(response, String.valueOf(rs));
	}

	@RequestMapping(value = "get_list_product_most_buy", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_most_buy(@RequestParam("f_row") String f_row, @RequestParam("record") String record,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_most_buy(Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_product_new", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_new(@RequestParam("f_row") String f_row, @RequestParam("record") String record,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_new(Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_list_product_high_rating", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_high_rating(@RequestParam("f_row") String f_row, @RequestParam("record") String record,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_high_rating(Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "write_log_product", method = RequestMethod.GET)
	@ResponseBody
	public void write_log_product(@RequestParam("str") String str, HttpServletRequest request,
			HttpServletResponse response)
			throws JSONException, IOException, ClassNotFoundException, InstantiationException, SQLException {
		ItemHistoryProduct data = new ItemHistoryProduct();
		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(str, ItemHistoryProduct.class);
		String email = OrderController.get_user_login(request, response);
		data.setUser(email);
		int _rs = ProductDAL.save_history(data);
		JSONObject obj = new JSONObject();
		obj.put("error", _rs);
		String strdata = JsonHelper.ConvetJSONObjectToJson(obj);
		responseUtf8.response_Utf8(response, strdata);
	}

	@RequestMapping(value = "write_log_search_product", method = RequestMethod.GET)
	@ResponseBody
	public void write_log_search_product(@RequestParam("str") String str, HttpServletRequest request,
			HttpServletResponse response)
			throws JSONException, IOException, ClassNotFoundException, InstantiationException, SQLException {
		ItemHistorySearchProduct data = new ItemHistorySearchProduct();
		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(str, ItemHistorySearchProduct.class);
		String email = OrderController.get_user_login(request, response);
		data.setUser(email);
		int _rs = ProductDAL.save_search_history(data);
		JSONObject obj = new JSONObject();
		obj.put("error", _rs);
		String strdata = JsonHelper.ConvetJSONObjectToJson(obj);
		responseUtf8.response_Utf8(response, strdata);
	}

	@RequestMapping(value = "write_log_search_advance_product", method = RequestMethod.GET)
	@ResponseBody
	public void write_log_search_advance_product(@RequestParam("str") String str, HttpServletRequest request,
			HttpServletResponse response)
			throws JSONException, IOException, ClassNotFoundException, InstantiationException, SQLException {
		ItemHistorySearchAdvanceProduct data = new ItemHistorySearchAdvanceProduct();
		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(str, ItemHistorySearchAdvanceProduct.class);
		String email = OrderController.get_user_login(request, response);
		data.setUser(email);
		int _rs = ProductDAL.save_search_advance_history(data);
		JSONObject obj = new JSONObject();
		obj.put("error", _rs);
		String strdata = JsonHelper.ConvetJSONObjectToJson(obj);
		responseUtf8.response_Utf8(response, strdata);
	}

	@RequestMapping(value = "get_product_comment", method = RequestMethod.GET)
	@ResponseBody
	public void get_product_comment(HttpServletRequest request, HttpServletResponse response)
			throws JSONException, IOException, ClassNotFoundException, InstantiationException, SQLException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String email = OrderController.get_user_login(request, response);
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_product_comment(email);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "get_product_recommend", method = RequestMethod.GET)
	@ResponseBody
	public void get_product_recommend(@RequestParam("f_row") String p_fromrecode,
			@RequestParam("record") String p_recordperpage, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String str = "";
		// default get top 10
		try {
			String email = OrderController.get_user_login(request, response);
			ArrayList<ProductData> list = new ArrayList<ProductData>();
			// list = ProductDAL.get_product_comment(email);
			list = ProductDAL.get_product_recommend(email, Integer.parseInt(p_fromrecode),
					Integer.parseInt(p_recordperpage));
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
			}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			str = jsonArray.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "get_product_choose", method = RequestMethod.GET)
	@ResponseBody
	public void get_product_choose(@RequestParam("f_row") String p_fromrecode,
			@RequestParam("record") String p_recordperpage, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String str = "";
		// default get top 10
		try {
			String email = OrderController.get_user_login(request, response);
			ArrayList<ProductData> list = new ArrayList<ProductData>();

			list = ProductDAL.get_product_choose(Integer.parseInt(p_fromrecode), Integer.parseInt(p_recordperpage));
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
			}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			str = jsonArray.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseUtf8.response_Utf8(response, str);
	}

	@RequestMapping(value = "new_product", method = RequestMethod.GET)
	@ResponseBody
	public void new_product(HttpServletRequest request, HttpServletResponse response)
			throws JSONException, IOException, ClassNotFoundException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		Object emailObject = session.getAttribute("email");
		String email = "Guest";
		if (emailObject != null) {
			email = emailObject.toString();
			////// System.out.println(email);
		}
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.new_product(email);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "cancel_new_product", method = RequestMethod.GET)
	@ResponseBody
	public void cancel_new_product(@RequestParam("product_id") String product_id, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException, JSONException {
		int rs = -1;
		HttpSession session = request.getSession();
		Object emailObject = session.getAttribute("email");
		String email = "Guest";
		if (emailObject != null) {
			email = emailObject.toString();
			////// System.out.println(email);
		}
		rs = ProductDAL.cancel_new_product(email, product_id);
		JSONObject obj = new JSONObject();
		obj.put("result", String.valueOf(rs));
		String data = JsonHelper.ConvetJSONObjectToJson(obj);
		responseUtf8.response_Utf8(response, data);
	}

	@RequestMapping(value = "get_list_group_category", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_group_category(HttpServletResponse response) throws IOException {
		ArrayList<ItemProductType> list = new ArrayList<ItemProductType>();
		list = ProductTypeService.get_list_product_type_parent();	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemProductType>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value = "get_list_product_type_sub", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_type_sub(
				@RequestParam("parent") String parent,				
				HttpServletResponse response) throws IOException {
		ArrayList<ItemProductType> list = new ArrayList<ItemProductType>();
		list = ProductTypeService.get_list_product_type_sub(parent);	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemProductType>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value = "get_property", method = RequestMethod.GET)
	@ResponseBody
	public void get_property(
				@RequestParam("product_type") String product_type,				
				HttpServletResponse response) throws IOException {
		ArrayList<ItemProperty> list = new ArrayList<ItemProperty>();
		list = ProductTypeService.get_property(product_type);	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemProperty>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value = "get_color", method = RequestMethod.GET)
	@ResponseBody
	public void get_color(
				@RequestParam("product_type") String product_type,				
				HttpServletResponse response) throws IOException {
		ArrayList<ItemColor> list = new ArrayList<ItemColor>();
		list = ProductTypeService.get_color(product_type);	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemColor>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value = "get_branch", method = RequestMethod.GET)
	@ResponseBody
	public void get_branch(
				@RequestParam("product_type") String product_type,				
				HttpServletResponse response) throws IOException {
		ArrayList<ItemBranch> list = new ArrayList<ItemBranch>();
		list = ProductTypeService.get_branch(product_type);	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemBranch>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value = "get_list_product_promotion", method = RequestMethod.GET)
	@ResponseBody
	public void get_list_product_promotion(
			@RequestParam("cate") String cate,
			@RequestParam("promotion") String promotion, 
			@RequestParam("f_row") String f_row,
			@RequestParam("record") String record, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = ProductDAL.get_list_product_promotion(cate,promotion, Integer.parseInt(f_row), Integer.parseInt(record));
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ProductData>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}

	@RequestMapping(value = "count_get_list_product_promotion", method = RequestMethod.GET)
	@ResponseBody
	public void count_get_list_product_promotion(
			@RequestParam("cate") String cate, 
			@RequestParam("promotion") String promotion, 
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ClassNotFoundException, InstantiationException, SQLException {
		int rs = 0;
		rs = ProductDAL.count_get_list_product_promotion(cate,promotion);
		responseUtf8.response_Utf8(response, String.valueOf(rs));
	}
}
