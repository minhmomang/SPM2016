package DAL;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.ItemModel;


import EJB.IConnectEJBS;
public class ProviderDAL {
	public static ArrayList<ItemModel> getlistProviderInfor()
	{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		try
		{
			String spname = "sp_get_provider";
			List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			listob=	con.ExecBoFunctionReturnResutlSet(spname);
			
			
			 for(int i=0;i<listob.size();i++){
				 Map<String, Object> temp=listob.get(i);
					String id = temp.get("provider_id").toString();
					String name = temp.get("provider_name").toString();
					ItemModel item2= new ItemModel();
					item2.setID(id);
					item2.setName(name);
					list.add(item2);
			 }
			
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
}
