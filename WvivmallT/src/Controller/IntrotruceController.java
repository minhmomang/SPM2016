package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import DAL.IntroductionDAL;
import Helper.responseUtf8;
import Model.Introduction;

@Controller
@RequestMapping(value="IntrotruceController")
public class IntrotruceController {
	@RequestMapping(value="get_introduce",method=RequestMethod.GET)
	@ResponseBody
	public void get_introduce(HttpServletRequest request,HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, SQLException{
	
		Introduction item = IntroductionDAL.get_intro();
		responseUtf8.response_Utf8(response,item.getinfo_intro());
	}
}
