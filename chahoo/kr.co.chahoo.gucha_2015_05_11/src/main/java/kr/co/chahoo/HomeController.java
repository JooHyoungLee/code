package kr.co.chahoo;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Principal principal) {
		logger.info("Welcome home! ");
		//세션 여부 판별하여 페이지 리다이렉트
		if(principal != null)
		{
			if(principal.getName() != null)
			{
				return "redirect:/main/";
			}
			else
			{
				return "login";
			}
		}
		else
		{
			return "login";
		}
	}
}
