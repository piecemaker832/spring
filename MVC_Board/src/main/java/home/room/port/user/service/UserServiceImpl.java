package home.room.port.user.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import home.room.port.user.domain.LoginDTO;
import home.room.port.user.domain.UserVO;
import home.room.port.user.presistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	private final UserDAO userDAO;
	
	@Inject
	public UserServiceImpl(UserDAO userDAO) {
		// TODO Auto-generated constructor stub
		this.userDAO = userDAO;
	}
	
	@Override
	public void register(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.register(userVO);
	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.login(loginDTO);
	}

	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		// TODO Auto-generated method stub
		userDAO.keepLogin(userId, sessionId, sessionLimit);
	}

	@Override
	public UserVO checkLoginBefore(String value) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.checkUserWithSessionKey(value);
	}

	@Override
	public void userUpdate(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.userUpdate(userVO);
	}

	@Override
	public UserVO userView(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.userView(userId);
	}

	@Override
	public List<UserVO> userList() throws Exception {
		// TODO Auto-generated method stub
		return userDAO.userList();
	}

	@Override
	public void loginTimeUpdate(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		userDAO.loginTimeUpdate(userVO);
	}


}
