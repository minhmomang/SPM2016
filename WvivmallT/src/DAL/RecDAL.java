package DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import Helper.Extra;

import Model.ItemRec;

import EJB.IConnectEJBS;
public class RecDAL {
	public static Map<String, Object> insert_rec(ItemRec item,String lang){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tb_recruitment";
			String[]pfield = {
					"p_type","f","p_position","p_qt","p_location","p_main_responsibility",
					"p_requirement","p_policies","p_contact","p_lang","p_id"

			};
			String[]ptype={
					"VARCHAR","INT","VARCHAR","VARCHAR","VARCHAR","TEXT",
					"TEXT","TEXT","TEXT","VARCHAR","VARCHAR"
			};
			Object[]pvalue={
					item.getType(),"",item.getPos(),item.getQt(),item.getLocation(),Extra.replace_origin_html(item.getMain()),
					Extra.replace_origin_html(item.getRequi()),Extra.replace_origin_html(item.getPoli()),Extra.replace_origin_html(item.getContact()),lang,item.getId()
			};
			int[]pdirec = {
						0,1,0,0,0,0,
						0,0,0,0,0
					};
			IConnectEJBS con = new IConnectEJBS();	
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			////System.out.println(new Gson().toJson(result));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	public static ArrayList<Object> get_list(String lang){
		ArrayList<Object> list_re = new ArrayList<Object>();
		try{
			
			String query = "SELECT recruit_id,recruit_position,recruit_number FROM tb_recruitment where lang = '"+lang+"'";
			
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = con.GetDataReturnResultSet(query);
			int num = 1;
			for(Map<String,Object> item1 : list){
				ItemRec item = new ItemRec();
				item.setOrder(String.valueOf(num));
				item.setId(item1.get("recruit_id").toString());
				item.setPos(item1.get("recruit_position").toString());
				item.setQt(item1.get("recruit_number").toString());
				list_re.add(item);
				num++;
			}
						
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list_re;
	}
	public static ItemRec get_rec_id(String id,String lang){
		ItemRec item  = new ItemRec();
		try{
			
			String query = "SELECT recruit_id,recruit_position,recruit_number,";
				   query+="location_working,main_responsibility, requirement,";
				   query+="policies,contact FROM tb_recruitment where recruit_id = '"+id+"' and lang = '"+lang+"'";
			
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : list){			
				item.setId(item1.get("recruit_id").toString());
				item.setPos(item1.get("recruit_position").toString());
				item.setQt(item1.get("recruit_number").toString());
				item.setLocation(item1.get("location_working").toString());
				item.setMain(item1.get("main_responsibility").toString());
				item.setRequi(item1.get("requirement").toString());
				item.setPoli(item1.get("policies").toString());
				item.setContact(item1.get("contact").toString());				
			}
						
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return item;
	}
	public static	ArrayList<Object> get_list_(String option,String value,String lang){
		ArrayList<Object> list_rs=new ArrayList<Object> ();
		
		 try{			 
			 String spname = "sp_rec_get";
			 String[]pfield ={"p_option","p_value","p_lang"};
			 Object[]pvalues = {option,value,lang};
			 IConnectEJBS con = new IConnectEJBS();
			 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			 list = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
			 		
			 for(Map<String,Object> item1 : list){		
					ItemRec item  = new ItemRec();
					item.setId(item1.get("recruit_id").toString());
					item.setPos(item1.get("recruit_position").toString());
					item.setQt(item1.get("recruit_number").toString());
					item.setLocation(item1.get("location_working").toString());
					list_rs.add(item);
			 }			 

		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		return  list_rs;
	}

	public static int delete_rec(String id, String lang) {
		String query = "delete from tb_recruitment where recruit_id  = '" + id + "' and lang = '" + lang + "'";

		IConnectEJBS con = new IConnectEJBS();

		return Integer.parseInt(con.ExecUpdate(query).get("result"));
	}	
}
