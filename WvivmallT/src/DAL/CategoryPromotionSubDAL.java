package DAL;

import java.util.HashMap;
import java.util.Map;

import EJB.IConnectEJBS;

public class CategoryPromotionSubDAL {

	
	
	public static Map<String, Object> insert_category_promotion_sub(String type, String id, String name,
			String category_img, String ngay_ap_dung, String ngay_ket_thuc, String typedis, String valuedis,String parent_promo_id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String spname = "sp_tb_category_promotion_new_sub";
			String[] pfield = { "f", "p_type", "id", "cate", "p_category_img", "p_ngay_ap_dung", "p_ngay_ket_thuc",
					"p_typedis", "p_valuedis","p_parent_promo_id"

			};
			String[] ptype = { "INT", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR",
					"VARCHAR","VARCHAR" };
			Object[] pvalue = { "", type, id, name, category_img, ngay_ap_dung, ngay_ket_thuc, typedis, valuedis,parent_promo_id

			};
			int[] pdirec = { 1, 0, 0, 0, 0, 0, 0, 0, 0 ,0};
			IConnectEJBS con = new IConnectEJBS();
			result = con.ExecBoFunctionReturnList(spname, pfield, ptype, pvalue, pdirec);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
