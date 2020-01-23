package home.room.port.user.presistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import home.room.port.user.domain.LoginDTO;
import home.room.port.user.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final String NAMESPACE = "home.room.port.mappers.user.UserMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public UserDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void register(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".register", userVO);
	}

	@Override
	public UserVO login(LoginDTO loginDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".login", loginDTO);
	}
	
	//�α��� ���� ó��
	@Override
	public void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId",userId);
		paramMap.put("sessionId",sessionId);
		paramMap.put("sessionLimit", sessionLimit);
		
		sqlSession.update(NAMESPACE + ".keepLogin", paramMap);
	}
	
	// ����Ű ����
	@Override
	public UserVO checkUserWithSessionKey(String value) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".checkUserWithSessionKey", value);
	}
	
	//ȸ������ ����
	@Override
	public void userUpdate(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".userUpdate",userVO);
	}

	@Override
	public UserVO userView(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".userView", userId);
	}

	@Override
	public List<UserVO> userList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".userList");
	}

	@Override
	public void loginTimeUpdate(UserVO userVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".loginTimeUpdate",userVO);
	}

}
