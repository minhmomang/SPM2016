package BLL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.util.CheckAnnotationAdapter;

import DAL.CategoryPromotionDAL;
import EJB.ConnectDBD;
import Model.ItemProduct;

public class CategoryPromotionBLL {
	public static Map<String, Object> insert_product_to_promotion(ArrayList<ItemProduct> list_product,String catpromotion) throws ClassNotFoundException, InstantiationException, SQLException{
		Map<String, Object> result = new HashMap<String, Object>();	
		Connection con =  ConnectDBD.getConnection();
		con.setAutoCommit(false);
		try{
			Boolean check_exec = true;
			for(ItemProduct item : list_product){
				Map<String, Object> re = new HashMap<String, Object>();
				re=CategoryPromotionDAL.insert_product_to_promotion(con,item.getProduct_id(),catpromotion);
				 if(re!=null){
					 if(re.size()>0){
						 String f =re.get("f").toString();
						 if(f.equals("0")){
							 							 
						 }
						 else{
							 check_exec = false;
							 break;
						 }
					 }
					 else{
						 check_exec = false;
						 break;
					 }
				 }
				 else{
					 check_exec = false;
					 break;
				 }
			}
			if(check_exec==true){
				con.commit();
			}
			result.put("f",check_exec==true?0:1);	
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		
		return result;
	}
}
