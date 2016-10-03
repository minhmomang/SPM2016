package Model;

import java.util.ArrayList;

public class ShoppingCartItem {
	public String ID;
	public String Name;
	public double Quantity;
	public Float Price;
	public String ImageUrl;
	
	
	public int discount_percent;
	public Float final_price;
	
	
	
	
	public int getDiscount_percent() {
		return discount_percent;
	}
	public void setDiscount_percent(int discount_percent) {
		this.discount_percent = discount_percent;
	}
	public Float getFinal_price() {
		return final_price;
	}
	public void setFinal_price(Float final_price) {
		this.final_price = final_price;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getQuantity() {
		return Quantity;
	}
	public void setQuantity(double quantity) {
		Quantity = quantity;
	}
	public Float getPrice() {
		return Price;
	}
	public void setPrice(Float price) {
		Price = price;
	}
	public void setImageUrl(String url)
	{
		this.ImageUrl = url;
	}
	public String getImageUrl()
	{
		return this.ImageUrl;
	}
	public  static void UpdateShoppingCart(ArrayList<ShoppingCartItem> list,String ID,int quantity)
	{		
		for(ShoppingCartItem item : list)
		{
			if(item.getID().equals(ID))
			{
				item.setQuantity(item.getQuantity()+quantity);
				break;
			}
		}		
	}
	public  static void UpdateShoppingCart_in_knock_out(ArrayList<ShoppingCartItem> list,String ID,int quantity)
	{		
		for(ShoppingCartItem item : list)
		{
			if(item.getID().equals(ID))
			{
				item.setQuantity(quantity);
				break;
			}
		}		
	}
	
	public  static void RemoveItemOnShoppingCart(ArrayList<ShoppingCartItem> list,String ID)
	{		
		int i  = -1;
		int rowindex = -1;
		for(ShoppingCartItem item : list)
		{
			i++;
			if(item.getID().equals(ID))
			{	
				rowindex = i;
				break;
			}
			
		}
		list.remove(rowindex);
	}
	public static Boolean CheckProductExists(ArrayList<ShoppingCartItem> list,String ID)
	{
		Boolean check = false;	
		for(ShoppingCartItem item : list)
		{
			if(item.getID().equals(ID))
			{
				check = true;
				break;
			}
		}
		return check;
	}
	public static double get_total_amount(ArrayList<ShoppingCartItem> list){
		double total_amount =0;
		for(ShoppingCartItem item : list)
		{
			total_amount +=item.getPrice()*item.getQuantity();
		}
		return total_amount;
	}
}
