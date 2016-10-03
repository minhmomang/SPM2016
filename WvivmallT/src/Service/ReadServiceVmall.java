package Service;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;

import Model.ItemMember;

public class ReadServiceVmall {
	public static String  check_login(String email,String pass){		
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("check_login")
					.queryParam("email",email)
					.queryParam("pass",pass)
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			return data;
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return "";
	}	
	public static void main(String[] args) {
		String data = check_login("ngocphung2002@gmail.com","123");
		////System.out.println(data);
		
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(ModelService.get_url_customer()+"SrvConnect").build();
	}
}
