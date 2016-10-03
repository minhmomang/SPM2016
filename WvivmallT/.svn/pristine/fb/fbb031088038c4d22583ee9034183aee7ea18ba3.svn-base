package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import EJB.IConnectEJBS;
import Helper.EncrypterDecrypter;
import Helper.Extra;
import Model.ShoppingCartItem;
import Model.Product.ProductData;


public class SearchDAL {
	public static String get_search_key()
			throws ClassNotFoundException, InstantiationException, SQLException {
		
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT id,tag FROM tb_1_re_tag_product;";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		String json = new Gson().toJson(listob);
		////System.out.println("get_search_key: "+json);
		return json;
	}

	public static String get_product(
			String customerID,
			String groupID,
			String tagID,
			String tagType
			)
			
			throws ClassNotFoundException, InstantiationException, SQLException {
		String result = "-1";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
//		String query = "SELECT rtp.product_id_list FROM tb_1_re_customer_group rcg, " +
//				"tb_1_re_group_tag rgt,tb_1_re_tag_product rtp " +
//				"Where rcg.customerid = 1 " +
//				"and rcg.groupid = rgt.groupid " +
//				"and rgt.tagid = rtp.id " +
//				"group by rtp.tag ;";
		String query = "SELECT GROUP_CONCAT(DISTINCT rtp.product_id_list SEPARATOR '') list " +
				"FROM tb_1_re_customer_group rcg, tb_1_re_group_tag rgt,tb_1_re_tag_product rtp " +
				"Where rcg.customerid like 1 " +
				"and rcg.groupid = rgt.groupid " +
				"and rgt.tagid = rtp.id; ";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		if(listob==null){
			return result;
		}
		String json = new Gson().toJson(listob);
		result = json;
		////System.out.println("product_id_list: "+json);
		return result;
	}
	public static String get_product() throws ClassNotFoundException, InstantiationException, SQLException{

			return get_product("","","","");
		
		
	}
	
	public static String get_list_product_bycate(String cate,int from,int to)
			throws ClassNotFoundException, InstantiationException, SQLException {
		
		
		ArrayList<String> list = new ArrayList<String>();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		String query = "SELECT product_id"
				+ " FROM tb_product "
				+ " where product_type_id="
				+ cate
				+ " and isvisible=true  LIMIT "+from+","+to+" ";
		IConnectEJBS con = new IConnectEJBS();
		////System.out.println(query.toString());
		listob = con.GetDataReturnResultSet(query);
		for (int j = 0; j < listob.size();j++) {
			ProductData item =new ProductData();		
			Map<String, Object> temp = listob.get(j);			
			
			list.add(temp.get("product_id").toString());
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (String s : list)
		{
		    sb.append(s);
		    sb.append(",");
		}

		return sb.toString();
	}
	public static String get_list_product_bycate(String cate) throws ClassNotFoundException,
	InstantiationException, SQLException {
		String str = get_list_product_bycate(cate,0,10);
		int result = update_product_id_list_bytag(str);
		////System.out.println(result);
		return str;
	}
	public static int update_product_id_list_bytag(String product_id_list) {
		int _result = 0;
		try {
//			String spname = "sp_insert_product_list_bytag";
//			String[] pfield = { "product_id_list" };
//			Object[] pvalues = { product_id_list };
			String query = "update tb_product_list_bytag "+
					"set product_id_list ='"+ product_id_list +"' "+
					
					"where id = 1;";
			////System.out.println(query);
			IConnectEJBS con = new IConnectEJBS();
			_result = Integer.parseInt(con.ExecUpdate(query).get("result"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _result;
	}
	
	
	
	public static String get_product_by_old(String from_old,  String to_old) throws NullPointerException {
		String result = "";
		try {
			//////System.out.println("get_product_by_old");
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String query = "SELECT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg " +
					"from tb_order o, tb_member m,tb_order_detail od, tb_product p " +
					" where  o.email = m.email " +
					"and o.order_id = od.order_id " +
					"and p.product_id = od.product_id and p.isvisible=true " +
					"group by p.product_id;";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			if(listob==null){
				return result;
			}
			String json = new Gson().toJson(listob);
			result = json;
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static String get_product_by_gender(String gender) {
		String result = "";
		try {
			//////System.out.println("get_product_by_gender");
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String query = "SELECT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg " +
					"from tb_order o, tb_member m,tb_order_detail od, tb_product p " +
					" where  o.email = m.email " +
					"and m.gender = '" + gender + "' "+
					"and o.order_id = od.order_id " +
					"and p.product_id = od.product_id and p.isvisible=true " +
					"group by p.product_id;";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			if(listob==null){
				return result;
			}
			String json = new Gson().toJson(listob);
			result = json;
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return result;
	}
	public static ArrayList<ProductData> get_product_by_old1(String from_old,  String to_old) throws NullPointerException {
		ArrayList<ProductData> result = new ArrayList<ProductData>();
		try {
			//////System.out.println("get_product_by_old");
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String query = "SELECT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg,ispromo,product_price_2 " +
					"from tb_order o, tb_member m,tb_order_detail od, tb_product p " +
					" where  o.email = m.email " +
					"and o.order_id = od.order_id " +
					"and p.product_id = od.product_id and p.isvisible=true " +
					"group by p.product_id;";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			if(listob==null){
				return result;
			}
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				
				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp=ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if(item.getPricePercent()>0){
					item.setIsPromo(1);
				}
				else{
					item.setIsPromo(0);
				}*/
				
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				int len = temp.get("product_name").toString().length();
				String product_name =temp.get("product_name").toString();
				if(len > 15){
					product_name = temp.get("product_name").toString().substring(0,15)+"...";
				}
				
				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setProductimglarg(temp.get("product_img_larg").toString());
				result.add(item);
			}
			
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static  ArrayList<ProductData> get_product_by_gender1(String gender) {
		ArrayList<ProductData> result = new ArrayList<ProductData>();
		try {
			//////System.out.println("get_product_by_gender");
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String query = "SELECT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg,ispromo,product_price_2 " +
					"from tb_order o, tb_member m,tb_order_detail od, tb_product p " +
					" where  o.email = m.email " +
					"and m.gender = '" + gender + "' "+
					"and o.order_id = od.order_id " +
					"and p.product_id = od.product_id and p.isvisible=true " +
					"group by p.product_id;";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			if(listob==null){
				return result;
			}
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
				item.setProductId(strproductid);
				
				/*ShoppingCartItem item_temp = new ShoppingCartItem();
				item_temp=ShoppingCartDAL.get_price_product_item(temp.get("product_id").toString());
				item.setNewPrice(item_temp.getFinal_price());
				item.setPricePercent(item_temp.getDiscount_percent());
				item.setProductPrice(item_temp.getPrice());
				if(item.getPricePercent()>0){
					item.setIsPromo(1);
				}
				else{
					item.setIsPromo(0);
				}
*/				
				item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
				item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
				item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
				item.setProducttype(temp.get("product_type_id").toString());
				int len = temp.get("product_name").toString().length();
				String product_name =temp.get("product_name").toString();
				if(len > 15){
					product_name = temp.get("product_name").toString().substring(0,15)+"...";
				}
				
				item.setProductName(product_name);
				item.setProductImage(temp.get("product_image").toString());
				item.setProductimglarg(temp.get("product_img_larg").toString());
				result.add(item);
			}
			
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return result;
	}
	public static String get_product_by_profile(String gender,String from_old, String to_old) {
		String result = "";
		try {
			String json =  get_product_by_gender(gender)+"/n"+ get_product_by_old(from_old,  to_old);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static  String get_favourite_product_in_date(String type, String d, int f_row,int record) throws ClassNotFoundException, InstantiationException, SQLException {
		String result = "";
		int rownum = count_get_list_favorite_in_date(type);
		try {
			//////System.out.println("get_product_by_gender");
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			String query = "SELECT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg,p.ispromo,product_price_2 " +
					"from tb_order o, tb_order_detail od, tb_product p " +
					"where  0=0 " +
					"and 	case '"+type+"' " +
						"when '1' then date(order_date) = curdate() " +
						"when '2' then month(order_date) = month(curdate()) " +
						"when '3' then year(order_date) = year(curdate()) " +
						" end  " +
					"and o.order_id = od.order_id " +
					"and p.product_id = od.product_id and p.isvisible=true " +
					"group by p.product_id LIMIT "+f_row+","+record+";";
			////System.out.println(query);
			ArrayList<ProductData> list = new ArrayList<ProductData>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			if(listob==null||listob.size()==0){
				return result;
			}
			else{
				for (int i = 0; i < listob.size(); i++) {
					Map<String, Object> temp = listob.get(i);
					ProductData item = new ProductData();
					String strproductid = EncrypterDecrypter.encodeString(Extra.IDShop+"-"+temp.get("product_id").toString());
					item.setProductId(strproductid);
					item.setPrice_old(Float.parseFloat(temp.get("product_price_2").toString()));
					item.setProductPrice(Float.parseFloat(temp.get("product_price").toString()));
					item.setIsPromo(Integer.parseInt(temp.get("ispromo").toString()));
					item.setProducttype(temp.get("product_type_id").toString());
					int len = temp.get("product_name").toString().length();
					String product_name =temp.get("product_name").toString();
					if(len > 15){
						product_name = temp.get("product_name").toString().substring(0,15)+"...";
					}
					
					item.setProductName(product_name);
					item.setProductImage(temp.get("product_image").toString());
					item.setRownum(rownum);
					list.add(item);
				}
			}
			result = new Gson().toJson(list);
		} catch (Exception ex) {
			ex.printStackTrace();
			result="";
		} 
		////System.out.println(result);
		return result;
	}
	public static int count_get_list_favorite_in_date(String type) throws ClassNotFoundException, InstantiationException, SQLException {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		int rownum = 0;
		String query = "SELECT COUNT(product_id) rownum FROM (SELECT DISTINCT p.product_type_id,p.product_id,p.product_name,p.product_image,product_price,product_img_larg,p.ispromo,product_price_2 " +
				"from tb_order o, tb_order_detail od, tb_product p " +
				"where  0=0 " +
				"and 	case '"+type+"' " +
					"when '1' then date(order_date) = curdate() " +
					"when '2' then month(order_date) = month(curdate()) " +
					"when '3' then year(order_date) = year(curdate()) " +
					" end  " +
				"and o.order_id = od.order_id " +
				"and p.product_id = od.product_id and p.isvisible=true) " +
				" result";
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		Map<String, Object> temp = listob.get(0);
		rownum = Integer.parseInt(temp.get("rownum").toString());
		return rownum;
	}
	
	
}
