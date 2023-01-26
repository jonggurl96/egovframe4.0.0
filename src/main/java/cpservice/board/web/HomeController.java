package cpservice.board.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="home/*")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="login", method=RequestMethod.GET)
	public String nlogin() throws Exception {
		logger.info("not member, need to login");
		return "redirect:/user/login";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public void ajaxTest() {
		;
	}
}
