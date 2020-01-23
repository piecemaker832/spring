package home.room.port.commons.util;

import java.awt.PageAttributes.OriginType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

// ���� ���ε�/����/����,���ϻ���,���ϸ� �ߺ����� ���� ����� ó���� �޼������ ���� Ŭ����

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	// ���� ���ε� ó��
	public static String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception{
		
		String originalFileName = file.getOriginalFilename(); //���� ����
		byte[] fileData = file.getBytes(); //���� ������
		
		// 1. ���ϸ� �ߺ� ó�� ����
		String uuidFileName = getUuidFileName(originalFileName);
		
		// 2. ���� ���ε� ��� ����
		String rootPath = getRootPath(originalFileName, request); //�⺻��� ����(�̹��� or �Ϲ�����)
		String datePath = getDatePath(rootPath); // ��¥ ��� ����, ��¥ ���� ����
		
		// 3. ������ ���� ����
		File target = new File(rootPath + datePath, uuidFileName); //���� ��ü ����
		FileCopyUtils.copy(fileData, target); //���� ��ü�� ���� ������ ����
		
		// 4. �̹��� ������ ��� ������̹��� ����
		if(MediaUtils.getMediaType(originalFileName)!=null) {
			uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
		}

		// 5. ���� ���� ��� ġȯ
		return replaceSavedFilePath(datePath,uuidFileName);
	}
	
	//���� �ߺ�ó�� ó��
	private static String getUuidFileName(String originalFileName) {
		return UUID.randomUUID().toString() + "_" + originalFileName;
	}
	
	// �⺻ ��� ����
	public static String getRootPath(String fileName, HttpServletRequest request) {
		String rootPath = "/resources/upload";
		MediaType mediaType = MediaUtils.getMediaType(fileName); // ����Ÿ�� Ȯ��
		if(mediaType != null) 
			return request.getSession().getServletContext().getRealPath(rootPath + "/images"); //�̹��� ���� ���
			
		return request.getSession().getServletContext().getRealPath(rootPath + "/files"); //�Ϲ����� ���
		
	}
	
	// ��¥ ������ ����
	public static String getDatePath(String uploadPath) {
		Calendar calendar = Calendar.getInstance();
		String yearPath = File.separator + calendar.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		
		makeDateDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	//��¥�� ���� ����
		public static void makeDateDir(String uploadPath, String...paths) {
			
			//��¥�� ������ �̹� �����ϸ� �޼ҵ� ����
			if(new File(uploadPath + paths[paths.length - 1]).exists())
				return;
			
			for(String path: paths) {
				File dirPath = new File(uploadPath + path);
				if(!dirPath.exists())
					dirPath.mkdir();
			}
		}
	
	//���� ���� ó��
	public static void deleteFile(String fileName, HttpServletRequest request) {
		
		String rootPath = getRootPath(fileName, request); // �⺻��� ���� (�̹��� or �Ϲ�����)
		
		// 1. ���� �̹��� ���� ���� 
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		if(mediaType != null) {
			String originalImg = fileName.substring(0,12) + fileName.substring(14);
			new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
		}
		
		// 2. ���� ����(����� �̹��� or �Ϲ�����)
		new File(rootPath + fileName.replace('/', File.separatorChar)).delete();
	}
	
	// ���� ����� ���� HtppHeader ����
	public static HttpHeaders getHttpHeaders(String fileName) throws Exception {
		MediaType mediaType = MediaUtils.getMediaType(fileName); // ����Ÿ�� Ȯ��
		HttpHeaders httpHeaders = new HttpHeaders(); //???
		//�̹��� ���� O
		if(mediaType != null) {
			httpHeaders.setContentType(mediaType);
			return httpHeaders;
		}
		
		//�̹�������X
		fileName = fileName.substring(fileName.indexOf("_")+1); //UUID ����
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM); // �ٿ�ε� MIME Ÿ�� ����
		// ���ϸ� �ѱ� ���ڵ� ó��
		httpHeaders.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
		
		return httpHeaders;
	}
	
	//���� ���� ��� ġȯ
	private static String replaceSavedFilePath(String datePath, String fileName) {
		String savedFilePath = datePath + File.separator + fileName;
//		return savedFilePath.replace(File.separatorChar, '/').replace(" ", ""); //��������..?
		return savedFilePath.replace(File.separatorChar, '/');
	}
	

	
	// ����� �̹��� ����
	private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws Exception {
		//�����̹����� �޸𸮻� �ε�
		BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
		//�����̹����� ���
		BufferedImage thumbnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		//����� ���ϸ�
		String thumbnailImgName = "s_" + fileName;
		//����� ���ε� ���
		String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
		//����� ���� ��ü����
		File newFile = new File(fullPath);
		//����� ���� Ȯ���� ����
		String formatName = MediaUtils.getFormatName(fileName);
		//����� ���� ����
		ImageIO.write(thumbnailImg, formatName, newFile);
		
		return thumbnailImgName;
	}
}




