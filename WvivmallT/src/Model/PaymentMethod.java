package Model;
/***********************************************************************
 * Module:  PaymentMethod.java
 * Author:  HuynhChau
 * Purpose: Defines the Class TbPaymentMethod
 ***********************************************************************/

import java.util.*;

/** @pdOid e22f5722-31d1-463b-b348-bee35dd362ae */
public class PaymentMethod {
   /** @pdOid 84fa9a07-7bea-4be9-b435-20f0e71b62ca */
   private String paymentId;
   /** @pdOid 461e8689-638d-45d5-a560-c8da62b3e73b */
   private String paymentName;
   /** @pdOid 874acd02-ae83-4d00-bc50-9a1a45ee8d45 */
   private String paymentDesc;
   
   /** @pdRoleInfo migr=no name=TbOrder assc=association5 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Order> Order;
   
   /** @pdOid 52260731-ba01-4b4e-bb89-55c750e246bf */
   public void addPayment() {
      // TODO: implement
   }
   
   /** @pdOid 73183fc1-10be-499f-86c7-d390fdb78d1d */
   public void modifiyPayment() {
      // TODO: implement
   }
   
   /** @pdOid 736c2336-eea4-4397-a763-370df8dd390a */
   public List getPayment() {
      // TODO: implement
      return null;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Order> getTbOrder() {
      if (Order == null)
         Order = new java.util.HashSet<Order>();
      return Order;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorTbOrder() {
      if (Order == null)
         Order = new java.util.HashSet<Order>();
      return Order.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newTbOrder */
   public void setTbOrder(java.util.Collection<Order> newTbOrder) {
      removeAllTbOrder();
      for (java.util.Iterator iter = newTbOrder.iterator(); iter.hasNext();)
         addTbOrder((Order)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newTbOrder */
   public void addTbOrder(Order newTbOrder) {


   }
   
   /** @pdGenerated default remove
     * @param oldTbOrder */
   public void removeTbOrder(Order oldTbOrder) {


   }
   
   /** @pdGenerated default removeAll */
   public void removeAllTbOrder() {


   }

}