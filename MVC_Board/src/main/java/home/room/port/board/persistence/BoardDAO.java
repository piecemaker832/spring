package home.room.port.board.persistence;

import java.util.List;

import home.room.port.board.domain.BoardVO;
import home.room.port.commons.paging.SearchCriteria;

public interface BoardDAO {
	void create(BoardVO boardVO) throws Exception; // 게시글 작성
	BoardVO read(Integer boardNO) throws Exception; //게시글 조회
	void update(BoardVO boardVO) throws Exception; // 게시글 수정
	void delete(Integer boardNO) throws Exception; //게시글 삭제
	List<BoardVO> listAll() throws Exception; //게시글 리스트 출력
	List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception;
	int countSearchedArticles(SearchCriteria searchCriteria) throws Exception;
	void updateHit(Integer boardNo) throws Exception;
	void responseCreate(BoardVO boardVO) throws Exception; //답글 등록
	void responseUpdate(BoardVO boardVO) throws Exception; //답글 번호 수정
}
