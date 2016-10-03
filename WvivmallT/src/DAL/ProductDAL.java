package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import EJB.IConnectEJBS;
import Helper.EncrypterDecrypter;
import Helper.Extra;
import Model.ItemHistoryProduct;
import Model.ItemHistorySearchAdvanceProduct;
import Model.ItemHistorySearchProduct;
import Model.Product.ProductData;
import Model.ProductType;
import Model.ShoppingCartItem;

public class ProductDAL {
	public List<Map<String, Object>> getProduct() throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select * from tb_product where isvisible=true ";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		return listob;
	}

	public static List<Map<String, Object>> getProductbyID(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_type_id,product_image,product_price,product_des,product_price_2,ispromo"
				+ " from tb_product where product_id='" + id + "' and isvisible=true ";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		return listob;
	}

	public static ProductData getInfoProduct(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		ProductData item = new ProductData();
		try {
			String query = "select product_id,product_name,product_type_id ,product_image ,product_des ,product_price ,guide,desc_short,quantity,"
					+ "more_information,product_provider_id,type_img_larg,ispromo,product_price_2,branch,property,color "
					+ "from tb_product "
					+ "where product_id='"+ id + "' and isvisible=true ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			String json = new Gson().toJson(listob);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(0);
				item.setProductId(temp.get("product_id").toString());
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProductName(temp.get("product_name").toString());
				item.setProducTypeId(temp.get("product_type_id").toString());
				item.setProductImage(temp.get("product_image").toString());
				item.setProductDes(temp.get("product_des").toString());
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setProductguide(temp.get("guide").toString());
				item.setProductDescShort(temp.get("desc_short").toString());
				item.setProductquantity(temp.get("quantity").toString());
				item.setMoreinfo(temp.get("more_information").toString());
				item.setTypeimglarg(temp.get("type_img_larg") == null ? "" : temp.get("type_img_larg").toString());
				item.setBranch(temp.get("branch").toString());
				item.setProperty(temp.get("property").toString());
				item.setColor(temp.get("color").toString());
				// item.setProductProviderId(temp.get("product_provider_id").toString());
				break;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	public static ProductData getInfoPromoProduct(String id, String pro_id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ProductData item = new ProductData();
		////System.out.println(id + "---------------------------------" + pro_id);
		try {
			String query = "select category_promotion_id,product_id,price,percent,price_percent from tb_cate_promotion_product  "
					+ " where product_id='" + id + "' and category_promotion_id='" + pro_id + "'";

			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			Map<String, Object> temp = listob.get(0);
			item.setCatePromotionId(temp.get("category_promotion_id").toString());
			item.setProductId(temp.get("product_id").toString());
			item.setProductPrice(Float.parseFloat(temp.get("price").toString()));
			item.setPricePercent(Integer.parseInt(temp.get("percent").toString()));
			item.setNewPrice(Float.parseFloat(temp.get("price_percent").toString()));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	public static ProductData getInfoProductOB(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		ProductData item = new ProductData();
		try {
			String query = "select product_id id,product_name name,product_type_id typeproduct,product_image image,product_des des,product_price price,guide,desc_short,quantity,more_information from tb_product where product_id='"
					+ id + "' and isvisible=true ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			Map<String, Object> temp = listob.get(0);
			item.setProductId(temp.get("id").toString());
			item.setProductName(temp.get("name").toString());
			item.setProducTypeId(temp.get("typeproduct").toString());
			item.setProductImage(temp.get("image").toString());
			item.setProductDes(temp.get("des")==null?"":temp.get("des").toString());
			item.setProductPrice(Float.parseFloat(temp.get("price").toString()));
			item.setProductguide(temp.get("guide").toString());
			item.setProductDescShort(temp.get("desc_short").toString());
			item.setProductquantity(temp.get("quantity").toString());
			item.setMoreinfo(temp.get("more_information").toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return item;
	}

	public static ArrayList<ProductData> getlistimageproduct(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		ArrayList<ProductData> list = new ArrayList<ProductData>();
		try {
			String query = "select product_id id,product_image image from tb_product_sub where product_id='" + id
					+ "' and isvisible=true ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("id").toString());
				item.setProductImage(temp.get("image").toString());
				list.add(item);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ShoppingCartItem getProductByIDReturnCartItem(String ID)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ShoppingCartItem item = new ShoppingCartItem();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = getProductbyID(ID);
		if(listob.size()>0){
			Map<String, Object> temp = listob.get(0);
			item.setID(temp.get("product_id").toString());
			item.setName(temp.get("product_name").toString());
			item.setQuantity(1);
			item.setImageUrl(temp.get("product_image").toString());
			item.setPrice(Float.parseFloat(temp.get("product_price").toString()));
		}
		
		return item;
	}

	public static ArrayList<ProductData> getListProduct(int fromrecord, int recordperpage)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_price,product_image,product_img_larg from tb_product where isvisible=true order by orderproduct desc limit "
				+ fromrecord + "," + recordperpage;
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		int rownum = get_count_product();
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			item.setProductimglarg(temp.get("product_img_larg").toString());
			item.setRownum(rownum);
			list.add(item);
		}
		return list;
	}

	public static int get_count_product() throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select count(*) rownum from tb_product where isvisible=true";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		int rownum = 0;
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());

		return rownum;
	}

	public static int getQuantityProduct(String ID) {
		int quantity = 0;
		try {
			String query = "select quantity from tb_product where product_id='" + ID + "' and isvisible=true ";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

			Map<String, Object> temp = listob.get(0);
			quantity = Integer.parseInt(temp.get("quantity").toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return quantity;
	}

	public static ArrayList<ProductData> getSearchProduct(String name, String type, String fromprice, String toprice,
			int fromrow, int recordperpage) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "tb_product_seach";
			String[] pfield = { "p_name", "p_type", "p_fromprice", "p_toprice", "fromrow", "recordperpage" };
			Object[] pvalues = { name, type, fromprice, toprice, fromrow, recordperpage };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int Addrating(String productid, String star, String user, String branch) {
		int result = 0;
		try {
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String SQL = "SELECT * FROM tb_rating where email = '" + user + "' and product_id ='" + productid + "';";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(SQL);
			if (listob.size() > 0) {
				String query = "update tb_rating set Star = '" + star + "' where email = '" + user
						+ "' and product_id ='" + productid + "';";
				////System.out.println("queryUpdate:" + query);
				result = Integer.parseInt(con.ExecUpdate(query).get("result"));
			} else {
				if (branch.length() == 0) {
					branch = "dbvivmallt";
				}
				String query = "insert into tb_rating(Product_ID,Star,Email,branch) values('" + productid + "','" + star
						+ "','" + user + "','" + branch + "')";
				////System.out.println("queryInsert:" + query);

				result = Integer.parseInt(con.ExecUpdate(query).get("result"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}

	public static ArrayList<ProductData> getlistproductmanager(String name, String type, String fromprice,
			String toprice, int f_row, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "tb_product_manager";
			String[] pfield = { "p_name", "p_type", "p_fromprice", "p_toprice", "p_from", "p_record" };
			Object[] pvalues = { name, type, fromprice, toprice, f_row, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductquantity(temp.get("quantity").toString());
				item.setProducttype(temp.get("isvisible") == null ? "" : temp.get("isvisible").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setNewPrice(Float.parseFloat(temp.get("new_price_with_percent").toString()));
				item.setPricePercent(Integer.parseInt(temp.get("price_percent").toString()));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static int count_get_list_product_manager(String name, String type, String fromprice, String toprice) {
		int rs = 0;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "tb_product_manager_count";
			String[] pfield = { "p_name", "p_type", "p_fromprice", "p_toprice" };
			Object[] pvalues = { name, type, fromprice, toprice };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				rs = Integer.parseInt(temp.get("rownum").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int insertproduct(String action, String parProductid, String parProductname, String parProducttypeid,
			String parProductimage, String parProductdes, float parProductprice, String parProductproviderid,
			float parQuantity, String parMoreinformation, String parGuide, String parDesshort, String imglarg,
			String typeimglarg,String property,String color,String branch) {
		int _result = 0;
		try {
			String spname = "sp_insert_product";
			String[] pfield = { "p_type", "productid", "productname", "producttypeid", "productimage", "productdes",
					"productprice", "productproviderid", "quantity", "moreinformation", "p_guide", "desshort",
					"imglarg", "p_typeimglarg","p_property","p_color","p_branch" };
			Object[] pvalues = { action, parProductid, parProductname, parProducttypeid, parProductimage, parProductdes,
					parProductprice, parProductproviderid, parQuantity, parMoreinformation, parGuide, parDesshort,
					imglarg, typeimglarg ,property,color,branch};
			
			IConnectEJBS con = new IConnectEJBS();
			_result = Integer.parseInt(con.ExecBoFunction(spname, pfield, pvalues).get("result"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _result;
	}

	private static String replace_origin_html(String str_html) {
		return str_html.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"")
				.replace("&blink;", "'");
	}

	public static int insert_image_product_by_id(String productid, String image) {
		int result = 0;
		try {
			String query = null;
			query = "insert into tb_product_sub(product_id,product_image)";
			query += "values('" + productid + "','" + image + "')";
			IConnectEJBS con = new IConnectEJBS();
			result = Integer.parseInt(con.ExecUpdate(query).get("result"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static ArrayList<ProductData> getlistimagebyid(String productid) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String query = null;
			query = "select product_index,product_image from tb_product_sub where product_id = '" + productid + "'";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductIndex(Integer.parseInt(temp.get("product_index").toString()));
				item.setProductImage(temp.get("product_image").toString());
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int delete_image_product_by_id(String productid, String index) {
		int result = 0;
		try {
			String query = null;
			query = "delete from  tb_product_sub ";
			query += "where product_index = '" + index + "' and product_id='" + productid + "'";
			IConnectEJBS con = new IConnectEJBS();
			result = Integer.parseInt(con.ExecUpdate(query).get("result").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static int update_visible(String productid) {
		Map<String, Object> mapOfObjects = new HashMap<String, Object>();
		int result = 0;
		try {

			String spname = "sp_tbproduct_visisble";
			String[] pfield = { "f", "p_productid" };
			String[] ptype = { "INT", "VARCHAR" };
			Object[] pvalues = { "", productid };
			int[] pdirec = { 1, 0 };
			IConnectEJBS con = new IConnectEJBS();
			mapOfObjects = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalues, pdirec);
			if (mapOfObjects.size() > 0) {
				result = Integer.parseInt(mapOfObjects.get("f").toString());
			} else {
				result = -1;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}

	public static int change_num_order(String productid, int num) {
		int result = 0;
		try {
			String query = null;
			query = "update tb_product ";
			query += "set  orderproduct=" + num + ",istate=2,issyn = false where product_id = '" + productid + "'";
			IConnectEJBS con = new IConnectEJBS();
			result = Integer.parseInt(con.ExecUpdate(query).get("result").toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static ArrayList<ProductData> get_list_sell_best()
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT * FROM vw_product_sell_best;";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static String get_list_product_rand() throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT product_type_id,product_id,product_name,product_image,product_price"
				+ " FROM tb_product " + " where isvisible=true" + " ORDER BY RAND() LIMIT 5 ;";

		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int j = 0; j < listob.size(); j++) {
			Map<String, Object> temp = listob.get(j);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			item.setProducttype(temp.get("product_type_id").toString());
			list.add(item);
		}
		String json = "";
		json = new Gson().toJson(list);
		return json;
	}

	public static String get_list_product_popup3(String cate)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ProductType rs = new ProductType();
		rs.setProducttype(cate);

		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT product_type_id,product_id,product_name,product_image,product_price,product_img_larg,ispromo,product_price_2 "
				+ " FROM tb_product " + " where product_type_id=" + cate
				// +" and tb_product.product_id not in (select product_id from
				// tb_cate_promotion_product)"
				+ " and isvisible=true  LIMIT 0,7 ";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int j = 0; j < listob.size(); j++) {
			ProductData item = new ProductData();
			Map<String, Object> temp = listob.get(j);
			String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
			item.setProductId(strproductid);
			
			/*ShoppingCartItem item_temp = new ShoppingCartItem();
			item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
			item.setNewPrice(item_temp.getFinal_price());
			item.setPricePercent(item_temp.getDiscount_percent());
			item.setProductPrice(item_temp.getPrice());
			if (item.getPricePercent() > 0) {
				item.setIsPromo(1);
			} else {
				item.setIsPromo(0);
			}*/
			item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
			item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
			item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
			item.setProductName(temp.get("product_name").toString());
			item.setProductImage(temp.get("product_image").toString());
			item.setProducttype(cate);
			item.setProductimglarg(temp.get("product_img_larg").toString());
			list.add(item);
		}
		rs.setList(list);
		String json = "";
		json = new Gson().toJson(rs);
		////System.out.println(json);
		return json;
	}

	public static String get_list_product_popup2(String cateid)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();

		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();

		IConnectEJBS con = new IConnectEJBS();
		String[] pf = { "p_cateid" };
		Object[] pv = { cateid };
		listob = con.ExecBoFunctionReturnResutlSet("sp_get_product_saleoff", pf, pv);
		for (int j = 0; j < listob.size(); j++) {
			Map<String, Object> temp = listob.get(j);
			ProductData item = new ProductData();
			item.setProducttype(cateid);
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image") == null ? "" : temp.get("product_image").toString());
			item.setProducttype(temp.get("product_type_id").toString());
			item.setProductimglarg(temp.get("product_img_larg") == null ? "" : temp.get("product_img_larg").toString());
			item.setTypeimglarg(temp.get("type_img_larg") == null ? "" : temp.get("type_img_larg").toString());
			list.add(item);
		}
		String json = "";
		json = new Gson().toJson(list);
		return json;
	}

	private static List<Map<String, Object>> get_list_id_category() {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT product_type_id" + " FROM tb_product_type ";

		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		return listob;
	}

	public static ArrayList<ProductData> get_list_product_bycate(String cate)
			throws ClassNotFoundException, InstantiationException, SQLException {

		return getListProductCate(cate, 0, 10);
	}

	public static ArrayList<ProductData> getListProductCate(String cate, int fromrecord, int recordperpage)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String spname = "tb_product_seach_cate";
		String[] pfield = { "p_cate", "fromrow", "recordperpage" };
		Object[] pvalues = { cate, fromrecord, recordperpage };
		IConnectEJBS con = new IConnectEJBS();
		listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		int rownum = get_count_product_cate(cate);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			item.setProductimglarg(temp.get("product_img_larg").toString());
			item.setRownum(rownum);
			list.add(item);
		}
		return list;
	}

	public static int get_count_product_cate(String cate)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select count(*) rownum from tb_product where isvisible=true and product_type_id like '" + cate
				+ "'";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		int rownum = 0;
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());

		return rownum;
	}

	public static ArrayList<ProductData> get_list_top()
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_price,product_image from tb_product where isvisible=true order by orderproduct desc limit 0,9";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static ArrayList<ProductData> get_list_adv()
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_price,product_image from tb_product where isvisible=true order by orderproduct desc limit 0,10";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static ArrayList<ProductData> search_basic(String key, int fromrecord, int recordperpage)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String spname = "tb_product_seach_basic";
		String[] pfield = { "p_name", "fromrow", "recordperpage" };
		Object[] pvalues = { key, fromrecord, recordperpage };
		IConnectEJBS con = new IConnectEJBS();
		listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		int rownum = get_count_product_search_basic(key);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
			item.setProductId(strproductid);
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			item.setProductimglarg(temp.get("product_img_larg").toString());
			item.setRownum(rownum);
			list.add(item);
		}
		return list;
	}

	public static int get_count_product_search_basic(String key)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select count(*) rownum from tb_product1 where isvisible=true and product_name like '%" + key
				+ "%'";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		int rownum = 0;
		Map<String, Object> temp = listob.get(0);

		rownum = Integer.parseInt(temp.get("rownum").toString());

		return rownum;
	}

	public static ArrayList<ProductData> search_adv(String key, String cate, String fromprice, String toprice,
			int fromrecord, int recordperpage) throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String spname = "tb_product_seach";
		String[] pfield = { "p_name", "p_type", "p_fromprice", "p_toprice", "fromrow", "recordperpage" };
		Object[] pvalues = { key, cate, fromprice, toprice, fromrecord, recordperpage };
		IConnectEJBS con = new IConnectEJBS();
		listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		int rownum = count_search_adv(key, cate, fromprice, toprice);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			item.setProductimglarg(temp.get("product_img_larg").toString());
			item.setRownum(rownum);
			list.add(item);
		}
		return list;
	}

	public static int count_search_adv(String key, String cate, String fromprice, String toprice)
			throws ClassNotFoundException, InstantiationException, SQLException {

		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String spname = "count_tb_product_seach";
		String[] pfield = { "p_name", "p_type", "p_fromprice", "p_toprice" };
		Object[] pvalues = { key, cate, fromprice, toprice };
		IConnectEJBS con = new IConnectEJBS();
		listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		int rownum = 0;
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());

		return rownum;
	}

	public static ArrayList<ProductData> get_list_compare(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select tb1.product_id,tb1.product_name,tb1.product_price,tb1.product_image ";
		query += "from tb_product tb1,(select product_type_id from tb_product where product_id='" + id + "') tb2 ";
		query += "where  tb1.product_type_id = tb2.product_type_id  and tb1.product_id<>'" + id
				+ "' and tb1.isvisible=true  ";
		query += "limit 0,10";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static Map<String, Object> delete_product(String str) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbproduct_delete";
			String[] pfield = { "id", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> visible_product(String str) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbproduct_visible";
			String[] pfield = { "id", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> Unvisible_product(String str_writer) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbproduct_unvisible";
			String[] pfield = { "id", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str_writer, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> delete_img_product(String id, String str_img) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbproduct_sub_delete";
			String[] pfield = { "id", "str_img", "f"

			};
			String[] ptype = { "VARCHAR", "TEXT", "INT" };
			Object[] pvalue = { id, str_img, ""

			};
			int[] pdirec = { 0, 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static ArrayList<ProductData> get_list_product_manager_cate(String cate, int f_from, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "tb_product_manager_cate";
			String[] pfield = { "p_cate", "p_from", "p_record" };
			Object[] pvalues = { cate, f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductquantity(temp.get("quantity").toString());
				item.setProducttype(temp.get("isvisible").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setNewPrice(Float.parseFloat(temp.get("new_price_with_percent").toString()));
				item.setPricePercent(Integer.parseInt(temp.get("price_percent").toString()));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int count_get_list_product_manager_cate(String cate) {
		int rs = 0;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "tb_product_manager_cate_count";
			String[] pfield = { "p_cate" };
			Object[] pvalues = { cate };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				rs = Integer.parseInt(temp.get("rownum").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rs;
	}

	public static ArrayList<ProductData> get_list_new()
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_price,product_image from tb_product where isvisible=true order by product_input_date desc limit 0,10;";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
			item.setProductId(strproductid);
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static ArrayList<ProductData> get_list_product_related(String productid)
			throws ClassNotFoundException, InstantiationException, SQLException {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String spname = "get_product_related";
		String[] pf = { "p_productid" };
		Object[] pv = { productid };
		IConnectEJBS con = new IConnectEJBS();
		listob = con.ExecBoFunctionReturnResutlSet(spname, pf, pv);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			item.setProductName(temp.get("product_name").toString());
			item.setProductPrice(
					temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
			item.setProductImage(temp.get("product_image").toString());
			list.add(item);
		}
		return list;
	}

	public static ArrayList<ProductData> get_list_product_most_view(int f_from, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			int rownum = count_get_list_product_most_view();
			String spname = "sp_get_product_later";
			String[] pfield = { "p_from", "p_record" };
			Object[] pvalues = { f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				
				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if (item.getPricePercent() > 0) {
					item.setIsPromo(1);
				} else {
					item.setIsPromo(0);
				}*/
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				int len = temp.get("product_name").toString().length();
				String product_name = temp.get("product_name").toString();
				if (len > 15) {
					product_name = temp.get("product_name").toString().substring(0, 15) + "...";
				}

				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setProductquantity(temp.get("quantity").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setRownum(rownum);
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int count_get_list_product_most_view()
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int rownum = 0;
		String query = "select distinct count(product_id) rownum from tb_product_later";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());
		return rownum;
	}

	public static ArrayList<ProductData> get_list_product_most_buy(int f_from, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			int rownum = count_get_list_product_most_buy();
			String spname = "sp_product_most_buy";
			String quantity = "";
			String[] pfield = { "p_from", "p_record" };
			Object[] pvalues = { f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				
				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if (item.getPricePercent() > 0) {
					item.setIsPromo(1);
				} else {
					item.setIsPromo(0);
				}*/
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				int len = temp.get("product_name").toString().length();
				String product_name = temp.get("product_name").toString();
				if (len > 15) {
					product_name = temp.get("product_name").toString().substring(0, 15) + "...";
				}

				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setRownum(rownum);
				item.setProductquantity(get_quantity_most_buy(temp.get("product_id").toString()));
				list.add(item);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static int count_get_list_product_most_buy()
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int rownum = 0;
		String query = "select count(product_id) rownum "
				+ "from (select distinct tb1.product_id,tb2.istate from tb_order_detail tb1,tb_product tb2"
				+ " where tb1.product_id = tb2.product_id "
				+ " AND tb2.isvisible =true AND tb2.istate!=3) id";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());
		return rownum;
	}

	public static String get_quantity_most_buy(String id)
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String quantity = "";
		String query = "SELECT sum(quantity) as COUNT, product_id FROM tb_order_detail where product_id='" + id + "';";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		quantity = temp.get("COUNT").toString();
		return quantity;
	}

	public static ArrayList<ProductData> get_list_product_new(int f_from, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			int rownum = count_get_list_product_new();
			String spname = "sp_product_new";
			String quantity = "";
			String[] pfield = { "p_from", "p_record" };
			Object[] pvalues = { f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);

				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if (item.getPricePercent() > 0) {
					item.setIsPromo(1);
				} else {
					item.setIsPromo(0);
				}
				 */	
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				int len = temp.get("product_name").toString().length();
				String product_name = temp.get("product_name").toString();
				if (len > 15) {
					product_name = temp.get("product_name").toString().substring(0, 15) + "...";
				}

				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setRownum(rownum);
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int count_get_list_product_new() throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int rownum = 0;
		String query = "select  count(product_id) rownum from tb_product where isvisible=true ";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());
		return rownum;
	}

	public static ArrayList<ProductData> get_list_product_high_rating(int f_from, int record) {
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			int rownum = count_get_list_product_high_rating();
			String spname = "sp_product_high_rating";
			String quantity = "";
			String[] pfield = { "p_from", "p_record" };
			Object[] pvalues = { f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				int len = temp.get("product_name").toString().length();
				String product_name = temp.get("product_name").toString();
				if (len > 15) {
					product_name = temp.get("product_name").toString().substring(0, 15) + "...";
				}
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProductName(product_name);
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProducttype(temp.get("product_type_id").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setRownum(rownum);
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static int count_get_list_product_high_rating()
			throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int rownum = 0;
		String query = "select count(product_id) rownum FROM (SELECT product_id from tb_rating group by product_id) row";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());
		return rownum;
	}

	public static int save_history(ItemHistoryProduct item)
			throws ClassNotFoundException, InstantiationException, SQLException {

		try {
			String col = "colHistoryWProduct";
			String date = CommentDAL.get_current_date2();
			String time = CommentDAL.get_time();
			String[] pf = { "ip", "hostname", "city", "region", "country", "loc", "org", "user", "date", "time",
					"product_id" };
			Object[] pv = { item.getIp(), item.getHostname(), item.getCity(), item.getRegion(), item.getCountry(),
					item.getLoc(), item.getOrg(), item.getUser(), date, time, item.getProductid() };
			// role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);
			if (_rs == 0) {
				return 0;
			} else {
				return -1;
			}
		} catch (Exception ex) {
			return -1;
		}
	}

	public static int save_search_history(ItemHistorySearchProduct item)
			throws ClassNotFoundException, InstantiationException, SQLException {

		try {
			String col = "colSearchHistory";
			String date = CommentDAL.get_current_date2();
			String time = CommentDAL.get_time();
			String[] pf = { "ip", "hostname", "city", "region", "country", "loc", "org", "user", "date", "time",
					"keysearch" };
			Object[] pv = { item.getIp(), item.getHostname(), item.getCity(), item.getRegion(), item.getCountry(),
					item.getLoc(), item.getOrg(), item.getUser(), date, time, item.getKey() };
			// role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);
			if (_rs == 0) {
				return 0;
			} else {
				return -1;
			}
		} catch (Exception ex) {
			return -1;
		}
	}

	public static int save_search_advance_history(ItemHistorySearchAdvanceProduct item)
			throws ClassNotFoundException, InstantiationException, SQLException {

		try {
			String col = "colSearchHistory";
			String date = CommentDAL.get_current_date2();
			String time = CommentDAL.get_time();
			String[] pf = { "ip", "hostname", "city", "region", "country", "loc", "org", "user", "date", "time",
					"keysearch", "typeproduct", "fromprice", "toprice" };
			Object[] pv = { item.getIp(), item.getHostname(), item.getCity(), item.getRegion(), item.getCountry(),
					item.getLoc(), item.getOrg(), item.getUser(), date, time, item.getKey(), item.getType(),
					item.getFromprice(), item.getToprice() };
			// role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);
			if (_rs == 0) {
				return 0;
			} else {
				return -1;
			}
		} catch (Exception ex) {
			return -1;
		}
	}

	public static ArrayList<ProductData> get_product_comment(String email) {
		// get user_id
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select id from tb_member where email='" + email + "'";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		if (listob.size() > 0) {
			String user_id = listob.get(0).get("id").toString();
			// get list product comment
			DBObject matchFields = new BasicDBObject("id_cus", user_id);
			DBObject groupFields = new BasicDBObject("_id", "$product");
			AggregationOutput output = null;
			output = ConnectDBMongo.GroupByCollection("colCustomer", matchFields, groupFields);
			String product_arr = "";
			for (DBObject doc : output.results()) {
				product_arr += "'" + doc.get("_id") + "',";
			}
			if (product_arr.trim().length() > 0) {
				product_arr = product_arr.substring(0, product_arr.length() - 1);

				try {
					query = "SELECT a.product_id, a.product_name,a.product_price,a.product_des,a.product_image ";
					query += "FROM tb_product a ";
					query += "where a.product_id in(" + product_arr + ") and isvisible=true ";
					List<Map<String, Object>> listob_pro = new ArrayList<Map<String, Object>>();
					listob_pro = con.GetDataReturnResultSet(query);
					for (int i = 0; i < listob_pro.size(); i++) {
						Map<String, Object> temp = listob_pro.get(i);
						ProductData item = new ProductData();
						item.setProductId(temp.get("product_id").toString());
						item.setProductName(temp.get("product_name").toString());
						item.setProductPrice(temp.get("product_price") == null ? 0
								: Float.parseFloat(temp.get("product_price").toString()));
						item.setProductImage(temp.get("product_image").toString());
						item.setProductDes(temp.get("product_des").toString());
						list.add(item);
					}
				} catch (Exception ex) {

				}
			}
		}
		return list;
	}

	public static ArrayList<ProductData> get_product_recommend(String email, int fromrow, int recordperpage) {
		// get user_id
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		// get list product comment
		DBObject matchFields = new BasicDBObject("user", email);
		DBObject groupFields = new BasicDBObject("_id", "$product_id");
		AggregationOutput output = null;
		output = ConnectDBMongo.GroupByCollection("colHistoryWProduct", matchFields, groupFields);
		String product_arr = "";
		for (DBObject doc : output.results()) {
			product_arr += "'" + doc.get("_id") + "',";
		}
		String query = "";
		if (product_arr.trim().length() > 0) {
			product_arr = product_arr.substring(0, product_arr.length() - 1);

			try {
				int numrow = 0;
				query = "SELECT count(a.product_id) num_row ";
				query += "FROM tb_product a,(select product_type_id from tb_product where product_id in(" + product_arr
						+ ") group by product_type_id) b ";
				query += "where a.product_type_id=b.product_type_id and a.isvisible=true  ";
				List<Map<String, Object>> listob_pro = new ArrayList<Map<String, Object>>();
				listob_pro = con.GetDataReturnResultSet(query);
				numrow = Integer.parseInt(listob_pro.get(0).get("num_row").toString());
				////System.out.println(numrow);
				// get data
				query = "SELECT a.product_id, a.product_name,a.product_price,a.product_des,a.product_image,a.product_type_id,a.ispromo,a.product_price_2 ";
				query += "FROM tb_product a,(select product_type_id from tb_product where product_id in(" + product_arr
						+ ") group by product_type_id) b ";
				query += "where a.product_type_id=b.product_type_id and a.isvisible=true  limit " + fromrow + ","
						+ recordperpage + "";
				listob_pro = con.GetDataReturnResultSet(query);
				////System.out.println(query);
				for (int i = 0; i < listob_pro.size(); i++) {
					Map<String, Object> temp = listob_pro.get(i);
					ProductData item = new ProductData();
					String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
					item.setProductId(strproductid);
					
					/*ShoppingCartItem item_temp = new ShoppingCartItem();
					item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
					item.setNewPrice(item_temp.getFinal_price());
					item.setPricePercent(item_temp.getDiscount_percent());
					item.setProductPrice(item_temp.getPrice());
					if (item.getPricePercent() > 0) {
						item.setIsPromo(1);
					} else {
						item.setIsPromo(0);
					}*/
					item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
					item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
					item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
					item.setProducttype(temp.get("product_type_id").toString());
					int len = temp.get("product_name").toString().length();
					String product_name = temp.get("product_name").toString();
					if (len > 15) {
						product_name = temp.get("product_name").toString().substring(0, 15) + "...";
					}

					item.setProductName(product_name);
					item.setProductImage(temp.get("product_image").toString());
					item.setRownum(numrow);
					list.add(item);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public static ArrayList<ProductData> get_product_choose(int fromrow, int recordperpage) {
		// get user_id
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		// get list product comment
		DBObject groupFields = new BasicDBObject("_id", "$ID");
		AggregationOutput output = null;
		output = ConnectDBMongo.GroupByCollectionNoWhere("colHistoryShoppingcart", groupFields);
		String product_arr = "";
		for (DBObject doc : output.results()) {
			product_arr += "'" + doc.get("_id") + "',";
		}
		String query = "";
		if (product_arr.trim().length() > 0) {
			product_arr = product_arr.substring(0, product_arr.length() - 1);

			try {
				int numrow = 0;
				query = "SELECT count(a.product_id) num_row ";
				query += "FROM tb_product a ";
				query += "where product_id in(" + product_arr + ") and isvisible=true  ";
				List<Map<String, Object>> listob_pro = new ArrayList<Map<String, Object>>();
				listob_pro = con.GetDataReturnResultSet(query);
				numrow = Integer.parseInt(listob_pro.get(0).get("num_row").toString());
				// get data
				query = "SELECT a.product_id, a.product_name,a.product_price,a.product_des,a.product_image,a.product_type_id,a.ispromo,product_price_2 ";
				query += "FROM tb_product a ";
				query += "where product_id in(" + product_arr + ") and isvisible=true  limit " + fromrow + ","
						+ recordperpage + "";
				listob_pro = con.GetDataReturnResultSet(query);
				for (int i = 0; i < listob_pro.size(); i++) {
					Map<String, Object> temp = listob_pro.get(i);
					ProductData item = new ProductData();
					String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
					item.setProductId(strproductid);
					
					item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
					item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
					item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
					item.setProducttype(temp.get("product_type_id").toString());
					int len = temp.get("product_name").toString().length();
					String product_name = temp.get("product_name").toString();
					if (len > 15) {
						product_name = temp.get("product_name").toString().substring(0, 15) + "...";
					}

					item.setProductName(product_name);
					item.setProductImage(temp.get("product_image").toString());
					item.setRownum(numrow);
					list.add(item);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}

	public static ArrayList<ProductData> new_product(String email) {

		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		try {
			String query = "";
			query = "SELECT a.product_id, b.product_name,b.product_price,b.product_des,b.product_image ";
			query += "FROM tb_product_news a, tb_product b ";
			query += "where a.email = '" + email + "' and a.product_id = b.product_id and b.isvisible=true ";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductDes(temp.get("product_des").toString());
				list.add(item);
			}
		} catch (Exception ex) {

		}
		return list;

	}

	public static int cancel_new_product(String email, String product_id) {
		int rs = -1;
		IConnectEJBS con = new IConnectEJBS();
		try {
			String query = "";
			query = "SELECT * FROM tb_product_news where email = '" + email + "' and product_id='" + product_id + "'";
			////System.out.println(query);
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			listob = con.GetDataReturnResultSet(query);
			////System.out.println("Size: " + listob.size());
			if (listob.size() > 0) {
				query = "delete from tb_product_news where email = '" + email + "' and product_id='" + product_id + "'";

				Map<String, String> result = new HashMap<String, String>();
				result = con.ExecUpdate(query);
				rs = Integer.parseInt(result.get("result").toString());
			} else {
				rs = -2;
			}
		} catch (Exception ex) {

		}
		return rs;

	}

	public static String search_basic(String key, String p_fromrecode, String p_recordperpage, String product_type_id) {
		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		try {
			String query = "";
			query = "select product_id,product_name,product_image,product_price,product_type_id,quantity,isvisible,product_img_larg,ispromo,product_price_2"
					+ " from tb_product " + " where  product_name like '%" + key + "%' and isvisible=1 ";
			if (product_type_id != "" && product_type_id != null) {
				query += " and product_type_id=" + product_type_id;
			}
			query += " limit " + p_fromrecode + "," + p_recordperpage;

			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			int numpage = count_search_basic(key, product_type_id);
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductimglarg(temp.get("product_img_larg").toString());
				item.setRownum(numpage);
				list.add(item);
			}
		} catch (Exception ex) {

		}

		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public static int count_search_basic(String key, String product_type_id) {
		IConnectEJBS con = new IConnectEJBS();
		String query = "select count(product_id) as co from  tb_product " + " where  product_name like '%" + key
				+ "%' and isvisible=1 ";
		if (product_type_id != "" && product_type_id != null) {
			query += " and product_type_id=" + product_type_id;
		}
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		int temp2 = Integer.parseInt(temp.get("co").toString());

		return temp2;

	}

	public static String search_product_advand(String key, String cate, String fromprice, String toprice,
			String p_fromrecode, String p_recordperpage) {
		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		try {

			String query = "select product_id,product_name,product_image,product_price,product_type_id,quantity,isvisible,product_img_larg,ispromo,product_price_2 "
					+ " from tb_product " + " where  product_name like '%" + key + "%' and isvisible=1 ";
			if (cate != "" && cate != null) {
				query += " and product_type_id=" + cate;
			}
			query += " and product_price between " + fromprice + " and " + toprice + "";
			query += " limit " + p_fromrecode + "," + p_recordperpage;
			// List<Map<String, Object>> listob = new ArrayList<Map<String,
			// Object>>();
			// int numpage=count_search_advv(key,cate,fromprice,toprice);
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			int numpage = count_search_advv(key, cate, fromprice, toprice);
			;
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductimglarg(temp.get("product_img_larg").toString());
				item.setRownum(numpage);
				list.add(item);
			}

		} catch (Exception ex) {

		}
		// ////System.out.println("!!!!!!!!"+list.size());
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public static int count_search_advv(String key, String cate, String fromprice, String toprice) {
		IConnectEJBS con = new IConnectEJBS();
		String query = "select  count(product_id) as co " + " from tb_product " + " where  product_name like '%" + key
				+ "%' and isvisible=1 ";
		if (cate != "" && cate != null) {
			query += " and product_type_id=" + cate;
		}
		query += " and product_price between " + fromprice + " and " + toprice + "";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		int temp2 = Integer.parseInt(temp.get("co").toString());
		// ////System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+temp2);

		return temp2;
	}

	

	public static String get_product_cate(String cate, String fromrecode, String recordperpage)
			throws ClassNotFoundException, InstantiationException, SQLException {
		int rownum = count_product_cate(cate);
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "select product_id,product_name,product_image,product_price,product_type_id,quantity,isvisible,product_img_larg,ispromo,product_price_2 ";
		query += " from tb_product";
		query += " where product_type_id = '" + cate + "'";
		query += " and isvisible='1' ";
		query += " LIMIT " + fromrecode + "," + recordperpage + ";";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		for (int i = 0; i < listob.size(); i++) {
			Map<String, Object> temp = listob.get(i);
			ProductData item = new ProductData();
			item.setProductId(temp.get("product_id").toString());
			
			/*ShoppingCartItem item_temp = new ShoppingCartItem();
			item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
			item.setNewPrice(item_temp.getFinal_price());
			item.setPricePercent(item_temp.getDiscount_percent());
			item.setProductPrice(item_temp.getPrice());
			if (item.getPricePercent() > 0) {
				item.setIsPromo(1);
			} else {
				item.setIsPromo(0);
			}*/
			item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
			item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
			item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
			item.setProducttype(temp.get("product_type_id").toString());
			int len = temp.get("product_name").toString().length();
			String product_name = temp.get("product_name").toString();
			if (len > 15) {
				product_name = temp.get("product_name").toString().substring(0, 15) + "...";
			}

			item.setProductName(product_name);
			item.setProductImage(temp.get("product_image").toString());
			item.setProductimglarg(temp.get("product_img_larg").toString());
			item.setRownum(rownum);
			list.add(item);
		}
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	public static int count_product_cate(String cate){
		int rownum=0;
		
		try{
			String query="";
			query+="SELECT COUNT(product_id) rownum ";
			query+=" FROM tb_product ";
			query+=" WHERE product_type_id='"+cate+"' ";
			query+=" AND isvisible = 1 ";
			query+=" AND istate !=3";
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			listob = con.GetDataReturnResultSet(query);
			Map<String, Object> temp = listob.get(0);
			rownum = Integer.parseInt(temp.get("rownum").toString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return rownum;
	}

	public static String search_group(String p_fromrecode, String p_recordperpage) {

		ArrayList<String> liststr = new ArrayList<String>();
		int rownum = count_search_result();
		DBObject groupFields = new BasicDBObject("_id", "$keysearch");
		AggregationOutput output = null;
		output = ConnectDBMongo.GroupByCollectionNoWhere("colSearchHistory", groupFields);
		for (DBObject doc : output.results()) {
			if (doc.get("_id").toString().length() > 0) {
				liststr.add(doc.get("_id").toString());
			}
		}
		List<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String result = "";
		String query = "";
		query = "select product_index, product_id,product_name,product_image,product_price,product_type_id,product_img_larg,isvisible,ispromo,product_price_2 ";
		query += " from tb_product ";
		query += "where isvisible='1' and ( 0 = 1 ";
		for (String str : liststr) {
			query += " or product_name like '%" + str + "%'";
		}
		query += ")";
		query += " limit " + p_fromrecode + "," + p_recordperpage;
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		if (listob.size() > 0) {
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				
				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp = ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if (item.getPricePercent() > 0) {
					item.setIsPromo(1);
				} else {
					item.setIsPromo(0);
				}*/
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				int len = temp.get("product_name").toString().length();
				String product_name = temp.get("product_name").toString();
				if (len > 15) {
					product_name = temp.get("product_name").toString().substring(0, 15) + "...";
				}

				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setProductimglarg(temp.get("product_img_larg").toString());
				item.setRownum(rownum);
				list.add(item);
			}
			result = new Gson().toJson(list);
		} else {
			result = "";
		}

		return result;
	}
	public static int count_search_result(){
		int rownum = 0;
		ArrayList<String> liststr = new ArrayList<String>();

		DBObject groupFields = new BasicDBObject("_id", "$keysearch");
		AggregationOutput output = null;
		output = ConnectDBMongo.GroupByCollectionNoWhere("colSearchHistory", groupFields);
		for (DBObject doc : output.results()) {
			if (doc.get("_id").toString().length() > 0) {
				liststr.add(doc.get("_id").toString());
			}
		}
		
		String result = "";
		String query = "";
		query = "select COUNT(product_id) rownum,product_name,isvisible";
		query += " from tb_product ";
		query += "where isvisible='1' and ( 0 = 1 ";
		for (String str : liststr) {
			query += " or product_name like '%" + str + "%'";
		}
		query += ")";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		rownum = Integer.parseInt(listob.get(0).get("rownum").toString());
		return rownum;
	}
	public static ArrayList<ProductData> get_list_product_promotion(
				String cate,String promotion, int f_from, int record) 
	{
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "get_product_promotion";
			String[] pfield = { "p_cate","p_promotion", "p_from", "p_record" };
			Object[] pvalues = { cate,promotion, f_from, record };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(
						temp.get("product_price") == null ? 0 : Float.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());
				item.setProductquantity(temp.get("quantity").toString());
				item.setProducttype(temp.get("isvisible").toString());
				item.setOrderproduct(temp.get("orderproduct").toString());
				item.setNewPrice(Float.parseFloat(temp.get("new_price_with_percent").toString()));
				item.setPricePercent(Integer.parseInt(temp.get("price_percent").toString()));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}
	public static int count_get_list_product_promotion(String cate,String promotion) {
		int rs = 0;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "get_product_promotion_count";
			String[] pfield = { "p_cate","p_promotion"};
			Object[] pvalues = { cate,promotion};
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				rs = Integer.parseInt(temp.get("rownum").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rs;
	}
}
