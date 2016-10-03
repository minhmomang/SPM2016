package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import Helper.responseUtf8;
import Model.ItemCurrency;
import Service.ReadService;
@Controller
@RequestMapping(value="CurrencyController")
public class CurrencyController {
	@RequestMapping(value="get_currentcy",method=RequestMethod.GET)
	@ResponseBody
	public void get_about_client(HttpServletResponse response,HttpServletRequest request) throws IOException{
		ArrayList<ItemCurrency> list=new ArrayList<ItemCurrency> ();
		list=ReadService.Get_Current_VietcomBank(request);
		String json = new Gson().toJson(list); 
		responseUtf8.response_Utf8(response,json);
	}
	

}
