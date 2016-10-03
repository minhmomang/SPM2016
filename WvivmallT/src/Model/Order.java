package Model;
/***********************************************************************
 * Module:  TbOrder.java
 * Author:  HuynhChau
 * Purpose: Defines the Class TbOrder
 ***********************************************************************/

import java.util.*;
public class Order {
   
	private String orderId;
	private String email;
	private String productId;
	private Float quantity;
	private Float productPrice;
	private Float amount;
	private String paymentId;
	private String orderDate;
	private String orderStatus;
	private String note;
	private String phone;
	private String customername;
	private String address_delivery;
	private String delivery_method;
	private String payment_method;
	private String invoice;
	String payment_typeNL;
	String refund_typeNL;
	String orderStatusNL;
	public String getPayment_typeNL() {
		return payment_typeNL;
	}

	public void setPayment_typeNL(String payment_typeNL) {
		this.payment_typeNL = payment_typeNL;
	}

	public String getRefund_typeNL() {
		return refund_typeNL;
	}

	public void setRefund_typeNL(String refund_typeNL) {
		this.refund_typeNL = refund_typeNL;
	}

	public String getOrderStatusNL() {
		return orderStatusNL;
	}

	public void setOrderStatusNL(String orderStatusNL) {
		this.orderStatusNL = orderStatusNL;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getAddress_delivery() {
		return address_delivery;
	}

	public void setAddress_delivery(String address_delivery) {
		this.address_delivery = address_delivery;
	}

	public String getDelivery_method() {
		return delivery_method;
	}

	public void setDelivery_method(String delivery_method) {
		this.delivery_method = delivery_method;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}


}