package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import DAL.AccessDAL;
import DAL.HelperDAL;
import DAL.ProductTypeDAL;
import Helper.responseUtf8;
import Model.ItemModel;
import Model.ShoppingCartItem;
@Controller
@RequestMapping(value="AccessController")
public class AccessController {
	
	@RequestMapping(value="check_access",method=RequestMethod.GET)
	@ResponseBody
	public void check_access(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		// if null => create new
		if(session.getAttribute("access")==null)
		{			
			session.setAttribute("access",0);
			//insert
			int result = AccessDAL.insert_access();
			responseUtf8.response_Utf8(response, "0");
		}
		else{
			responseUtf8.response_Utf8(response, "1");
		}
	}
	@RequestMapping(value="get_access",method=RequestMethod.GET)
	@ResponseBody
	public void get_access(
			HttpServletResponse response) throws IOException{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		list = HelperDAL.get_access();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {}.getType());	
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response, jsonArray.toString());
	}
}
