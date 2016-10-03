package DAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import EJB.IConnectEJBS;
public class LoginAdminDAO {
	public static int check_login(String username,String pass){
		int result = 0;
		IConnectEJBS con = new IConnectEJBS();
		try{
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			String query ="select * from tb_useradmin where username = '"+username+"' and password = '"+pass+"'";			
			rs =con.GetDataReturnResultSet(query);		
			for(Map<String,Object> item1 : rs){
				result =1;
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
