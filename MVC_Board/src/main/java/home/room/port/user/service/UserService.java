package home.room.port.user.service;

import java.util.Date;
import java.util.List;

import home.room.port.user.domain.LoginDTO;
import home.room.port.user.domain.UserVO;

public interface UserService {
	
	void register(UserVO userVO) throws Exception; //회원 가입 처리
	UserVO userView(String userId) throws Exception; //회원정보 조회
	void userUpdate(UserVO userVO) throws Exception; //회원정보 수정
	UserVO login(LoginDTO loginDTO) throws Exception; // 로그인 처리
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	UserVO checkLoginBefore(String value) throws Exception;
	List<UserVO> userList() throws Exception;
//	List<UserVO> userList(SearchUser searchUser) throws Exception;
	public void loginTimeUpdate(UserVO userVO) throws Exception;
	
}
