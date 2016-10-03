package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import BLL.Aboutbll;
import DAL.OrderDAL;
import Helper.Extra;
import Helper.responseUtf8;
import Model.Order;
import Model.OrderDetail;
import Service.ReadServiceMail;

@Controller
@RequestMapping(value = "AboutController")
public class AboutController {
	@RequestMapping(value = "save_about", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void save_about(@RequestBody final String json_subject, HttpServletResponse response) throws IOException {

		String result = Aboutbll.insert_about(json_subject);
		responseUtf8.response_Utf8(response, result);
	}

	@RequestMapping(value = "get_about", method = RequestMethod.GET)
	@ResponseBody
	public void get_about(@RequestParam("lang") String lang, HttpServletResponse response) throws IOException {
		String result = Aboutbll.get_data(lang);
		responseUtf8.response_Utf8(response, result);
	}// end get_user_info

	@RequestMapping(value = "get_about_client", method = RequestMethod.GET)
	@ResponseBody
	public void get_about_client(HttpServletResponse response, @RequestParam("lang") String lang) throws IOException {
		String result = Aboutbll.get_about_client(lang);
		responseUtf8.response_Utf8(response, result);
	}// end get_user_info

	@RequestMapping(value = "demo", method = RequestMethod.GET)
	@ResponseBody
	public void demo(HttpServletResponse response, HttpServletRequest request) throws IOException {
		ServletContext servl = request.getServletContext();
		String urltext = request.getRealPath("/upload/txt");
		urltext += "/order.txt";
		String content_text = Extra.readFile(urltext);
		// get info order
		String order_id = "OD1607280000000122";
		Order item_order = OrderDAL.getInfoOrder(order_id);
		//
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date order_date = null;
		try {
			order_date = dt.parse(item_order.getOrderDate().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content_text = content_text.replace("@orderid", order_id);
		content_text = content_text.replace("@date_order", dt.format(order_date));
		content_text = content_text.replace("@customer_name", item_order.getCustomername());
		content_text = content_text.replace("@customer_email",
				order_id.equals(item_order.getEmail()) ? "NO" : item_order.getEmail());
		content_text = content_text.replace("@phone", item_order.getPhone());
		content_text = content_text.replace("@customer_address", item_order.getAddress_delivery());
		content_text = content_text.replace("@phone", item_order.getPhone());
		content_text = content_text.replace(" @customer_payment", item_order.getPayment_method());
		ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
		list = OrderDAL.get_orderdetail_by_id(order_id);
		String order_content = "";
		float total_amount = 0;
		for(OrderDetail item : list){
			String row = "<tr>";
			row+="<td>"+item.getProduct_name()+"</td><td>"+Extra.format_currency(String.valueOf(item.getPrice()))+" VND </td><td>"+item.getQuantity()+"</td><td>0</td><td>"+Extra.format_currency(String.valueOf(item.getAmount()))+" VND</td>";					
			row+="</tr>";
			order_content+=row;
			total_amount+=item.getAmount();
		}
		content_text = content_text.replace("@content_order", order_content);
		content_text = content_text.replace("@customer_amount", Extra.format_currency(String.valueOf(total_amount))+" VND");
		//
		String title = "Đơn hàng #" + order_id + " đã sẵn sàng để giao đến quý khách";
		if(!item_order.getEmail().equals(order_id)){
			int rs = ReadServiceMail.SendingFromgmail(item_order.getEmail(), title, content_text, request);
			////System.out.println("AAAAAAA");
		}	
	}// end get_user_info
}
