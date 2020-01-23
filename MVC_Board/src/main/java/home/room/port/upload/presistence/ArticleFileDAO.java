package home.room.port.upload.presistence;

import java.util.List;

public interface ArticleFileDAO {
	
	//���� �߰�
	void addFile(String fullName) throws Exception;
	List<String> getArticleFiles(Integer boardNo) throws Exception;
	void deleteFiles(Integer boardNo) throws Exception;
	
	//÷������ ����/����/���� ����
	void deleteFile(String fileName) throws Exception;
	void replaceFile(String fileName, Integer boardNo) throws Exception;
	void updateFileCnt(Integer boardNo) throws Exception;
	
}
