package home.room.port.commons.paging;

//페이징 처리를 위한 클래스
public class Criteria {

	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1; // 현재 페이지
		this.perPageNum = 10; //페이지당 보여지는 게시글 수 
	}
	
	public int getPageStart() {
		return (this.page - 1 ) * perPageNum; //현재 페이지의 시작 게시글 번호
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		
		if(page<=0) {
			this.page=1;
			return;
		}
		
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
}
