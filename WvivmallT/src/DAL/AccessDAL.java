package DAL;

import java.util.HashMap;
import java.util.Map;

import EJB.IConnectEJBS;


public class AccessDAL {

	public static int insert_access(){
		int result = -1;
		String query="insert into tb_acess(date_acess) values(now())";
		try{
			Map<String,String> rs = new HashMap<String,String>();
			IConnectEJBS con = new IConnectEJBS();
			rs = con.ExecUpdate(query);
			result =Integer.parseInt(rs.get("result"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
