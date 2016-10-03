package DAL;


import EJB.IConnectEJBS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.SlideModel;

public class SlideDAL {

	
	public static ArrayList<SlideModel> get_list_slide(){
		String query = null;
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		ArrayList<SlideModel> list=new ArrayList<SlideModel>();
		try{			
			query = "SELECT  id,name,creator,create_date,isvisible FROM tb_slider order by id desc";
			IConnectEJBS con = new IConnectEJBS();
			listob= con.GetDataReturnResultSet(query);
		
			int num = 0;
			
			
			 for(int i=0;i<listob.size();i++){
				 Map<String, Object> temp=listob.get(i);
				 SlideModel item = new SlideModel();
					item.setId(temp.get("id").toString());
					item.setNum(String.valueOf(num));
					item.setName(temp.get("name").toString());
					item.setCreator(temp.get("creator").toString());
					item.setCreate_date(temp.get("create_date").toString());
					item.setIsvisible(Boolean.parseBoolean(temp.get("isvisible").toString())==true?"Hiện":"Ẩn");
					list.add(item);
					num++;
			 }

		
			
		}catch(Exception ex){
			////System.out.println(ex.getMessage());
		}
		return list;
		}
	public static Map<String, Object> save_slide(SlideModel a){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tbslider_insert";
			String[]pfield = {
					
					"f","p_name","p_creator"
			};
			String[]ptype={
					"INT","VARCHAR","VARCHAR"
			};
			Object[]pvalue={
				"",a.getName(),a.getCreator()
			};
			int[]pdirec = {
						1,0,0
							
					};
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	public static Map<String, Object> delete_slide(String str){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tbslider_delete";
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
	public static Map<String, Object> visible_slide(int type,String str){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tbslider_visible";
			String[]pfield = {
					"p_type","str","f"

			};
			String[]ptype={
					"INT","TEXT","INT"
			};
			Object[]pvalue={
					type,str,""
								
			};
			int[]pdirec = {
						0,0,1
					};
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
