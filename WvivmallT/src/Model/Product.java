package Model;
/***********************************************************************
 * Module:  Product.java
 * Author:  HuynhChau
 * Purpose: Defines the Class Product
 ***********************************************************************/

import java.util.*;

import DAL.ProductDAL;

import java.sql.ResultSet;
import java.sql.SQLException;

/** @pdOid 2070a92b-266a-474d-a432-62555db0ccae */
public class Product {
   public static class ProductData {
		
		private int productIndex;
		private String productId;		
		private String productName;		
		private String producTypeId;		
		private String productImage;		
		private String productDes;		
		private Float productPrice;		
		private String productProviderId;		
		private Date productInputDate;
		private String productguide;
		private String productDescShort;
		private String productquantity;
		private String moreinfo;
		private String producttype;
		private String orderproduct;
		private int rownum;
		private String productimglarg;
		private String typeimglarg;
		private Float newPrice;
		private int pricePercent;
		private String catePromotionId;
		private int isPromo;
		private String percent_discount;
		private String price_promo;
		private Float price_old;
		String property;
		String color;
		String branch;
		
		
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public Float getPrice_old() {
			return price_old;
		}
		public void setPrice_old(Float price_old) {
			this.price_old = price_old;
		}
		public String getPrice_promo() {
			return price_promo;
		}
		public void setPrice_promo(String price_promo) {
			this.price_promo = price_promo;
		}
		public String getPercent_discount() {
			return percent_discount;
		}
		public void setPercent_discount(String percent_discount) {
			this.percent_discount = percent_discount;
		}
		public String getPrice_discount() {
			return price_discount;
		}
		public void setPrice_discount(String price_discount) {
			this.price_discount = price_discount;
		}
		private String price_discount;
		
		public int getIsPromo() {
			return isPromo;
		}
		public void setIsPromo(int isPromo) {
			this.isPromo = isPromo;
		}
		public String getCatePromotionId() {
			return catePromotionId;
		}
		public void setCatePromotionId(String catePromotionId) {
			this.catePromotionId = catePromotionId;
		}
		public String getTypeimglarg() {
			return typeimglarg;
		}
		public void setTypeimglarg(String typeimglarg) {
			this.typeimglarg = typeimglarg;
		}
		public String getProductimglarg() {
			return productimglarg;
		}
		public void setProductimglarg(String productimglarg) {
			this.productimglarg = productimglarg;
		}
		public int getProductIndex() {
			return productIndex;
		}
		public void setProductIndex(int productIndex) {
			this.productIndex = productIndex;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getProducTypeId() {
			return producTypeId;
		}
		public void setProducTypeId(String producTypeId) {
			this.producTypeId = producTypeId;
		}
		public String getProductImage() {
			return productImage;
		}
		public void setProductImage(String productImage) {
			this.productImage = productImage;
		}
		public String getProductDes() {
			return productDes;
		}
		public void setProductDes(String productDes) {
			this.productDes = productDes;
		}
		public Float getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(Float productPrice) {
			this.productPrice = productPrice;
		}
		public String getProductProviderId() {
			return productProviderId;
		}
		public void setProductProviderId(String productProviderId) {
			this.productProviderId = productProviderId;
		}
		public Date getProductInputDate() {
			return productInputDate;
		}
		public void setProductInputDate(Date productInputDate) {
			this.productInputDate = productInputDate;
		}
		public String getProductguide() {
			return productguide;
		}
		public void setProductguide(String productguide) {
			this.productguide = productguide;
		}
		public String getProductDescShort() {
			return productDescShort;
		}
		public void setProductDescShort(String productDescShort) {
			this.productDescShort = productDescShort;
		}
		public String getProductquantity() {
			return productquantity;
		}
		public void setProductquantity(String productquantity) {
			this.productquantity = productquantity;
		}
		public String getMoreinfo() {
			return moreinfo;
		}
		public void setMoreinfo(String moreinfo) {
			this.moreinfo = moreinfo;
		}
		public String getProducttype() {
			return producttype;
		}
		public void setProducttype(String producttype) {
			this.producttype = producttype;
		}
		public String getOrderproduct() {
			return orderproduct;
		}
		public void setOrderproduct(String orderproduct) {
			this.orderproduct = orderproduct;
		}
		public int getRownum() {
			return rownum;
		}
		public void setRownum(int rownum) {
			this.rownum = rownum;
		}
		public Float getNewPrice() {
			return newPrice;
		}
		public void setNewPrice(Float newPrice) {
			this.newPrice = newPrice;
		}
		public int getPricePercent() {
			return pricePercent;
		}
		public void setPricePercent(int pricePercent) {
			this.pricePercent = pricePercent;
		}
		
		
	}

}