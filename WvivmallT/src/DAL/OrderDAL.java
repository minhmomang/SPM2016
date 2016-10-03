package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import EJB.ConnectDBD;
import EJB.IConnectEJBS;
import Model.ModelOrderNL;
import Model.ModelPO;
import Model.ModelPODTL;
import Model.Order;
import Model.OrderDetail;
import Model.Product.ProductData;

public class OrderDAL {
	public static String OrderInsert(Connection conn,String fullname, String email,
			String phone, String address, String address2, String delivery,
			String payment, int invoice, String notes,String branch) {
		String result = "";
			String orderid = "";
			int error = -2;
			String spname = "tb_order_insert";
			String[] pfield = { "fullname", "email", "phone", "address",
					"address2", "delivery", "payment", "invoice", "notes",
					"re_branch","orderid", "f" };
			String[] ptype = { "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR",
					"VARCHAR", "CHAR", "CHAR", "INT", "VARCHAR", 
					"VARCHAR","VARCHAR","INT" };
			Object[] pvalues = { fullname, email, phone, address, 
					address2,delivery, payment, invoice, notes, 
					branch,orderid, error };
			int[] direc = { 0, 0, 0, 0, 
					0, 0, 0, 0, 0,
					0,1, 1 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			IConnectEJBS con = new IConnectEJBS();
			try {
			mapOfObjects = con.ExecBoFunctionReturnList(conn,spname, pfield, ptype,
					pvalues, direc);
			error = Integer.parseInt(mapOfObjects.get("f").toString());
			orderid = mapOfObjects.get("orderid") == null ? "" : mapOfObjects
					.get("orderid").toString();
			////System.out.println("AAA: "+error);
			if (error == 0) {
				result = orderid + "_" + error;
			} else {
				result = "A_-1";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// -1: error
			result = "A_-2";
		}
		return result;
	}

	public static int OrderDetailInsert(Connection conn,String productid, double quantity,
			double price, double amount, String orderid) {
		int result = 0;
		try {
			int error = -2;
			String spname = "tb_order_detail_insert";
			String[] pfield = { "pro_id", "quan", "price", "amount",
					"order_id", "f" };
			String[] ptype = { "VARCHAR", "INT", "FLOAT", "FLOAT", "VARCHAR",
					"INT" };
			Object[] pvalues = { productid, quantity, price, amount, orderid,
					error };
			int[] direc = { 0, 0, 0, 0, 0, 1 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			IConnectEJBS con = new IConnectEJBS();
			mapOfObjects = con.ExecBoFunctionReturnList(conn,spname, pfield, ptype,
					pvalues, direc);
			if(mapOfObjects.size()>0){
				error = Integer.parseInt(mapOfObjects.get("f").toString());	
			}
			
			if (error !=0) {
				result = -1;
			} else {
				result = 0;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		return result;
	}

	public static void orderdetaildelete(String productid, String orderid) {
		String query = "delete tb_order_detail where product_id='" + productid
				+ "' and order_id ='" + orderid + "'";
		IConnectEJBS con = new IConnectEJBS();
		con.ExecUpdate(query);
	}

	public static void orderdelete(String orderid) {
		String query = "delete tb_order where order_id ='" + orderid + "'";
		IConnectEJBS con = new IConnectEJBS();
		con.ExecUpdate(query);
	}

	public static Order getInfoOrder(String orderid) {
		Order _order = new Order();
		try {
			String spname = "tb_order_get";
			String[] pfield = { "orderid" };
			Object[] pvalues = { orderid };
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			Map<String, Object> temp = listob.get(0);
			_order.setEmail(temp.get("email").toString());
			_order.setCustomername(temp.get("fullname").toString());
			_order.setPhone(temp.get("phone").toString());
			_order.setAddress_delivery(temp.get("address_delivery").toString());
			_order.setPayment_method(temp.get("payment_name").toString());			
			_order.setOrderDate(temp.get("order_date").toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return _order;
	}

	public static ArrayList<Order> get_order_manager() {
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			String spname = "get_order";
			String[] pfield = { "fromdate", "todate", "order_status" };
			Object[] pvalues = { "", "", "" };
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			////System.out.println(new Gson().toJson(listob));
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				Order item = new Order();
				item.setOrderId(temp.get("order_id").toString());
				item.setCustomername(temp.get("fullname").toString());
				
				item.setAddress_delivery(temp.get("address_delivery")==null?"":temp.get("address_delivery").toString());						
				item.setPhone(temp.get("phone").toString());
				item.setPayment_method(temp.get("payment_name").toString());
				// item.setDelivery_method(temp.get("delivery_name"));
				String date_order = temp.get("order_date").toString();
				
				item.setOrderDate(date_order);
				item.setOrderStatus(temp.get("order_status").toString());
				item.setInvoice(temp.get("invoice").toString());
				item.setOrderStatusNL(temp.get("status_nganluong").toString());
				item.setPayment_typeNL(temp.get("payment_type_nl").toString());
				item.setRefund_typeNL(temp.get("refund_type_nl").toString());
				list.add(item);
			}
			////System.out.println("size : "+list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<Order> get_order_manager_by_id(String orderid) {
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			String spname = "get_order_by_id";
			String[] pfield = { "orderid" };
			Object[] pvalues = { orderid };
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				Order item = new Order();
				item.setOrderId(temp.get("order_id").toString());
				item.setCustomername(temp.get("fullname").toString());
				item.setAddress_delivery(temp.get("address_delivery")
						.toString());
				item.setPhone(temp.get("phone").toString());
				item.setPayment_method(temp.get("payment_name").toString());
				String date_order = temp.get("order_date").toString();
				
				item.setOrderDate(date_order);
				item.setOrderStatus(temp.get("order_status").toString());
				item.setInvoice(temp.get("invoice").toString());
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<OrderDetail> get_orderdetail_by_id(String orderid) {
		ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			query = "select tb1.order_id, tb1.product_id,tb2.product_name,tb2.product_image,tb1.quantity,tb1.price,tb1.amount ";
			query += "from tb_order_detail tb1,tb_product tb2 ";
			query += "where tb1.product_id = tb2.product_id ";
			query += "and tb1.order_id = '" + orderid + "' and tb2.isvisible=true ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				OrderDetail item = new OrderDetail();
				item.setImage(temp.get("product_image").toString());
				item.setOrder_id(temp.get("order_id").toString());
				item.setProduct_id(temp.get("product_id").toString());
				item.setProduct_name(temp.get("product_name").toString());
				item.setQuantity(temp.get("quantity").toString());
				item.setPrice(Float.parseFloat(temp.get("price").toString()));
				item.setAmount(Float.parseFloat(temp.get("amount").toString()));
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static String Process_Order(String orderid, String type) {
		String result = "";
		try {
			String spname = "tb_order_processing";
			String[] pfields = { "orderid", "p_type", "f" };
			Object[] pvalues = { orderid, type, "" };
			String[] ptypes = { "VARCHAR", "VARCHAR", "VARCHAR" };
			int[] pdirec = { 0, 0, 1 };
			Map<String, Object> mapOfObjects = new HashMap<String, Object>();
			IConnectEJBS con = new IConnectEJBS();
			mapOfObjects = con.ExecBoFunctionReturnList(spname, pfields,
					ptypes, pvalues, pdirec);
			if (mapOfObjects.size() > 0) {
				result = mapOfObjects.get("f").toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static List<Map<String, Object>> get_order_manager_by_id_return_resultset(
			String orderid) {
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "get_order_by_id";
			String[] pfield = { "orderid" };
			Object[] pvalues = { orderid };
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listob;
	}

	public static List<Map<String, Object>> get_orderdetail_by_id_result(
			String orderid) {
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			query = "select tb1.order_id, tb1.product_id,tb2.product_name,tb1.quantity,tb1.price,tb1.amount ";
			query += "from tb_order_detail tb1,tb_product tb2 ";
			query += "where tb1.product_id = tb2.product_id ";
			query += "and tb1.order_id = '" + orderid + "' and tb2.isvisible=true ";
			IConnectEJBS con = new IConnectEJBS();
			listob = con.GetDataReturnResultSet(query);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listob;
	}

	public static ArrayList<Order> get_list_order_by_user(String email) {
		ArrayList<Order> list = new ArrayList<Order>();
		try {
			String spname = "get_order_by_user";
			String[] pfield = { "p_email" };
			Object[] pvalues = { email };
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			////System.out.println("email: "+email);
			////System.out.println("Size: "+listob.size());
			for (int i = 0; i < listob.size(); i++) {
				Map<String, Object> temp = listob.get(i);
				Order item = new Order();
				ItemOrderHistory itemhis = get_item_order_his(temp.get(
						"order_id").toString());
				item.setOrderId(temp.get("order_id").toString());
				item.setAddress_delivery(temp.get("address_delivery") == null ? ""
						: temp.get("address_delivery").toString());
				item.setAmount(itemhis.amount);
				item.setProductId(itemhis.productname);
				String date_order = temp.get("order_date").toString();
				
				item.setOrderDate(date_order);
				item.setOrderStatus(temp.get("order_status").toString());
				list.add(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static ItemOrderHistory get_item_order_his(String orderid) {
		ItemOrderHistory item = new ItemOrderHistory();
		String query = "SELECT b.product_name,a.amount ";
		query += "FROM tb_order_detail a,tb_product b ";
		query += "where a.product_id = b.product_id and a.order_id = '"
				+ orderid + "' and b.isvisible=true ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		IConnectEJBS con = new IConnectEJBS();
		list = con.GetDataReturnResultSet(query);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> temp = list.get(i);
			item.productname += temp.get("product_name") + ";";
			item.amount += Float.parseFloat(temp.get("amount").toString());
		}
		if(item.productname.length()>0){
			item.productname = item.productname.substring(0,
					item.productname.length() - 1);
		}
		
		return item;
	}

	public static int save_product_later(String email, String product_id) {
		int rs = -1;
		IConnectEJBS con = new IConnectEJBS();
		try {
			String query = "";
			query = "SELECT * FROM tb_product_later where email = '"+ email + "' and product_id='" + product_id + "' ";
			////System.out.println(query);
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();			
			listob = con.GetDataReturnResultSet(query);
			////System.out.println("Size: "+listob.size());
			if(listob.size()>0){
				rs = -2;
			}
			else{
				query = "insert into tb_product_later(email,product_id) values('"
						+ email + "','" + product_id + "')";

				
				Map<String, String> result = new HashMap<String, String>();
				result = con.ExecUpdate(query);
				rs = Integer.parseInt(result.get("result").toString());	
			}			
		} catch (Exception ex) {

		}
		return rs;

	}
	public static ArrayList<ProductData> get_save_product_later(String email) {
		
		IConnectEJBS con = new IConnectEJBS();
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		try {
			String query = "";
			query = "SELECT a.product_id, b.product_name,b.product_price,b.product_des,b.product_image ";
			query+="FROM tb_product_later a, tb_product b ";
			query+="where a.email = '"+email+"' and a.product_id = b.product_id and b.isvisible=true ";			
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();			
			listob = con.GetDataReturnResultSet(query);
			for(int i=0;i<listob.size();i++){
				Map<String, Object> temp = listob.get(i);
				ProductData item = new ProductData();
				item.setProductId(temp.get("product_id").toString());
				item.setProductName(temp.get("product_name").toString());
				item.setProductPrice(temp.get("product_price") == null ? 0 : Float
						.parseFloat(temp.get("product_price").toString()));
				item.setProductImage(temp.get("product_image").toString());		
				item.setProductDes(temp.get("product_des").toString());
				list.add(item);
			}			
		} catch (Exception ex) {

		}
		return list;

	}
	public static int cancel_save_product_later(String email, String product_id) {
		int rs = -1;
		IConnectEJBS con = new IConnectEJBS();
		try {
			String query = "";
			query = "SELECT * FROM tb_product_later where email = '"+ email + "' and product_id='" + product_id + "'";
			////System.out.println(query);
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();			
			listob = con.GetDataReturnResultSet(query);
			////System.out.println("Size: "+listob.size());
			if(listob.size()>0){
				query = "delete from tb_product_later where email = '"+ email + "' and product_id='" + product_id + "'";
				
				Map<String, String> result = new HashMap<String, String>();
				result = con.ExecUpdate(query);
				rs = Integer.parseInt(result.get("result").toString());	
			}
			else{
				rs =-2;
			}			
		} catch (Exception ex) {

		}
		return rs;

	}
	public static int finish_order( String order) {
		int rs = -1;
		IConnectEJBS con = new IConnectEJBS();
		try {
			String query = "";
			query = "SELECT * FROM tb_order where order_id = '"+ order + "'";			
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();			
			listob = con.GetDataReturnResultSet(query);
			////System.out.println("Size: "+listob.size());
			if(listob.size()>0){
				query = "update  tb_order set order_status='3',isstate=2,issyn=false where  order_id = '"+ order + "'";
				
				Map<String, String> result = new HashMap<String, String>();
				result = con.ExecUpdate(query);
				rs = Integer.parseInt(result.get("result").toString());	
			}
			else{
				rs =-2;
			}			
		} catch (Exception ex) {

		}
		return rs;

	}
	public static int cancel_order( String order_id) {
		int rs = -1;
		IConnectEJBS con = new IConnectEJBS();
		try {
			String query = "";
			query = "SELECT * FROM tb_order where order_id = '"+ order_id+"'";			
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();			
			listob = con.GetDataReturnResultSet(query);
			Map<String, Object> temp = listob.get(0);
			ProductData item = new ProductData();
			String status = (temp.get("order_status").toString());
			//////System.out.println("stt : "+status);
			if(status.equals("0") || status.equals("1")){
				query = "UPDATE  tb_order set order_status='-1',isstate=2,issyn=false where order_id = '"+ order_id+"'";
				Map<String, String> result = new HashMap<String, String>();
				result = con.ExecUpdate(query);
				rs = Integer.parseInt(result.get("result").toString());
			}
			else if(status.equals("3")){
				rs =3;
			}
			else if(status.equals("-1")){
				rs=-1;
			}
		} catch (Exception ex) {
				rs=2;
		}
		//////System.out.println("rs : "+rs);
		return rs;

	}
	public static ModelPO  get_order_by_id(String orderid){
		ModelPO item = new ModelPO();
		 try{
			 String spname = "get_order_by_id";
			 String[]pfield = {"orderid"};
			 Object[]pvalues ={orderid};
			 ResultSet rs = null;
			 
			 rs = ConnectDBD.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			 while(rs.next()){	 
				 
				 item.setBuyer("VIVMALL");
				 item.setAddressBuyer(rs.getString("address_delivery"));
				 item.setPhoneBuyer(rs.getString("phone"));
				 item.setEmail(rs.getString("email"));
				 item.setVendor("VIVMALL");				 
			 }
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return item;
	}
	public static ArrayList<ModelPODTL>  get_orderdetail_by_id2(String orderid){
		ArrayList<ModelPODTL> list = new ArrayList<ModelPODTL>();
		String query = null;
		ResultSet rs  = null;
		try{
			query = "select tb1.order_id, tb1.product_id,tb2.product_name,tb1.quantity,tb1.price,tb1.amount "; 
			query +="from tb_order_detail tb1,tb_product tb2 ";
			query +="where tb1.product_id = tb2.product_id ";
			query +="and tb1.order_id = '"+orderid+"'";
			rs = ConnectDBD.GetDataReturnResultSet(query);
			while(rs.next()){
				ModelPODTL item = new ModelPODTL();				
				item.setItem_no(rs.getString("product_id"));
				item.setItem_name(rs.getString("product_name"));
				item.setQuantity(rs.getFloat("quantity"));
				item.setPrice(rs.getFloat("price"));
				item.setAmount(rs.getFloat("amount"));
				list.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	public static int  check_status_order_send_logistic(String order){
		int n = -2;
		try{
			String query ="SELECT order_status FROM tb_order where order_id = '"+order+"'";
			IConnectEJBS con = new IConnectEJBS();			
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			listob = con.GetDataReturnResultSet(query);
			for(Map<String, Object> item : listob){
				n = Integer.parseInt(item.get("order_status").toString());
				break;
			}
			
		}catch(Exception ex){
			//ex.printStackTrace();
		}		
		return n;
	}
	public static int  update_status_order_after_send_logistic(String order){
		int n = -1;
		try{
			String query ="update tb_order set order_status=2,isstate=2,issyn=false  where order_id = '"+order+"'";
			IConnectEJBS con = new IConnectEJBS();			
			Map<String, String> result = new HashMap<String, String>();
			result = con.ExecUpdate(query);
			n = Integer.parseInt(result.get("result").toString());			
		}catch(Exception ex){
			//ex.printStackTrace();
		}		
		return n;
	}
	public static Boolean check_payment(String payment) {
		String query = "SELECT payment_id FROM tb_payment_method where payment_id='"+payment+"'";		
		IConnectEJBS con = new IConnectEJBS();			
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		for(Map<String, Object> item : listob){
			return true;			
		}
		return false;
	}
	public static ModelOrderNL  get_info_order_sendto_nl(String orderid){
		ModelOrderNL item = new ModelOrderNL();
		 try{
			 String spname = "get_info_order_sendto_nl";
			 String[]pfield = {"orderid"};
			 Object[]pvalues ={orderid};
			 ResultSet rs = null;
			 
			 rs = ConnectDBD.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			 while(rs.next()){	 
				 
				 item.setPrice(rs.getString("amount"));
				 item.setQuantity(rs.getFloat("quantity"));
				 break;
			 }
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return item;
	}
	public static String get_cancel_url() {
		String query = "SELECT CDCONTENT FROM dbnvivmall.tb_allcode where cdtype = 'NL' AND CDNAME = 'URL' AND CDVAL = 'CANCEL'";		
		IConnectEJBS con = new IConnectEJBS();			
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		String url_cancel = "";
		for(Map<String, Object> item : listob){
				url_cancel = item.get("CDCONTENT").toString();
				break;
		}
		return url_cancel;
	}
	public static String get_return_url() {
		String query = "SELECT CDCONTENT FROM dbnvivmall.tb_allcode where cdtype = 'NL' AND CDNAME = 'URL' AND CDVAL = 'RETURN'";		
		IConnectEJBS con = new IConnectEJBS();			
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		String url_cancel = "";
		for(Map<String, Object> item : listob){
				url_cancel = item.get("CDCONTENT").toString();
				break;
		}
		return url_cancel;
	}
	public static String get_receive() {
		String query = "SELECT CDCONTENT FROM dbnvivmall.tb_allcode where cdtype = 'NL' AND CDNAME = 'URL' AND CDVAL = 'RECEIVE'";		
		IConnectEJBS con = new IConnectEJBS();			
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		listob = con.GetDataReturnResultSet(query);
		String url_cancel = "";
		for(Map<String, Object> item : listob){
				url_cancel = item.get("CDCONTENT").toString();
				break;
		}
		return url_cancel;
	}
	

	public static List<Map<String, Object>> get_info_chart_report(String pdate) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try{
			 String query="";
			 query+=" SELECT tb1.order_id,amount,tb2.product_id,tb3.product_type_id,tb4.product_type_name,SUM(amount) total ";
			 query+=" FROM tb_order tb1,tb_order_detail tb2,tb_product tb3,tb_product_type tb4 ";
			 query+=" WHERE datediff('"+pdate+"',order_date)=0 ";
			 query+=" AND tb1.order_status=3 ";
			 query+=" AND tb1.order_id = tb2.order_id  ";
			 query+=" AND tb3.product_type_id =tb4.product_type_id ";
			 query+=" AND tb2.product_id = tb3.product_id ";
			 query+=" Group by product_type_id;";
			IConnectEJBS con = new IConnectEJBS();
			list = con.GetDataReturnResultSet(query);
		}catch(Exception e){
			e.printStackTrace();
		}

		return list;
	}

	public static List<Map<String, Object>> count_order(String pdate) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String query="";
		try{
			query+="SELECT count(order_id) countorder,order_status,DATE_FORMAT(order_date,'%Y-%m-%d') datefm ";
			query+=" FROM tb_order ";
			query+=" WHERE datediff('"+pdate+"',order_date)=0";
			query+=" group by order_status";
			//System.out.print(query);
			IConnectEJBS con = new IConnectEJBS();
			list=con.GetDataReturnResultSet(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<ProductData> get_info_report_product(String pdate) {
		
		ArrayList<ProductData> list = new ArrayList<ProductData>();
		List<Map<String,Object>> listob= new ArrayList<Map<String,Object>>();
		String query="";
		query+="SELECT tb1.order_id,tb2.product_id,tb2.quantity,tb1.order_date,SUM(tb2.quantity) amount,price,product_name  ";
		query+=" FROM tb_order tb1, tb_order_detail tb2,tb_product tb3 ";
		query+=" WHERE tb1.order_id=tb2.order_id";
		query+=" AND datediff('"+pdate+"',order_date)=0";
		query+=" AND tb2.product_id = tb3.product_id";
		query+=" Group by product_id";	
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		if(listob.size()>0){
			for (Map<String, Object> temp : listob) {
				ProductData item = new ProductData();
				item.setProductquantity(temp.get("amount").toString());
				item.setProductId(temp.get("product_id").toString());
				item.setProductPrice(Float.parseFloat(temp.get("price").toString()));
				item.setProductName(temp.get("product_name").toString());
				list.add(item);
			}
			
		}
		return list;
	}

	public static List<Map<String,Object>> get_report_period(String fdate, String tdate) {

		List<Map<String,Object>> listob = new ArrayList<Map<String,Object>>();		
		try{	
			 IConnectEJBS con = new IConnectEJBS();
			 String spname = "get_report_period";
			 String[]pfield = {"fromdate","todate"};
			 Object[]pvalues ={fdate,tdate};	 
			 listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }	
		////System.out.println(listob.size());
		return listob;
	}
	public static String get_amount_of_day(String date){
		String amount="";
		String query="";
		query+= "SELECT SUM(amount) sum, DATE_FORMAT(order_date,'%Y-%m-%d') datefm";
		query+= " FROM tb_order tb1, tb_order_detail tb2";
		query+= " WHERE tb1.order_id = tb2.order_id";
		query+= " AND DATE_FORMAT(tb1.order_date,'%Y-%m-%d') = '"+date+"'";
		List<Map<String,Object>> listob = new ArrayList<Map<String,Object>>();
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		amount =listob.get(0).get("sum").toString();
		return amount;
	}
	public static void main(String[] args) {
		////System.out.println(get_info_report_product("2016-08-03"));
	}

	public static List<Map<String, Object>> get_report_year(String fdate, String tdate) {
		List<Map<String,Object>> listob = new ArrayList<Map<String,Object>>();		
		try{	
			 IConnectEJBS con = new IConnectEJBS();
			 String spname = "get_report_year";
			 String[]pfield = {"fromdate","todate"};
			 Object[]pvalues ={fdate,tdate};	 
			 listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }	
		////System.out.println(listob.size());
		return listob;
	}

	public static ArrayList<Order> search_order(String order_id, String date) {
		List<Map<String,Object>> listob= new ArrayList<Map<String,Object>>();
		ArrayList<Order> list = new ArrayList<Order>();
		try{
			IConnectEJBS con = new 	IConnectEJBS();
			String spname="sp_search_order";
			String[] pfield = {"p_order","p_date"};
			Object[] pvalues = {order_id,date};
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			if(listob.size()>0){
				for (Map<String,Object> temp : listob) {
					Order item = new Order();
					item.setOrderId(temp.get("order_id").toString());
					item.setCustomername(temp.get("fullname").toString());		
					item.setAddress_delivery(temp.get("address_delivery")==null?"":temp.get("address_delivery").toString());						
					item.setPhone(temp.get("phone").toString());
					item.setPayment_method(temp.get("payment_name").toString());
					String date_order = temp.get("order_date").toString();	
					item.setOrderDate(date_order);
					item.setOrderStatus(temp.get("order_status").toString());
					item.setInvoice(temp.get("invoice").toString());
					item.setOrderStatusNL(temp.get("status_nganluong").toString());
					item.setPayment_typeNL(temp.get("payment_type_nl").toString());
					item.setRefund_typeNL(temp.get("refund_type_nl").toString());
					list.add(item);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}

class ItemOrderHistory {
	public String productname = "";
	public float amount = 0;

}
