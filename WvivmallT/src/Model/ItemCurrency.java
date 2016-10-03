package Model;

public class ItemCurrency {
 private String	 currency_code;
 private String currency_name;
 public String getCurrency_name() {
	return currency_name;
}
public void setCurrency_name(String currency_name) {
	this.currency_name = currency_name;
}
private String  buy_cash;
 private String sold_out;
 private String buy_transfer;
public String getCurrency_code() {
	return currency_code;
}
public void setCurrency_code(String currency_code) {
	this.currency_code = currency_code;
}
public String getBuy_cash() {
	return buy_cash;
}
public void setBuy_cash(String buy_cash) {
	this.buy_cash = buy_cash;
}
public String getSold_out() {
	return sold_out;
}
public void setSold_out(String sold_out) {
	this.sold_out = sold_out;
}
public String getBuy_transfer() {
	return buy_transfer;
}
public void setBuy_transfer(String buy_transfer) {
	this.buy_transfer = buy_transfer;
}
 
 
}
