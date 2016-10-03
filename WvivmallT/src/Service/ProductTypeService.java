package Service;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import DAL.ProductDAL;
import Model.ItemBranch;
import Model.ItemColor;
import Model.ItemModel;
import Model.ItemProductType;
import Model.ItemProperty;
import Model.ProductType;
import Model.Product.ProductData;

public class ProductTypeService {
	public static ArrayList<ItemProductType> get_list_product_type_parent(){
		ArrayList<ItemProductType> list = new ArrayList<ItemProductType>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("Get_parent_product_type")
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<ItemProductType>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	public static ArrayList<ItemProductType> get_list_product_type_sub(String parent){
		ArrayList<ItemProductType> list = new ArrayList<ItemProductType>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("get_list_product_type_sub")
					.queryParam("parent", parent)
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<ItemProductType>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	public static ArrayList<ItemProperty> get_property(String product_type){
		ArrayList<ItemProperty> list = new ArrayList<ItemProperty>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("get_property")
					.queryParam("product_type", product_type)
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<ItemProperty>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	public static ArrayList<ItemColor> get_color(String product_type){
		ArrayList<ItemColor> list = new ArrayList<ItemColor>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("get_color")
					.queryParam("product_type", product_type)
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<ItemColor>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	public static ArrayList<ItemBranch> get_branch(String product_type){
		ArrayList<ItemBranch> list = new ArrayList<ItemBranch>();
		try{
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			WebTarget target = client.target(getBaseURI());
			//read service
			String data = target.path("get_branch")
					.queryParam("product_type", product_type)
					.request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			
			if(data.length()>0){
				Gson gson = new Gson();
				list = gson.fromJson(data, new TypeToken<ArrayList<ItemBranch>>() {}.getType());				
				return list;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return list;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(ModelService.get_url_product_type()+"SrvConnect").build();
	}
	public static void main(String[] args) {
		ArrayList<ItemProperty> list = new ArrayList<ItemProperty>();
		list = ProductTypeService.get_property("0401");	
		
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemProperty>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		System.out.println(jsonArray.toString());
	}
}
