package EJB;

import java.util.Arrays;

import org.json.JSONArray;

public class ExtraJBOSS {
	public static String jboss_name = "VivmallEJB";
	public static String urlservicedb = "http://10.0.10.24:8080/SrvEJBVivmallT/RestSrv/SrvConnect/";
	public static String ConvertArrayToJson(String[] parray) {
		JSONArray mJSONArray = new JSONArray(Arrays.asList(parray));
		return mJSONArray.toString();
	}
	public static String ConvertArrayObjToJson(Object[] parray) {
		 String[]pstr = new String[parray.length];
	  	 for(int i=0;i<parray.length;i++){
	  		 if(parray[i]==null || parray[i].toString().length()==0){
	  			pstr[i] = "NULL";	 
	  		 }
	  		 else{
	  			pstr[i] = String.valueOf(parray[i]);	
	  		 }	  		
	  	 }
		JSONArray mJSONArray = new JSONArray(Arrays.asList(pstr));
		return mJSONArray.toString();
	}
	public static String ConvertArrayObjToInt(int[] parray) {
		 String[]pstr = new String[parray.length];
	  	 for(int i=0;i<parray.length;i++){
	  		pstr[i] = String.valueOf(parray[i]);
	  	 }
		 JSONArray mJSONArray = new JSONArray(Arrays.asList(pstr));
		 return mJSONArray.toString();
	}
}
