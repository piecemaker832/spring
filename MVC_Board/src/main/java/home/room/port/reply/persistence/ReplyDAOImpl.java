package home.room.port.reply.presistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import home.room.port.commons.paging.Criteria;
import home.room.port.reply.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	private static final String NAMESPACE = "home.room.port.mappers.reply.ReplyMapper";
	
	private SqlSession sqlSession;
	
	@Inject
	public ReplyDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<ReplyVO> list(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE +"list",boardNo);
	}

	@Override
	public void create(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE+".create",replyVO);
	}

	@Override
	public void update(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".update",replyVO);
	}

	@Override
	public void delete(Integer replyNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE+".delete",replyNo);
	}

	@Override
	public List<ReplyVO> listPaging(Integer boardNo, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNo", boardNo);
		paramMap.put("criteria", criteria);
		
		return sqlSession.selectList(NAMESPACE+".listPaging",paramMap);
	}

	@Override
	public int countReply(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+".countReply",boardNo);
	}

}
