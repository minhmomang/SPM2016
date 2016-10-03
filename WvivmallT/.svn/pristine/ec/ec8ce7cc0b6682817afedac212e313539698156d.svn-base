package Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.ItemCurrency;

public class ReadService {

	public static ArrayList<ItemCurrency> Get_Current_VietcomBank(HttpServletRequest request){
		ArrayList<ItemCurrency> list = new ArrayList<ItemCurrency>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("ExrateXML.aspx")
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			data = data.substring(1, data.length());
			String url = request.getRealPath("/XML");
			url +="/curency.xml";
			writing(url,data);
			list = read_xml(url);
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	public static void writing(String url,String data) {
		try {
			//Whatever the file path is.
			File statText = new File(url);
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);    
			Writer w = new BufferedWriter(osw);	  
			w.write(data);
			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://vietcombank.com.vn/ExchangeRates/").build();
	}

	private static ArrayList<ItemCurrency> read_xml(String url)
	{
		ArrayList<ItemCurrency> list = new ArrayList<ItemCurrency>();
		try{			
			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);				
			doc.getDocumentElement().normalize();				
			NodeList nList = doc.getElementsByTagName("Exrate");	

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);					
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;		
					ItemCurrency item = new ItemCurrency();
					item.setCurrency_code(eElement.getAttribute("CurrencyCode").toString());
					item.setCurrency_name(eElement.getAttribute("CurrencyName").toString());				
					item.setBuy_cash(eElement.getAttribute("Buy").toString());
					item.setBuy_transfer(eElement.getAttribute("Transfer").toString());
					item.setSold_out(eElement.getAttribute("Sell").toString());
					list.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}	
}
