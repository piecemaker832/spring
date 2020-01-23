<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%@ include file="../include/head.jsp" %>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
.pagination {
   justify-content: center;
}
</style>
</head>
<body>
	<%@ include file="../include/main_header.jsp"%>
	<div class="container">
	<h1 class="text-center" style="padding-top:15px; padding-bottom:15px"><strong>게시판</strong></h1>
		<table class="table">
			<tbody>
				<tr>
					<th style="width: 5%">#</th>
					<th style="width: auto">제목</th>
					<th style="width: 10%">작성자</th>
					<th style="width: 25%">작성시간</th>
					<th style="width: 10%">조회수</th>
				</tr>
				<c:forEach items="${board}" var="board">
					<tr>
						<td>${board.boardNo}</td>
						<td><c:if test="${board.boardLvl > 0}">
								<!-- 조건문 설정 다시 -->
								<c:forEach begin="1" end="${board.boardLvl}">
                            &nbsp; <!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
								</c:forEach>
                        RE :: 
					</c:if> <a href="${path}/board/read?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
						<td>${board.boardWriter}</td>
						<td><fmt:formatDate value="${board.boardRegdate}"
								pattern="yyyy-MM-dd a HH:mm" /></td>
						<td>${board.boardHit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 페이징 번호 -->
	<div class="container">
		<ul class="pagination">
			<c:if test="${pageMaker.prev}">
				<li class="page-item"><a class="page-link"
					href="${path}/board/list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
				var="idx">
				<c:choose>
					<c:when test="${pageMaker.criteria.page == idx}">
						<li class="page-link"><span style="color: red">${idx}</span></li>
					</c:when>
					<c:otherwise>
						<li class="page-link"><a
							href="${path}/board/list${pageMaker.makeSearch(idx)}">${idx}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li class="page-item"><a class="page-link"
					href="${path}/board/list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- 검색 -->
	<div class="container">
		<div class="row" align="center">
			<div class="form-group col-sm-2">
				<select class="form-control" name="searchType" id="searchType">
					<option value="n"
						<c:out value="${searchCriteria.searchType == null ? 'selected' : '' }"/>>:::::
						선택 :::::</option>
					<option value="t"
						<c:out value="${searchCriteria.searchType == t ? 'selected' : '' }"/>>제목</option>
					<option value="c"
						<c:out value="${searchCriteria.searchType == c ? 'selected' : '' }"/>>내용</option>
					<option value="w"
						<c:out value="${searchCriteria.searchType == w ? 'selected' : '' }"/>>작성자</option>
					<option value="tc"
						<c:out value="${searchCriteria.searchType == tc ? 'selected' : '' }"/>>제목+내용</option>
					<option value="cw"
						<c:out value="${searchCriteria.searchType == cw ? 'selected' : '' }"/>>내용+작성자</option>
					<option value="tcw"
						<c:out value="${searchCriteria.searchType == tcw ? 'selected' : '' }"/>>제목+내용+작성자</option>
				</select>
			</div>
			<div class="form-group col-sm-4">
				<input type="text" class="form-control" name="keyword"
					id="keywordInput" value="${searchCriteria.keyword}"
					placeholder="검색어">
			</div>
			<span>
				<button type="button" class="btn btn-primary" id="searchBtn">
					<i class="fas fa-search"></i>검색
				</button>
			</span>
		</div>
		<div align="right">
		<button type="button" class="btn btn-success" id="writeBtn">
			<i class="fas fa-pen"></i>글쓰기
		</button>
		<button type="button" class="btn btn-info" id="home">
			<i class="fas fa-home"></i> 홈으로
		</button>
		</div>
	</div>


	<%@ include file="../include/main_footer.jsp"%>
	<script>
	$(document).on("click",function(event){
		$("#searchBtn").on("click",function(event) {
		self.location = 
			"${path}/board/list${pageMaker.makeQuery(1)}"
			+ "&searchType=" + $("select option:selected").val()
			+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
		});
	});
	
		$("#writeBtn").on("click", function() {
			self.location = "${path}/board/write"
		});
		$("#home").on("click", function() {
			self.location = "${path}"
		});
	</script>
</body>
</html>