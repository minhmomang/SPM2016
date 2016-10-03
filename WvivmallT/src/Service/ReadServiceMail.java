package Service;

import java.net.URI;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.tempuri.WebServiceSoapProxy;

import Helper.FlagMain;


public class ReadServiceMail {
	public static int  SendingFromgmail(String tomail,String title,String message,HttpServletRequest request){		
		WebServiceSoapProxy call = new WebServiceSoapProxy();
		try {
			Boolean data = call.sendMailToCustomer(tomail,message, title);
			if(data==true){
				return 0;
			}
			else{
				return 1;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
}
