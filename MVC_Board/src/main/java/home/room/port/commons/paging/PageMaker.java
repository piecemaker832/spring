package home.room.port.commons.paging;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageMaker {
	private int totalCount; // ��ü �Խñ� ����(DB���� ���Ǵ� ������)
	private int startPage; // ���� ������ ��ȣ
	private int endPage; // ������ ������ ��ȣ
	private boolean prev; // ���� ��ũ
	private boolean next; // ���� ��ũ

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

		// �������� ��ȣ ��� ((���������� / ��������ȣ�� ����) * ������ ��ȣ�� ����)
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		// ���������� ��ȣ ���
		startPage = (endPage - displayPageNum) + 1;
		// �������� ��������
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		// ���� ��ũ
		prev = startPage == 1 ? false : true;
		// ���� ��ũ
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

	}
	 
    // ����Ʈ ���������� ���� �Խñ� Ŭ���ϸ� ����¡,
    // �˻� ������ ������ URI���ڿ��� ���� ��ȸ�������� �̵��Ѵ�
    // �׷��� ��ȸ���������� ����¡, �˻������� �����ϰ� �ֱ� ������ �ٽ� ����Ʈ �������� �̵��� �� ���� page�� �˻� ������ ����Ʈ
	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("perPageNum", criteria.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria) criteria).getKeyword())).build();

		return uriComponents.toUriString();
	}
	
	//�˻�Ű���� ���ڵ�
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
	// ����Ʈ���������� ���� �Խñ� Ŭ���ϸ� �ش� page������ ������
    // page, perPageNum �Ķ���͸� ������ URI ���ڿ��� ���� ��ȸ�������� �̵��Ѵ� 
    // �׷��� ��ȸ���������� page, perPageNum, boardNo ���� �����ϰ� �ֱ� ������ �ٽ� ����Ʈ �������� �̵��Ҷ� ���� page�� �̵�
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
