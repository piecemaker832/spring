package home.room.port.upload.presistence;

import java.util.List;

public interface ArticleFileDAO {
	
	//파일 추가
	void addFile(String fullName) throws Exception;
	List<String> getArticleFiles(Integer boardNo) throws Exception;
	void deleteFiles(Integer boardNo) throws Exception;
	
	//첨부파일 삭제/수정/개수 갱신
	void deleteFile(String fileName) throws Exception;
	void replaceFile(String fileName, Integer boardNo) throws Exception;
	void updateFileCnt(Integer boardNo) throws Exception;
	
}
