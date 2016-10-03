package Model;

/***********************************************************************
 * Module:  TbIntroduction.java
 * Author:  HuynhChau
 * Purpose: Defines the Class TbIntroduction
 ***********************************************************************/

import java.util.*;

/** @pdOid a11ceb3d-d8f9-4b06-b71c-d24f374ee6d3 */
public class Introduction {
   /** @pdOid 75a18ede-7e91-4af6-ae53-6c3c196b0bdb */
   private String infoIntro;
   
   public Introduction(){}
   /** @pdOid 708606e1-22d2-4bed-849a-ccaf27a2ab83 */
   public void insertIntro() {
      // TODO: implement
   }
   
   /** @pdOid b6e2ea98-d6f2-4507-9775-797151dd119a */
   public void updateIntro() {
      // TODO: implement
   }
   
   /** @pdOid f30865ec-9c92-400d-9c5c-1088b3c84ba3 */
   public Introduction getIntro() {
      // TODO: implement
      return this;
   }
   public String getinfo_intro(){
	   return this.infoIntro;
   }
   public void setIntro(String info_intro){
	   this.infoIntro=info_intro;
   }

}