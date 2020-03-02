package home.room.port.upload.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleFileDAOImpl implements ArticleFileDAO {

	private static final String NAMESPACE = "home.room.port.mappers.upload.ArticleFileMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ArticleFileDAOImpl(SqlSession sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void addFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DAO : " +fileName);
		sqlSession.insert(NAMESPACE + ".addFile",fileName);
	}

	@Override
	public List<String> getArticleFiles(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".getArticleFiles",boardNo);
	}

	@Override
	public void deleteFiles(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE + ".deleteFiles",boardNo);
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE + ".deleteFile",fileName);
	}

	@Override
	public void replaceFile(String fileName, Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("fileName", fileName);
		paramMap.put("boardNo", boardNo);
		sqlSession.insert(NAMESPACE + ".replaceFile", paramMap);
	}

	@Override
	public void updateFileCnt(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateFileCnt",boardNo);
	}

}
