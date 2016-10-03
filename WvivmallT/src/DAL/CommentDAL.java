package DAL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import Controller.CommentController;

import Model.ItemComment;
import Model.ItemCommentSS;
import Model.ItemReplyMessage;

import EJB.IConnectEJBS;
public class CommentDAL {
	public static String colCustomer = "colCustomer";
	public static String Inert_Customer(String email,String fullname){		
		String rs = "";
		try{
			if(MemberDAL.checkemailexist(email) == 0){
				String id = CommentDAL.get_id();
				String spname = "tb_member_insert_side_comment";
				String[] pfield = {"f","p_email", "p_pass", "p_fullname", "p_birthday",
						"p_phone", "p_registerdate","p_id"};
				String birthday = get_birthday();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				Object[] pvalues = {"", email,"123", fullname, birthday,"",
						dateFormat.format(date),id};
				String[]ptype= {"INT","VARCHAR","VARCHAR","VARCHAR","VARCHAR",
						"VARCHAR","VARCHAR","VARCHAR"};
				int[]pdirec = {1,0,0,0,0,
				               0,0,0
				};
				IConnectEJBS con = new IConnectEJBS();
				Map<String, Object> mapOfObjects = new HashMap<String, Object>();
				mapOfObjects = con.ExecBoFunctionReturnList(spname,pfield,ptype,pvalues,pdirec);
				int _result = Integer.parseInt(mapOfObjects.get("f").toString());
				if(_result==0){
					rs = id+"_0";	
				}
				else{
					rs = "A_-1";
				}
			}
			else{
				rs = "A_1";	
			}
		}catch(Exception ex){
			rs = "A_-1";
		}
		return rs;
	}
	public static ItemCommentSS insert_comment(ItemComment item){
		ItemCommentSS item1 = new ItemCommentSS();
		try{
			
			String col = "colCustomer";
			String date = get_current_date();
			String id_sub="";
			String id_main = "";
			String id  = get_id();
			String product_type = get_product_type(item.getProduct());
			
			if(item.getId().equals("1991")) // ma dinh danh
			{
				id_main = id;
				id_sub = "0";
			}
			else{
				id_main = item.getId();
				id_sub = id;
			}			
			String[]pf  = {
				"id_cus","id","id_sub","product","product_type","date","message" ,"role","state"
			};
			Object[]pv = {
				item.getId_customer(),id_main,id_sub,item.getProduct(),product_type,date,item.getMessage(),"1","1"
			};
			//role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);
			if(_rs==0){
				item1.setKey(0);
				item1.setDate(date);
				item1.setId(id);
			}
			else{
				item1.setKey(-1);
			}
			
		}catch(Exception ex){
			item1.setKey(-1);
		}
		return item1;
	}
	public static String get_product_type(String productid){
		String query = "SELECT product_type_id FROM tb_product where product_id='"+productid+"' and isvisible=true ";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();		
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		String producttype = "";
		if(listob.size()>0){
			producttype = listob.get(0).get("product_type_id").toString();
		}
		return producttype;
	}
	public static ArrayList<ItemComment> get_list_message_by_product_id(String product) throws ParseException{
		ArrayList<ItemComment>  list = new ArrayList<ItemComment>();
		DBObject obj = new BasicDBObject("product",product).append("id_sub","0").append("role","1");
		List<DBObject> list_data = new ArrayList<DBObject>();
		list_data = ConnectDBMongo.getColectionByObjCustomer(colCustomer, obj,-1);
		for(DBObject item : list_data){
			ItemComment it = new ItemComment();
			it.setDate(item.get("date").toString());
			it.setMessage(item.get("message").toString());
			it.setId_customer(item.get("id_cus").toString());
			it.setId(item.get("id").toString());
			it.setId_sub(item.get("id_sub").toString());
			it.setName_customer(item.get("id_cus")==null?"":MemberDAL.Getfullname_byid(item.get("id_cus").toString()));
			////System.out.println(it.getName_customer());
			it.setShortname(it.getName_customer()==null?"":CommentController.get_short_fullname(it.getName_customer().toUpperCase()));
			String time =convert_string_to_date(item.get("date").toString());
			it.setTimesend(get_time_send(time));
			ArrayList<ItemComment> list_comment_sub = new ArrayList<ItemComment>();
			it.setList_comment_sub(list_comment_sub);
			//get item sub if have			
			DBObject objs = new BasicDBObject("product",product).append("id",item.get("id").toString());			
			List<DBObject> list_data_s = new ArrayList<DBObject>();
			list_data_s = ConnectDBMongo.getColectionByObjCustomer(colCustomer, objs,1);
			for(DBObject items : list_data_s){
				if(items.get("role").toString().equals("0")){ // nhan vien quan tri
					ItemComment its = new ItemComment();
					its.setDate(items.get("date").toString());
					its.setMessage(items.get("message").toString());
					its.setId_customer(items.get("id_cus").toString());
					its.setId(items.get("id").toString());
					its.setId_sub(items.get("id_sub").toString());
					its.setRole(items.get("role").toString());
					its.setName_customer(MemberDAL.Getfullname_user_byid(items.get("id_user").toString()));
					its.setShortname(CommentController.get_short_fullname(its.getName_customer().toUpperCase()));
					String times =convert_string_to_date(items.get("date").toString());
					its.setTimesend(get_time_send(times));
					it.getList_comment_sub().add(its);
				}
				else{
					if(!items.get("id_sub").toString().equals("0")){
						ItemComment its = new ItemComment();
						its.setDate(items.get("date").toString());
						its.setMessage(items.get("message").toString());
						its.setId_customer(items.get("id_cus").toString());
						its.setId(items.get("id").toString());
						its.setId_sub(items.get("id_sub").toString());
						its.setRole(items.get("role").toString());
						its.setName_customer(MemberDAL.Getfullname_byid(items.get("id_cus").toString()));
						its.setShortname(CommentController.get_short_fullname(its.getName_customer().toUpperCase()));
						String times =convert_string_to_date(items.get("date").toString());
						its.setTimesend(get_time_send(times));
						it.getList_comment_sub().add(its);	
					}
					
				}						
			}
			list.add(it);
		}
		return list;
	}
	public static String get_producttype_by_userid(String userid){
		String query = "SELECT manager_cate_type FROM tb_user where user_name='"+userid+"'";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();		
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		String producttype = "";
		if(listob.size()>0){
			producttype = listob.get(0).get("manager_cate_type").toString();
		}
		return producttype;
	}
	public static void main(String[] args) throws ParseException {
		ArrayList<ItemComment>  list = new ArrayList<ItemComment>();
		list = get_list_message_manager("u1");
	}
	public static ArrayList<ItemComment> get_list_message_manager(String userid) throws ParseException{
		////System.out.println(userid);
		ArrayList<ItemComment>  list = new ArrayList<ItemComment>();
		String product_type_by_userid = get_producttype_by_userid(userid);
		
		String[]arr_produc_t = product_type_by_userid.split(",");
		//set obj
		DBObject obj = new BasicDBObject();
		if(arr_produc_t.length>0){			
			BasicDBList or = new BasicDBList();
			
			for(int i=0;i<arr_produc_t.length;i++){
				DBObject obj1 = new BasicDBObject();
				obj1.put("product_type",arr_produc_t[i]);
				obj1.put("state","1");
				or.add(obj1);
			}
			obj.put("$or",or);			
		}
		else{ //0
			obj.put("state","1");
			obj.put("product_type",arr_produc_t[0]);			
		}
		//
		List<DBObject> list_data = new ArrayList<DBObject>();
		list_data = ConnectDBMongo.getColectionByObjCustomer(colCustomer, obj,-1);
		
		//
		for(DBObject item : list_data){
			ItemComment it = new ItemComment();
			it.setDate(item.get("date").toString());
			it.setMessage(item.get("message").toString());
			it.setId_customer(item.get("id_cus").toString());
			it.setId(item.get("id").toString());
			it.setId_sub(item.get("id_sub").toString());
			String fullname = MemberDAL.Getfullname_byid(item.get("id_cus").toString());
			it.setName_customer(fullname);
		//	it.setShortname(CommentController.get_short_fullname(fullname));
			String time =convert_string_to_date(item.get("date").toString());
			it.setTimesend(get_time_send(time));						
			list.add(it);
		}
		return list;
	}
	public static int get_total_comment_by_product(String productid) throws ParseException{
		ArrayList<ItemComment>  list = new ArrayList<ItemComment>();	
		
		DBObject obj = new BasicDBObject();
		obj.put("product",productid);
		obj.put("role","1");
		//
		List<DBObject> list_data = new ArrayList<DBObject>();
		list_data = ConnectDBMongo.getColectionByObjCustomer(colCustomer, obj,-1);
		return list_data.size();
	}
//	public static void main(String[] args) throws ParseException {
//		ArrayList<ItemComment>  list = new ArrayList<ItemComment>();
//		list = get_list_message_manager("u1");
//		String aaa = new Gson().toJson(list);
//		////System.out.println(aaa);
//	}
	public static ItemComment get_info_comment_by_id(String id,String idsub){
		ItemComment item_rs = new ItemComment();
		DBObject obj = new BasicDBObject();
		obj.put("id",id);
		obj.put("id_sub",idsub);
		List<DBObject> list_data = new ArrayList<DBObject>();
		list_data = ConnectDBMongo.getColectionByObjCustomer(colCustomer, obj,-1);
		//
		for(DBObject item : list_data){		
			item_rs.setDate(item.get("date").toString());
			item_rs.setMessage(item.get("message").toString());
			item_rs.setId_customer(item.get("id_cus").toString());
			item_rs.setProduct(item.get("product").toString());
			item_rs.setId(item.get("id").toString());
			item_rs.setId_sub(item.get("id_sub").toString());
			item_rs.setProduct_name(get_product_name(item.get("product").toString()));
			item_rs.setName_customer(MemberDAL.Getfullname_byid(item.get("id_cus").toString()));			
											
			break;
		}
		return item_rs;
	}
	public static String get_product_name(String product_id){
		String query = "SELECT product_name FROM tb_product where product_id='"+product_id+"' and isvisible=true ";
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();		
		IConnectEJBS con = new IConnectEJBS();
		listob = con.GetDataReturnResultSet(query);
		String productname = "";
		if(listob.size()>0){
			productname = listob.get(0).get("product_name").toString();
		}
		return productname;
	}
	public static String get_time_send(String date) throws ParseException{
		
		Calendar cal1 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date start = sdf.parse(date);	
		
		Calendar cal2 = Calendar.getInstance();		
		Date end=new Date();
		end =  cal2.getTime();
		String rs = substract_time(start,end);
		//convert long to date
		Long diff = Long.parseLong(rs);
		String strrs = "";
		Long day = diff / (1000   * 60 * 60 * 24);
		Long hour = diff / (1000  * 60 * 60);
		Long minute = diff / (1000  * 60);
		Long second = diff / (1000);
		
		if(day>0){
			strrs = "Khoảng "+day+" giờ trước";
		}
		else if(hour>0){
			strrs = "Khoảng "+hour+" giờ trước";
		}
		else if(minute>0){
			strrs = "Khoảng "+minute+" phút trước";
		}
		else{
			strrs = "Khoảng "+second+" giây trước";
		}
		
		return strrs;
	}
	public static String convert_string_to_date(String date) {		
		
		String[] arr =date.split("-");
		String str = arr[2]+"/"+arr[1]+"/"+arr[0]+" "+arr[3]+":"+arr[4]+":"+arr[5];		
		return str;
	}
	public static String substract_time (Date d1,Date d2){
    	long l1=d1.getTime();
    	long l2=d2.getTime();
    	long d3=l2-l1;
		return String.valueOf(d3);
    	
    }
	public static int check_email_exists(String email){
		int rs = -1;
		try{
			DBObject matchFields = new BasicDBObject("email",email);
			List<DBObject> list = ConnectDBMongo.getColectionByObj(colCustomer, matchFields);
			if(list.size()>0)
				rs = 0;
			else
				rs =-1;
			
		}catch(Exception ex){
			rs = -1;
		}
		return rs;		
	}
	public static String get_id(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		String id = dateFormat.format(cal.getTime());
		return id;
	}
	public static String get_birthday(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String id = dateFormat.format(cal.getTime());
		return id;
	}
	public static String get_current_date(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String id = dateFormat.format(cal.getTime());
		return id;
	}
	public static String get_current_date2(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String id = dateFormat.format(cal.getTime());
		return id;
	}
	public static String get_time(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String id = dateFormat.format(cal.getTime());
		return id;
	}
	public static ItemCommentSS reply_message_customer(ItemReplyMessage item){
		ItemCommentSS item1 = new ItemCommentSS();
		try{
			
			String col = "colCustomer";
			String date = get_current_date();
			String product_type = get_product_type(item.getProduct());
			String subid  = get_id();
			String[]pf  = {
				"id_user","id_cus","id","id_sub","product",
				"product_type","date","message" ,"role"
			};
			Object[]pv = {
				item.getUserid(),item.getCustomerid(),item.getId(),subid,item.getProduct(),
				product_type,date,item.getMessage(),"0"				
			};
			//role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);
			if(_rs==0){
				//update staus message customer
				String []pf1={
					"id","id_sub","state"
				};
				String[]pv1 = {
						item.getId(),item.getSubid(),"0"
				};
				int[]pt1 = {
					1,1,0	
				};
				int _rs1 = ConnectDBMongo.update(colCustomer,pf1,pv1,pt1);
				if(_rs1==0){
					item1.setKey(0);	
				}
				else{
					//delete old row
					String[]pf2={
							"id","id_sub","id_user"		
					};
					String[]pv2 = {
						item.getId(),subid,item.getUserid()	
					};
					int _rs2 = ConnectDBMongo.remove(colCustomer,pf2,pv2);
					item1.setKey(-1);	
				}											
			}
			else{
				item1.setKey(-1);
			}
			
		}catch(Exception ex){
			item1.setKey(-1);
		}
		return item1;
	}	
}

