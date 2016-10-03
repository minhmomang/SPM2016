package DAL;

import EJB.IConnectEJBS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Sale_Acticle;

public class Sale_acticleDAL {

	
	public static Map<String, Object> insert_Sale_acticle(String type,Sale_Acticle item){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String spname = "sp_tb_Sale_acticle_insert";
			String[]pfield = {
					"f","p_type","p_id","p_title","p_content",
					"p_creator","p_modifier","p_description","p_image"

			};
			String[] ptype={
					"INT","varchar","varchar","TEXT","TEXT",
					"varchar","varchar","Text","varchar"
			};
			Object[]pvalue={
					"",type,item.getId(),item.getTitle(),item.getContent(),
					item.getCreator(),item.getModifier(),item.getDescription(),item.getImage()
					
			};
			int[]pdirec = {
						1,0,0,0,0
						,0,0,0,0		
					};
			IConnectEJBS con = new IConnectEJBS();			
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	public static Sale_Acticle get_Sale_acticle(String p_id){
		Sale_Acticle item = new Sale_Acticle();
	String query = "select title,content,image,description  from tb_sale_acticle  where id='"+p_id+"'";
	try{
		IConnectEJBS con = new IConnectEJBS();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = con.GetDataReturnResultSet(query);
		for(Map<String,Object> item1 : list){
			item.setTitle(item1.get("title").toString());
			item.setContent(item1.get("content").toString());
			break;
		}
	}catch(Exception ex){			
	}
	
	return item;
}
	public static ArrayList<Sale_Acticle> get_list_saleacticle()
	{
		ArrayList<Sale_Acticle> list = new ArrayList<Sale_Acticle>();
		try
		{
			String 	query="select id, title, sale_acticle_status, creator,"
					+	"DATE_FORMAT(create_date,'%Y-%m-%d') as create_date, modifier, DATE_FORMAT(modify_date,'%Y-%m-%d')as modify_date" 
						+"	from tb_sale_acticle;";
				List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
				IConnectEJBS con = new IConnectEJBS();
				listob=con.GetDataReturnResultSet(query);
				//System.out.print(listob.size());
				//String json=new Gson().toJson(listob);
			//	////System.out.println(json);
				 for(int i=0;i<listob.size();i++){
					 Map<String, Object> temp=listob.get(i);
					 
						Sale_Acticle item= new Sale_Acticle();
						item.setId(temp.get("id").toString());
						item.setTitle(temp.get("title").toString());
						item.setSale_acticle_status(temp.get("sale_acticle_status").toString());
						item.setCreator(temp.get("creator").toString());
						item.setCreate_date(temp.get("create_date").toString());
						item.setModifier(temp.get("modifier").toString());
						item.setModify_date(temp.get("modify_date").toString());
						list.add(item);
				 }
	
				
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Sale_Acticle> get_list_saleacticle_client()
	{
		ArrayList<Sale_Acticle> list = new ArrayList<Sale_Acticle>();
		try
		{
			String 	query="select id,title,description,image "
						+"	from tb_sale_acticle"
						+ " where sale_acticle_status=1"
						+ "	order by id desc;";
				List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
				IConnectEJBS con = new IConnectEJBS();
				listob=con.GetDataReturnResultSet(query);
				//System.out.print(listob.size());
				//String json=new Gson().toJson(listob);
			//	////System.out.println(json);
				 for(int i=0;i<listob.size();i++){
					 Map<String, Object> temp=listob.get(i);
					 
						Sale_Acticle item= new Sale_Acticle();
						item.setId(temp.get("id").toString());
						item.setTitle(temp.get("title").toString());
					
						item.setDescription(temp.get("description").toString());
						item.setImage(temp.get("image").toString());
						list.add(item);
				 }
	
				
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	public static Sale_Acticle get_saleacticle_clientbyid(String id)
	{
		Sale_Acticle l = new Sale_Acticle();
		try
		{
			String 	query="select id,title,content,description,image "
						+"	from tb_sale_acticle"
						+ " where sale_acticle_status=1 and id="+id+";";
						
				List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
				IConnectEJBS con = new IConnectEJBS();
				listob=con.GetDataReturnResultSet(query);
				//System.out.print(listob.size());
				//String json=new Gson().toJson(listob);
			//	////System.out.println(json);
				 for(int i=0;i<listob.size();){
					 Map<String, Object> temp=listob.get(i);
						l.setId(temp.get("id").toString());
						l.setTitle(temp.get("title").toString());
						l.setDescription(temp.get("description").toString());
						l.setImage(temp.get("image").toString());
						l.setContent(temp.get("content").toString());
						break;
				 }
	
				
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return l;
	}
	public static Map<String, Object> delete_sale(String str){
	Map<String, Object> result = new HashMap<String, Object>();
	try{
		String spname = "sp_tb_Saleacticle_delete";
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
	public static Map<String, Object> visible1_sale(String str){
	Map<String, Object> result = new HashMap<String, Object>();
	try{
		String spname = "sp_tb_Saleacticle_visible1";
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
	public static Map<String, Object> visible0_sale(String str){
	Map<String, Object> result = new HashMap<String, Object>();
	try{
		String spname = "sp_tb_Saleacticle_visible0";
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
