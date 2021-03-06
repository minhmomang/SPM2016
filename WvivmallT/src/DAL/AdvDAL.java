package DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import EJB.IConnectEJBS;

import Model.SlideModel;

public class AdvDAL {

	public static ArrayList<SlideModel> get_list_slide() {
		String query = null;

		ArrayList<SlideModel> list = new ArrayList<SlideModel>();
		try {
			query = "SELECT  id,img,status FROM tb_adv order by id desc";
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			rs = con.GetDataReturnResultSet(query);
			int num = 0;
			for (Map<String, Object> item1 : rs) {
				SlideModel item = new SlideModel();
				item.setId(item1.get("id").toString());
				item.setNum(String.valueOf(num));
				item.setName(item1.get("img").toString());
				item.setStatus(item1.get("status").toString());
				list.add(item);
				num++;
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static ArrayList<SlideModel> get_list_slide_active() {
		String query = null;
		ArrayList<SlideModel> list = new ArrayList<SlideModel>();
		try {
			query = "SELECT  id,img,status FROM tb_adv where status = 0";
			IConnectEJBS con = new IConnectEJBS();
			List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
			rs = con.GetDataReturnResultSet(query);
			int num = 0;
			for (Map<String, Object> item1 : rs) {
				SlideModel item = new SlideModel();
				item.setId(item1.get("id").toString());
				item.setNum(String.valueOf(num));
				item.setName(item1.get("img").toString());
				item.setStatus(item1.get("status").toString());
				list.add(item);
				num++;
			}

		} catch (Exception ex) {
			////System.out.println(ex.getMessage());
		}
		return list;
	}

	public static Map<String, Object> save_slide(SlideModel a) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbadv_insert";
			String[] pfield = {

			"f", "p_name", "p_creator" };
			String[] ptype = { "INT", "VARCHAR", "VARCHAR" };
			Object[] pvalue = { "", a.getName(), a.getCreator() };
			int[] pdirec = { 1, 0, 0

			};
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype,
					pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Map<String, Object> delete_slide(String str) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbadv_delete";
			String[] pfield = { "str", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str, ""

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

	public static Map<String, Object> lock(String str) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbadv_lock";
			String[] pfield = { "str", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str, ""

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

	public static Map<String, Object> unlock(String str) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tbadv_unlock";
			String[] pfield = { "str", "f"

			};
			String[] ptype = { "TEXT", "INT" };
			Object[] pvalue = { str, ""

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
