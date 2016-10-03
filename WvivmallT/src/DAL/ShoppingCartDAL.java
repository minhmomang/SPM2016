package DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import EJB.IConnectEJBS;
import Model.ShoppingCartItem;
import Model.Product.ProductData;

public class ShoppingCartDAL {
	public static int save_item_shoppingcart(ShoppingCartItem item)
			throws ClassNotFoundException, InstantiationException, SQLException {
		
		try{
			String col = "colHistoryShoppingcart";
			String date = CommentDAL.get_current_date2();	
			String time = CommentDAL.get_time();
			String[]pf  = {
				"ID","Name","Quantity","Date","Hour"
			};
			Object[]pv = {
				item.getID(),item.getName(),item.getQuantity(),date,time
			};
			//role: 1=> customer, 0=> manager
			int _rs = ConnectDBMongo.insert(col, pf, pv);		
			if(_rs==0){
				return 0;
			}
			else{
				return -1;
			}			
		}catch(Exception ex){
			return -1;
		}		
	}
	public static ShoppingCartItem get_price_product_item(String p_product_id) {
		ShoppingCartItem item = new ShoppingCartItem();
		List<Map<String, Object>> listob = new ArrayList<Map<String, Object>>();
		try {
			String spname = "get_full_price_of_product";
			String[] pfield = {"p_product_id"};
			Object[] pvalues = {p_product_id};
			IConnectEJBS con = new IConnectEJBS();
			listob = con.ExecBoFunctionReturnResutlSet(spname, pfield, pvalues);
		
				Map<String, Object> temp = listob.get(0);
	
				item.setID(temp.get("p_product_id").toString());
				item.setFinal_price(Float.parseFloat(temp.get("price_final").toString()));
				item.setPrice(Float.parseFloat(temp.get("price").toString()));
				item.setDiscount_percent(Integer.parseInt(temp.get("price_percent").toString()));
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}
	
}
