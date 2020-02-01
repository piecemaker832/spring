package home.room.port.board.presistence;

import java.util.List;

import home.room.port.board.domain.BoardVO;
import home.room.port.commons.paging.SearchCriteria;

public interface BoardDAO {
	void create(BoardVO boardVO) throws Exception; // �Խñ� �ۼ�
	BoardVO read(Integer boardNO) throws Exception; //�Խñ� ��ȸ
	void update(BoardVO boardVO) throws Exception; // �Խñ� ����
	void delete(Integer boardNO) throws Exception; //�Խñ� ����
	List<BoardVO> listAll() throws Exception; //�Խñ� ����Ʈ ���
	List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception;
	int countSearchedArticles(SearchCriteria searchCriteria) throws Exception;
	void updateHit(Integer boardNo) throws Exception;
	void responseCreate(BoardVO boardVO) throws Exception; //��� ���
	void responseUpdate(BoardVO boardVO) throws Exception; //��� ��ȣ ����
}
