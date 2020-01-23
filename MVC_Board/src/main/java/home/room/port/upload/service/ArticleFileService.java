package home.room.port.upload.service;

import java.util.List;

public interface ArticleFileService {
	
	List<String> getAritcleFiles(Integer boardNo) throws Exception;
	
	// 파일 삭제
    void deleteFile(String fileName, Integer articleNo) throws Exception;
}
