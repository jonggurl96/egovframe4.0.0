package cpservice.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	/**
	 * 로그인 후 자동 실행
	 * ModelAndView modelAndView: 컨트롤러에서 생성된 Model 객체 저장 파라미터
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		logger.info("postHandle() .......");
		ModelMap model = modelAndView.getModelMap();
		HttpSession session = request.getSession();
		
		Object loginInfo = model.get("loginInfo");
		
		if(loginInfo == null) {
			logger.info("login failed");
			model.addAttribute("msg", "로그인 실패");
			modelAndView.setViewName("redirect:/user/login");
			
		} else {
			logger.info("login success .......");
			session.setAttribute("loginInfo", loginInfo);
		}
		
	}
	
	
}
