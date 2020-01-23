package home.room.port.commons.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import home.room.port.user.domain.UserVO;
import home.room.port.user.service.UserService;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginAfterInterceptor.class);
	
	@Inject
	private UserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath()+"/user/loginAfter");
			return false;
		}
		
		return true;
	}
}
