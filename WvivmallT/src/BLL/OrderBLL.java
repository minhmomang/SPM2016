package BLL;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import DAL.ErrorDAL;
import DAL.OrderDAL;
import DAL.ProductDAL;
import EJB.ConnectDBD;
import EJB.IConnectEJBS;
import Helper.ErrorMesage;
import Helper.Extra;
import Helper.responseUtf8;
import Model.ItemOrder;
import Model.ModelOrderNL;
import Model.Order;
import Model.OrderDetail;
import Model.Product.ProductData;
import Model.ShoppingCartItem;
import Service.ReadServiceMail;

import java.net.URLEncoder;

public class OrderBLL {
	public static String check_status_order_send_logistic(String order) {
		int n = OrderDAL.check_status_order_send_logistic(order);

		String mes = "";
		if (n != 1) {
			switch (n) {
			case 0:
				mes = ErrorDAL.getMesageError(9);
				break;
			case -1:
				mes = ErrorDAL.getMesageError(5);
				break;
			case 2:
				mes = ErrorDAL.getMesageError(7);
				break;
			case 3:
				mes = ErrorDAL.getMesageError(8);
				break;
			default:
				break;
			}
		}
		////System.out.println(mes);
		Map<String, String> obj = new HashMap<String, String>();
		obj.put("result", String.valueOf(n));
		obj.put("message", mes);
		String json = new Gson().toJson(obj);
		return json;

	}

	public static Map<String, String> insert_order(String str, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException,
			InstantiationException, SQLException {
		ObjectMapper mapper = new ObjectMapper();
		ItemOrder data = new ItemOrder();
		data = mapper.readValue(str, ItemOrder.class);
		Map<String, String> objrs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		try {
			//
			ArrayList<ShoppingCartItem> list = new ArrayList<ShoppingCartItem>();
			list = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
			if (session.getAttribute("cart") == null || session.getAttribute("cart") == "") {
				objrs.put("result", "-2");
				return objrs;
			}
			if (list.size() == 0) {
				objrs.put("result", "-2");
				return objrs;
			} else {
				//
				Connection conn = ConnectDBD.getConnection();
				conn.setAutoCommit(false);
				String branch = "";
				if (session.getAttribute("branch") != null) {
					branch = session.getAttribute("branch").toString();
				}
				// check payment
				Boolean payment_exists = OrderDAL.check_payment(data.getPayment());
				if (payment_exists == false) {
					objrs.put("result", "-4");
					return objrs;
				}
				//
				String result = OrderDAL.OrderInsert(conn, data.getFullname(), data.getEmail(), data.getPhone(),
						data.getAddress(), data.getAddress2(), data.getDelivery(), data.getPayment(),
						Integer.parseInt(data.getInvoice()), data.getNotes(), branch);
				String[] arr_result = result.split("_");
				String error = arr_result[1];

				if (error.equals("0")) {

					// default: success
					int check = 0;
					for (ShoppingCartItem item : list) {
						int errorinsert = OrderDAL.OrderDetailInsert(conn, item.getID(), item.getQuantity(),
								item.getPrice(), item.getQuantity() * item.getPrice(), arr_result[0]);
						if (errorinsert != 0) {
							check = -1;
							break;
						}
					}
					if (check == 0) {
						session.setAttribute("orderid", arr_result[0]);
						conn.commit();
						objrs.put("result", "0");
						//
						String order_code = arr_result[0];
						if (data.getPayment().equals("NL")) {

							ModelOrderNL item_info = OrderDAL.get_info_order_sendto_nl(order_code);
							String url = build_ngan_luong(order_code, item_info.getPrice(), item_info.getQuantity());
							objrs.put("url", url);
							objrs.put("payment", data.getPayment());
						}
						objrs.put("order_id",order_code);
						int rs_send_mail = send_mail_to_customer(order_code, request);
						// remove session
						remove_shopping_cart(request);
						// build
						return objrs;
					} else {
						// delte table order
						objrs.put("result", "-3");
						conn.rollback();
						return objrs;
					}
				} else {
					conn.rollback();
					objrs.put("result", "-1");
					return objrs;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return objrs;
		}

	}

	

	public static String build_url_nganluong(String return_url, String receiver, String transaction_info,
			String order_code, String price, String currency, float quantity, float tax, float discount, float fee_cal,
			float fee_shipping, String order_description, String buyer_info, String affiliate_code) {
		String secure_code = Extra.merchant_site_code + " " + return_url.toLowerCase() + " " + receiver;
		secure_code += " " + transaction_info + " " + order_code + " " + price;
		secure_code += " " + currency + " " + quantity + " " + tax;
		secure_code += " " + discount + " " + fee_cal + " " + fee_shipping;
		secure_code += " " + order_description + " " + buyer_info + " " + affiliate_code;
		secure_code += " " + Extra.secure_pass;
		secure_code = secure_code.trim();
		String md5_secure_code = getMD5(secure_code);
		String urlnl = Extra.nganluong_url;
		String param = "merchant_site_code=" + Extra.merchant_site_code;
		param += "&return_url=" + URLEncoder.encode(return_url.toLowerCase());
		param += "&receiver=" + URLEncoder.encode(receiver);
		param += "&transaction_info=" + URLEncoder.encode(transaction_info);
		param += "&order_code=" + URLEncoder.encode(order_code);
		param += "&price=" + URLEncoder.encode(price);
		param += "&currency=" + URLEncoder.encode(currency);
		param += "&quantity=" + URLEncoder.encode(String.valueOf(quantity));
		param += "&tax=" + URLEncoder.encode(String.valueOf(tax));
		param += "&discount=" + URLEncoder.encode(String.valueOf(discount));
		param += "&fee_cal=" + URLEncoder.encode(String.valueOf(fee_cal));
		param += "&fee_shipping=" + URLEncoder.encode(String.valueOf(fee_shipping));
		param += "&order_description=" + URLEncoder.encode(order_description);
		param += "&buyer_info=" + URLEncoder.encode(buyer_info);
		param += "&affiliate_code=" + URLEncoder.encode(affiliate_code);
		param += "&secure_code=" + URLEncoder.encode(md5_secure_code);
		param += "&cancel_url=" + URLEncoder.encode(OrderDAL.get_cancel_url());
		urlnl += param;
		urlnl = urlnl.trim();
		return urlnl;
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String build_ngan_luong(String order_code, String p_price, float p_quantitty) {
		String return_url = OrderDAL.get_return_url() + "?id=" + order_code;
		String receiver = OrderDAL.get_receive();
		String transaction_info = "Thong+tin+giao+dich";
		String price = p_price;
		String currency = "vnd";
		float quantity = p_quantitty;
		float tax = 0;
		float discount = 0;
		float fee_cal = 0;
		float fee_shipping = 0;
		String order_description = "";
		String buyer_info = buyer_info(order_code);
		String affiliate_code = "";
		return build_url_nganluong(return_url, receiver, transaction_info, order_code, price, currency, quantity, tax,
				discount, fee_cal, fee_shipping, order_description, buyer_info, affiliate_code);
	}

	public static String buyer_info(String order) {
		String buyer_info = "";
		try {
			String query = "select tb2.fullname, tb2.email,tb2.phone,tb1.address_delivery ";
			query += "from tb_order tb1, tb_member tb2 ";
			query += "where tb1.email = tb2.email ";
			query += "and order_id ='" + order + "'";
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			listob = con.GetDataReturnResultSet(query);
			String fullname = "A";
			String email = "A";
			String phone = "A";
			String address_delivery = "A";
//			for (Map<String, Object> item : listob) {
//				fullname = item.get("fullname").toString().trim();
//				email = item.get("email").toString().trim();
//				phone = item.get("phone").toString().trim();
//				address_delivery = item.get("address_delivery").toString().trim();
//				break;
//			}
			buyer_info = fullname + "*|*" + email + "*|*" + phone + "*|*" + address_delivery;
			////System.out.println(buyer_info);
		} catch (Exception ex) {
		}
		return buyer_info;
	}

	public static int send_mail_to_customer(String order_id, HttpServletRequest request) {
		try {
			ServletContext servl = request.getServletContext();
			String urltext = request.getRealPath("/upload/txt");
			urltext += "/order.txt";
			String content_text = Extra.readFile(urltext);
			// get info order
			Order item_order = OrderDAL.getInfoOrder(order_id);
			//
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date order_date = null;
			try {
				order_date = dt.parse(item_order.getOrderDate().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content_text = content_text.replace("@orderid", order_id);
			content_text = content_text.replace("@date_order", dt.format(order_date));
			content_text = content_text.replace("@customer_name", item_order.getCustomername());
			content_text = content_text.replace("@customer_email",
					order_id.equals(item_order.getEmail()) ? "NO" : item_order.getEmail());
			content_text = content_text.replace("@phone", item_order.getPhone());
			content_text = content_text.replace("@customer_address", item_order.getAddress_delivery());
			content_text = content_text.replace("@phone", item_order.getPhone());
			content_text = content_text.replace(" @customer_payment", item_order.getPayment_method());
			ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
			list = OrderDAL.get_orderdetail_by_id(order_id);
			String order_content = "";
			float total_amount = 0;
			for (OrderDetail item : list) {
				String row = "<tr>";
				row += "<td>" + item.getProduct_name() + "</td><td>"
						+ Extra.format_currency(String.valueOf(item.getPrice())) + " VND </td><td>" + item.getQuantity()
						+ "</td><td>0</td><td>" + Extra.format_currency(String.valueOf(item.getAmount())) + " VND</td>";
				row += "</tr>";
				order_content += row;
				total_amount += item.getAmount();
			}
			content_text = content_text.replace("@content_order", order_content);
			content_text = content_text.replace("@customer_amount",
					Extra.format_currency(String.valueOf(total_amount)) + " VND");
			//
			String title = "Đơn hàng #" + order_id + " đã sẵn sàng để giao đến quý khách";
			if(!item_order.getEmail().equals(order_id)){
				int rs = ReadServiceMail.SendingFromgmail(item_order.getEmail(), title, content_text, request);
				return rs;
			}
			return 0;			
			
		} catch (Exception ex) {
		}
		return 1;
	}

	public static void remove_shopping_cart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<ShoppingCartItem> list = new ArrayList<ShoppingCartItem>();
		session.setAttribute("cart", list);
	}
	public static void main(String[] args) {
		ModelOrderNL item_info = OrderDAL.get_info_order_sendto_nl("OD1607280000000111");
		String url = build_ngan_luong("OD1607280000000111", item_info.getPrice(), item_info.getQuantity());
		////System.out.println(url);
	}

	public static String get_info_chart_report(String pdate) {
		String result="";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = OrderDAL.get_info_chart_report(pdate);
		result = new Gson().toJson(list);
		return result;
	}

	public static String count_order(String pdate) {
		String result="";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = OrderDAL.count_order(pdate);
		
		result= new Gson().toJson(list);
		
		return result;
	}

	public static String get_info_report_product(String pdate) {
		String result="";
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		list = OrderDAL.get_info_report_product(pdate);
		result= new Gson().toJson(list);
		return result;
	}

	public static String get_report_period(String fdate, String tdate) {
		String result="";
		List<Map<String,Object>> listob = new ArrayList<Map<String,Object>>();
		listob = OrderDAL.get_report_period(fdate,tdate);	
		result = new Gson().toJson(listob);
	
		return result;
	}

	public static String get_report_year(String fdate, String tdate) {
		String result="";
		List<Map<String,Object>> listob = new ArrayList<Map<String,Object>>();
		listob = OrderDAL.get_report_year(fdate,tdate);	
		result = new Gson().toJson(listob);
	
		return result;
	}

	public static String search_order(String order_id, String date) {
		String result="";
		ArrayList<Order> list = new ArrayList<Order>();
		list = OrderDAL.search_order(order_id,date);
		result = new Gson().toJson(list);
		return result;
	}

}
