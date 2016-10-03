package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import DAL.ProviderDAL;
import Helper.responseUtf8;
import Model.*;

@Controller
@RequestMapping(value="ProviderController")
public class ProviderController {
	@RequestMapping(value="get_list_provider",method=RequestMethod.GET)
	@ResponseBody
	public void get_list_provider(HttpServletResponse response) throws IOException{
		ArrayList<ItemModel> list = new ArrayList<ItemModel>();
		list = ProviderDAL.getlistProviderInfor();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {}.getType());	
		JsonArray jsonArray = element.getAsJsonArray();		
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}//end get_user_info
}
