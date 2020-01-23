package home.room.port.user.service;

import java.util.Date;
import java.util.List;

import home.room.port.user.domain.LoginDTO;
import home.room.port.user.domain.UserVO;

public interface UserService {
	
	void register(UserVO userVO) throws Exception; //ȸ�� ���� ó��
	UserVO userView(String userId) throws Exception; //ȸ������ ��ȸ
	void userUpdate(UserVO userVO) throws Exception; //ȸ������ ����
	UserVO login(LoginDTO loginDTO) throws Exception; // �α��� ó��
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	UserVO checkLoginBefore(String value) throws Exception;
	List<UserVO> userList() throws Exception;
//	List<UserVO> userList(SearchUser searchUser) throws Exception;
	public void loginTimeUpdate(UserVO userVO) throws Exception;
	
}
