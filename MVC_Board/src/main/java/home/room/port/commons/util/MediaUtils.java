package home.room.port.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;


// 파일의 타입이 이미지인지를 판별하는 메서드를 가진 클래스
public class MediaUtils {
	
	private static Map<String,MediaType> mediaTypeMap;
	
	//클래스 초기화 블럭
	static {
		mediaTypeMap = new HashMap<>();
		mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
		mediaTypeMap.put("PNG",MediaType.IMAGE_PNG);
	}
	
	//파일 타입
	static MediaType getMediaType(String fileName) {
		String formatName = getFormatName(fileName);
		return mediaTypeMap.get(formatName);
	}
	
	//파일 확장자 추출
	static String getFormatName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
	}
	
}
