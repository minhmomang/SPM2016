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

import DAL.HelperDAL;
import DAL.MemberDAL;


import Helper.responseUtf8;
import Model.ItemModel;

@Controller
@RequestMapping(value="ExtraController")
public class ExtraController {
	@RequestMapping(value="get_payment_method",method=RequestMethod.GET)
	@ResponseBody
	public void get_payment_method(HttpServletResponse response) throws IOException{
		ArrayList<ItemModel> list =  HelperDAL.getPayment();	
		list = HelperDAL.getPayment();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {}.getType());	
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value="get_tranfer_method",method=RequestMethod.GET)
	@ResponseBody
	public void get_tranfer_method(HttpServletResponse response) throws IOException{
		ArrayList<ItemModel> list =  HelperDAL.getPayment();	
		list = HelperDAL.getdelivery();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<ItemModel>>() {}.getType());	
		JsonArray jsonArray = element.getAsJsonArray();
		responseUtf8.response_Utf8(response,jsonArray.toString());
	}
	@RequestMapping(value="check_email_exists_order",method=RequestMethod.GET)
	@ResponseBody
	public void check_email_exists_order(
			@RequestParam("email") String email,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String result="";
		int rs = MemberDAL.checkemailexist(email);
		result = String.valueOf(rs);
		responseUtf8.response_Utf8(response,result);
	}
}
