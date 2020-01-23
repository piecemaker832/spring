package home.room.port.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	//controller의 handler가 끝나면 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("user");
		
		if(userVO != null) {
			logger.info("new login success");
			httpSession.setAttribute(LOGIN, userVO);
//			response.sendRedirect("/port");
			
			if(request.getParameter("useCookie") != null) {
				logger.info("remember me . . .");
				//쿠키 생성
				Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
				loginCookie.setPath("/port");
				loginCookie.setMaxAge(60*60*24*7); //일주일
				//전송
				response.addCookie(loginCookie);
			}
			
			Object destination = httpSession.getAttribute("destination");
			response.sendRedirect(destination != null ? (String) destination : "/port");
		}
		
	}
	
	//controller로 보내기 전에 처리
	//반환이 false면 controller에 요청하지 않음
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession();
		//기존의 로그인 정보 제거
		if(httpSession.getAttribute(LOGIN) != null) {
			logger.info("clear login data before");
			httpSession .removeAttribute(LOGIN);
		}
		
		return true;
	}
}
