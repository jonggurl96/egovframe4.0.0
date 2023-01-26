package cpservice.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class BoardInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(BoardInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		Object vo = request.getSession().getAttribute("loginInfo");
		if(vo == null) {
			logger.info("비회원 접근");
			response.sendRedirect("/user/login");
			return false;
		}
		String id = (String) vo;
		logger.info(id + " access......: " + request.getRequestURL());
		return true;
	}
	
	
}
