package DAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.ItemModel;

import EJB.IConnectEJBS;
public class HelperDAL {
	public static ArrayList<ItemModel> getPayment()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		String query = "select payment_id,payment_name from tb_payment_method";
		try
		{
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				ItemModel item = new ItemModel();
				item.setID(item1.get("payment_id").toString());
				item.setName(item1.get("payment_name").toString());
				list.add(item);		
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<ItemModel> getdelivery()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		String query = "select delivery_method_id,delivery_method_name  from tb_delivery_method";
		try
		{
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				ItemModel item = new ItemModel();
				item.setID(item1.get("delivery_method_id").toString());
				item.setName(item1.get("delivery_method_name").toString());
				list.add(item);		
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<ItemModel> getintroduction()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		String query = "SELECT  info_intro info FROM tb_introduction limit 0,1;";
		try
		{
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				ItemModel item = new ItemModel();
				item.setID("0");
				item.setName(item1.get("info").toString());
				list.add(item);
				break;
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<ItemModel> getguide()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		
		String query = "SELECT  info_guide info  from tb_guide limit 0,1;";
		try
		{
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				ItemModel item = new ItemModel();
				item.setID("0");
				item.setName(item1.get("info").toString());
				list.add(item);
				break;
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<ItemModel> get_access()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
		String query = "select * from vw_get_access";
		try
		{
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				ItemModel item = new ItemModel();
				item.setName(item1.get("num").toString());
				list.add(item);	
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
}
