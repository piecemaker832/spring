package home.room.port.board.presistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import home.room.port.board.domain.BoardVO;
import home.room.port.commons.paging.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	private static final String NAMESPACE = "home.room.port.mappers.board.BoardMapper";
	
	private SqlSession sqlSession;
	
	@Inject
	public BoardDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void create(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE+".create",boardVO);
	}

	@Override
	public BoardVO read(Integer boardNO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+".read",boardNO);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".update",boardVO);
	}

	@Override
	public void delete(Integer boardNO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE+".delete",boardNO);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".listAll");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".listSearch", searchCriteria);
	}

	@Override
	public int countSearchedArticles(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+".countSearchedArticles",searchCriteria);
	}

	@Override
	public void updateHit(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".updateHit",boardNo);
	}

	@Override
	public void replyCreate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE+".replyCreate", boardVO);
	}

	@Override
	public void replyUpdate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".replyUpdate",boardVO);
	}

}
