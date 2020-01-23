package home.room.port.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import home.room.port.user.domain.UserVO;
import home.room.port.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRegisterController {
	private final UserService userService;
	
	@Inject
	public UserRegisterController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	//회원가입 페이지
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGET() throws Exception{
		return "/user/register";
	}
	
	//회원가입 처리
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
		String hasedPw = BCrypt.hashpw(userVO.getUserPass(),BCrypt.gensalt());
		userVO.setUserPass(hasedPw);
		userService.register(userVO);
		redirectAttributes.addFlashAttribute("msg","REGISTERD");
		return "redirect:/user/login";
	}
	
	//회원정보 수정페이지 이동
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	public String userUpdateGET() throws Exception {
//		
//		HttpSession session = request.getSession();
//		UserVO userVO = (UserVO) session.getAttribute("login");
//		model.addAttribute("user",userService.userView(userVO.getUserId()));
//		
		return "/user/userUpdate";
	}
	
	//회원정보 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String userUpdatePOST(UserVO userVO, RedirectAttributes redirectAttributes) throws Exception {
		userService.userUpdate(userVO);
		redirectAttributes.addFlashAttribute("msg","UPDATE");
		return "redirect:/user/login";
	}
	
	//회원정보목록페이지 이동
	@RequestMapping(value="/userList", method=RequestMethod.GET)
	public String userList(Model model) throws Exception {
		model.addAttribute("user",userService.userList());
		return "/user/userList";
	}
	
	
}
