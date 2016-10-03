package DAL;


import EJB.IConnectEJBS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.ItemModel;
import Model.ItemTemplateCate;

public class WriterCategoryDAL {
	public static ArrayList<Object> get_list_category(){
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ArrayList<Object> list = new ArrayList<Object>();
		try{			
			query = "SELECT id,name FROM tb_writer_category";
			 IConnectEJBS con = new IConnectEJBS();
			 listob =con.GetDataReturnResultSet(query);
			 for(int i=0;i<listob.size();i++){
				 Map<String, Object> temp=listob.get(i);
					ItemTemplateCate item = new ItemTemplateCate();
					item.setId(temp.get("id").toString());
					item.setName(temp.get("name").toString());
			
					list.add(item);
			 }
			 
			
		}catch(Exception ex){
			////System.out.println(ex.getMessage());
		}
		return list;
	}
	public static Map<String, Object> insert_cate(String type,String id,String name){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tb_writer_category";
			String[]pfield = {
					"f","p_type","p_id","p_cate"

			};
			String[]ptype={
					"INT","VARCHAR","VARCHAR","VARCHAR"
			};
			Object[]pvalue={
					"",type,id,name
								
			};
			int[]pdirec = {
						1,0,0,0
					};
			 IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	public static ItemModel get_cateogry_by_id(String id) throws SQLException, ClassNotFoundException, InstantiationException{
		ItemModel item = new ItemModel();
		String query = "SELECT id,name FROM tb_writer_category where id='"+id+"'";
		
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		 IConnectEJBS con = new IConnectEJBS();
		listob =con.GetDataReturnResultSet(query);
		
		 for(int i=0;i<listob.size();i++){
			 Map<String, Object> temp=listob.get(i);
			
				item.setID(temp.get("id").toString());
				item.setName(temp.get("name").toString());
				break;
		 }
		
		return item;
	}
	public static Map<String, Object> remove_category(String str){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tbwriter_cate_delete";
			String[]pfield = {
					"str","f"

			};
			String[]ptype={
					"TEXT","INT"
			};
			Object[]pvalue={
					str,""
								
			};
			int[]pdirec = {
						0,1
					};
			 IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}	
}
