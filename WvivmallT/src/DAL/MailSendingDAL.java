package DAL;

import java.sql.ResultSet;

import EJB.IConnectEJBS;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MailSendingDAL {

	public static int insertmailsending(String email,String email_to,String title,String content)
	{
		int _result = 0;	
		try
		{
			String spname = "tb_mailsending_insert";
			String[]pfield = {"Email","Email_to","Title","Content"};
			Object[]pvalues = {email,email_to,title,content};
			IConnectEJBS con = new IConnectEJBS();
			Map<String, String> rs = new HashMap<String, String>();
			rs = con.ExecBoFunction(spname, pfield, pvalues);
			_result = Integer.parseInt(rs.get("result").toString());
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return _result;
	}
	

}
