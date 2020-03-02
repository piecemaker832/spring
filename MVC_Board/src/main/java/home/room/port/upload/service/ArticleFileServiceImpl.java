package home.room.port.upload.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.room.port.upload.persistence.ArticleFileDAO;


@Service
public class ArticleFileServiceImpl implements ArticleFileService {
	
	private final ArticleFileDAO articleFilesDAO;
	
	@Inject
	public ArticleFileServiceImpl(ArticleFileDAO articleFilesDAO) {
		// TODO Auto-generated constructor stub
		this.articleFilesDAO = articleFilesDAO;
	}
	
	//첨부파일목록
	@Override
	public List<String> getAritcleFiles(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return articleFilesDAO.getArticleFiles(boardNo);
	}
	
	
    @Transactional
    @Override
    public void deleteFile(String fileName, Integer boardNo) throws Exception {
        articleFilesDAO.deleteFile(fileName);
        articleFilesDAO.updateFileCnt(boardNo);
    }
}
