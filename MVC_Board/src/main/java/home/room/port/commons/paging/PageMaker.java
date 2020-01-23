package home.room.port.commons.paging;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageMaker {
	private int totalCount; // 전체 게시글 갯수(DB에서 계산되는 데이터)
	private int startPage; // 시작 페이지 번호
	private int endPage; // 마지막 페이지 번호
	private boolean prev; // 이전 링크
	private boolean next; // 다음 링크

	private int displayPageNum = 10;

	private Criteria criteria;

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	private void calcData() {

		// 끝페이지 번호 계산 ((현재페이지 / 페이지번호의 갯수) * 페이지 번호의 갯수)
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		// 시작페이지 번호 계산
		startPage = (endPage - displayPageNum) + 1;
		// 실질적인 끝페이지
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		// 이전 링크
		prev = startPage == 1 ? false : true;
		// 다음 링크
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

	}
	 
    // 리스트 페이지에서 단일 게시글 클릭하면 페이징,
    // 검색 정보를 가지고 URI문자열을 만들어서 조회페이지로 이동한다
    // 그러면 조회페이지에서 페이징, 검색정보를 유지하고 있기 때문에 다시 리스트 페이지로 이동할 때 원래 page와 검색 조건의 리스트
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", criteria.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword())).build();

		return uriComponents.toUriString();
	}
	
	//검색키워드 인코딩
	public String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			return "";
		}

	}
	// 리스트페이지에서 단일 게시글 클릭하면 해당 page정보를 가지고
    // page, perPageNum 파라미터를 포함한 URI 문자열을 만들어서 조회페이지로 이동한다 
    // 그러면 조회페이지에서 page, perPageNum, boardNo 값을 유지하고 있기 때문에 다시 리스트 페이지로 이동할때 원래 page로 이동
    public String makeQuery(int page) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toUriString();
    }

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCriteria() {
		return criteria;
	}

}
