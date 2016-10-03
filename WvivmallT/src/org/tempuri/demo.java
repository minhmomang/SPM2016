package org.tempuri;

import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

public class demo {

	public static void main(String[] args) throws RemoteException {
		WebServiceSoapProxy call = new WebServiceSoapProxy();
		Boolean aa = call.sendMailToCustomer("ngocphung2002@gmail.com","12345", "12345");
		////System.out.println(aa);
	}
}
