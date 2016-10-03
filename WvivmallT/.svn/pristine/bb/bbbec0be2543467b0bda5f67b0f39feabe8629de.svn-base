package DAL;

import java.util.HashMap;
import java.util.Map;

import EJB.IConnectEJBS;

public class CatgoryDAL {
	public static Map<String, Object> Unvisible_Catgory(String str_catgory) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbcatgory_unvisible";
			String[] pfield = { "id", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str_catgory, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static Map<String, Object> visible_Catgory(String str_catgory) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbcatgory_visible";
			String[] pfield = { "id", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str_catgory, ""

			};
			int[] pdirec = { 0, 1 };
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
