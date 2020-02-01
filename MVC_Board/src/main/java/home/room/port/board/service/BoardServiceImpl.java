package home.room.port.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import home.room.port.board.domain.BoardVO;
import home.room.port.board.presistence.BoardDAO;
import home.room.port.commons.paging.SearchCriteria;
import home.room.port.upload.presistence.ArticleFileDAO;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	private final ArticleFileDAO articleFileDAO;
	
	public BoardServiceImpl(BoardDAO boardDAO, ArticleFileDAO articleFileDAO) {
		// TODO Auto-generated constructor stub
		this.boardDAO = boardDAO;
		this.articleFileDAO = articleFileDAO;
	}
	
	@Transactional
	@Override
	public void create(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		
		boardDAO.create(boardVO);
		String[] files = boardVO.getFiles();
		
		if(files == null)
			return;

		// 게시글 첨부파일 입력처리
		for(String fileName : files) 
			articleFileDAO.addFile(fileName);
		
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer boardNO) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.read(boardNO);
	}
	
	@Transactional
	@Override
	public void update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.update(boardVO);
	}
	
	@Transactional
	@Override
	public void delete(Integer boardNO) throws Exception {
		// TODO Auto-generated method stub
		articleFileDAO.deleteFiles(boardNO); //첨부파일 삭제
		boardDAO.delete(boardNO);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.listAll();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.listSearch(searchCriteria);
	}

	@Override
	public int countSearchedArticles(SearchCriteria searchCriteria) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.countSearchedArticles(searchCriteria);
	}

	@Override
	public void updateHit(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.updateHit(boardNo);
	}

	@Override
	public void responseCreate(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		boardDAO.responseUpdate(boardVO);
		System.out.println(boardVO.getBoardGrplvl());
		boardDAO.responseCreate(boardVO);
	}


}
