package home.room.port.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;


// ������ Ÿ���� �̹��������� �Ǻ��ϴ� �޼��带 ���� Ŭ����
public class MediaUtils {
	
	private static Map<String,MediaType> mediaTypeMap;
	
	//Ŭ���� �ʱ�ȭ ��
	static {
		mediaTypeMap = new HashMap<>();
		mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
		mediaTypeMap.put("PNG",MediaType.IMAGE_PNG);
	}
	
	//���� Ÿ��
	static MediaType getMediaType(String fileName) {
		String formatName = getFormatName(fileName);
		return mediaTypeMap.get(formatName);
	}
	
	//���� Ȯ���� ����
	static String getFormatName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
	}
	
}
