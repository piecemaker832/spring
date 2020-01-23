package home.room.port.board.domain;

import java.util.Date;

/**
 * @author USER
 *
 */
public class BoardVO {
	
	private Integer boardNo; //게시글 번호
	private String boardTitle; //게시글 제목
	private String boardContent; //게시글 내용
	private String boardWriter; //작성자
	private Date boardRegdate; //작성일자
	private Integer boardHit; //조회수
	private Integer boardGrpno; 
	private Integer boardGrplvl;
	private Integer boardLvl;
	private Integer boardLike; //좋아요
	private Integer boardDislike; //싫어요
	private Integer replyCnt; //댓글갯수
	private String boardCat; // 카테고리
	private String[] files;
	private int fileCnt;
	
	
	public Integer getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getBoardRegdate() {
		return boardRegdate;
	}
	public void setBoardRegdate(Date boardRegdate) {
		this.boardRegdate = boardRegdate;
	}
	public Integer getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(Integer boardHit) {
		this.boardHit = boardHit;
	}
	public Integer getBoardGrpno() {
		return boardGrpno;
	}
	public void setBoardGrpno(Integer boardGrpno) {
		this.boardGrpno = boardGrpno;
	}
	public Integer getBoardGrplvl() {
		return boardGrplvl;
	}
	public void setBoardGrplvl(Integer boardGrplvl) {
		this.boardGrplvl = boardGrplvl;
	}
	public Integer getBoardLvl() {
		return boardLvl;
	}
	public void setBoardLvl(Integer boardLvl) {
		this.boardLvl = boardLvl;
	}
	public Integer getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(Integer boardLike) {
		this.boardLike = boardLike;
	}
	public Integer getBoardDislike() {
		return boardDislike;
	}
	public void setBoardDislike(Integer boardDislike) {
		this.boardDislike = boardDislike;
	}
	public Integer getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}
	public String getBoardCat() {
		return boardCat;
	}
	public void setBoardCat(String boardCat) {
		this.boardCat = boardCat;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getFileCnt() {
		return fileCnt;
	}
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
}
