
package DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.Introduction;

import EJB.IConnectEJBS;
public class IntroductionDAL {
		public static int insertintroduction(String info_intro)
		{
			IConnectEJBS con = new IConnectEJBS();
			int _result = 0;	
			try
			{
				String spname = "tb_introduction_insert_info_intro";
				String[]pfield = {"infointro"};
				Object[]pvalues = {info_intro};				
				Map<String,String> rs = new HashMap<String,String>();
				rs = con.ExecBoFunction(spname, pfield, pvalues);
				_result = Integer.parseInt(rs.get("result").toString());
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return _result;
		}

		public static Introduction get_intro() throws ClassNotFoundException, InstantiationException, SQLException{
			String spname = "tb_introduction_get";
			
			Introduction ob_intro=new Introduction();
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			rs = con.ExecBoFunctionReturnResutlSet(spname);
			for(Map<String,Object> item1 : rs){
				ob_intro.setIntro(item1.get("info_intro").toString());
				break;
			}			
			return ob_intro;
			
		}
}
