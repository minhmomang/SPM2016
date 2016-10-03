package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import DAL.CategoryPromotionSubDAL;
import Helper.ErrorMesage;
import Helper.responseUtf8;




@Controller
@RequestMapping(value = "CategoryPromotionSubController")
public class CategoryPromotionSubController {
	

	@RequestMapping(value = "insert_category_promotion_sub", method = RequestMethod.GET)
	@ResponseBody
	public void insert_category_promotion_sub(@RequestParam("type") String type, @RequestParam("id") String id,
			@RequestParam("name") String name, @RequestParam("category_img") String category_img,
			@RequestParam("ngay_ap_dung") String ngay_ap_dung, @RequestParam("ngay_ket_thuc") String ngay_ket_thuc,
			@RequestParam("typedis") String typedis, @RequestParam("valuedis") String valuedis,@RequestParam("parent_promo_id") String parent_promo_id,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> obj = new HashMap<String, String>();
		//////System.out.println(name);
		result = CategoryPromotionSubDAL.insert_category_promotion_sub(type, id, name, category_img, ngay_ap_dung,
				ngay_ket_thuc, typedis, valuedis,parent_promo_id);
		if (result != null) {
			if (result.size() > 0) {
				String f = result.get("f").toString();
				String mes = ErrorMesage.getMesageError(Integer.parseInt(f));
				obj.put("result", f);
				obj.put("message",mes);

			}
		}
		String json = new Gson().toJson(obj);
		responseUtf8.response_Utf8(response, json);

	}
}
