<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<%@ include file="../include/main_header.jsp" %>
	<div class="container">
		<form role="form" id="modifyForm" method="post"
			action="${path}/board/modify">
			<div class="box box-primary">
				<div>
					<h2>게시글 수정</h2>
				</div>
				<div>
					<input type="hidden" name="boardNo" value="${board.boardNo}">
					<input type="hidden" name="page" value="${searchCriteria.page}">
					<input type="hidden" name="perPageNum"
						value="${searchCriteria.perPageNum}"> <input type="hidden"
						name="searchType" value="${searchCriteria.searchType}"> <input
						type="hidden" name="keyword" value="${searchCriteria.keyword}">
					<div class="form-group mb-3">
						<label for="boardTitle">제목</label> <input class="form-control"
							id="boardTitle" name="boardTitle" placeholder="제목을 입력해주세요">
					</div>
					<div class="form-group mb-3">
						<label for="boardContent">내용</label>
						<textarea class="form-control" id="boardContent"
							name="boardContent" rows="20" placeholder="내용을 입력해주세요"
							style="resize: none;">${board.boardContent}</textarea>
					</div>
					<div class="form-group mb-3">
						<label for="boardWriter">작성자</label> <input class="form-control"
							id="boardWriter" name="boardWriter" value="${board.boardWriter}"
							readonly>
					</div>
				</div>
				<div align="right">
					<button type="button" class="btn btn-danger cancelBtn">취소</button>
					<button type="submit" class="btn btn-success modBtn">수정</button>
				</div>
			</div>
		</form>
	</div>


	<script>
		$(document)
				.ready(
						function() {
							var formObj = $("form[role='form']");
							console.log(formObj);

							$(".modBtn").on("click", function() {
								formObj.submit();
							});
							$(".cancelBtn").on("click", function() {
								history.go(-1);
							});
						});
	</script>
</body>
</html>
