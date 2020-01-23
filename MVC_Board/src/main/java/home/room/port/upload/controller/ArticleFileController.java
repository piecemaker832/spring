package home.room.port.upload.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import home.room.port.commons.util.UploadFileUtils;
import home.room.port.upload.service.ArticleFileService;


@RestController
@RequestMapping("/article/file")
public class ArticleFileController {
	
	private final ArticleFileService articleFileService;
	
	@Inject
	public ArticleFileController(ArticleFileService articleFileService) {
		this.articleFileService = articleFileService;
	}
	
	// �Խñ� ���� ���ε�
	@RequestMapping(value="/upload", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request){
		ResponseEntity<String> entity = null;
		try {
			String savedFilePath = UploadFileUtils.uploadFile(file, request);  //������� �ٽ� õõ�� Ȯ��
			System.out.println(savedFilePath);
			entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// �Խñ� ÷������ ���
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ResponseEntity<byte[]>displayFile(String fileName, HttpServletRequest request) throws Exception{
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName); // Http ��� ���� ��������
		String rootPath = UploadFileUtils.getRootPath(fileName, request); // ���ε� �⺻��� ���
		System.out.println(fileName+rootPath);
		
		ResponseEntity<byte[]> entity = null;
		
		//���ϵ�����, HttpHeader ����
		try(InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//�Խ��� ���� ���� : �Խñ� �ۼ� ������
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		
		try {
			UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// �Խñ� ÷�� ���� ���
	@RequestMapping(value="/list/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getFiles(@PathVariable("boardNo") Integer boardNo){
		ResponseEntity<List<String>> entity = null;
		try {
			List<String> fileList = articleFileService.getAritcleFiles(boardNo);
			entity = new ResponseEntity<>(fileList,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// �Խñ� ���� ��ü ����
	@RequestMapping(value="/deleteAll", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request){
		
		if(files == null || files.length == 0)
			return new ResponseEntity<>("DELETED",HttpStatus.OK);
		
		ResponseEntity<String> entity = null;
		
		try {
			for(String fileName : files)
				UploadFileUtils.deleteFile(fileName, request);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// �Խñ� ÷������ ���� : �Խñ� ����
	@RequestMapping(value = "/delete/{boardNo}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@PathVariable("boardNo") Integer boardNo, String fileName, HttpServletRequest request){
		
		ResponseEntity<String> entity = null;
		
		try {
			UploadFileUtils.deleteFile(fileName, request);
			articleFileService.deleteFile(fileName,boardNo);
			entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
