package DAL;
import java.util.ArrayList;

import EJB.IConnectEJBS;

import java.util.List;
import java.util.Map;


public class ErrorDAL {

	public static String getMesageError(int error){
		String query = "";
		String mes = "";
		try{
			query = "select ID,ErrorMsg from tb_error where Id='"+error+"'";
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			IConnectEJBS con = new IConnectEJBS();
			rs = con.GetDataReturnResultSet(query);
			for(Map<String,Object> item1 : rs){
				mes = item1.get("ErrorMsg").toString();
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mes;
	}
}
