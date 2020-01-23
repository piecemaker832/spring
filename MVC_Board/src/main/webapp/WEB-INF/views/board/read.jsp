<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<head>
<%@ include file="../include/head.jsp" %>
</head>
<body class="hold-transition sidebar-mini">
<%@ include file="../include/main_header.jsp" %>

	<div class="container">
		<h2 style="padding-top:10px; padding-botton:15px"><strong>Content</strong></h2>
		<div class="bg-light rounded shadow-sm">
			<div class="board_title">${board.boardTitle}</div>
			<div class="board_content">${board.boardContent}</div>
			<div class="board_info_box">
				<span class="board_author">${board.boardWriter},
				<fmt:formatDate
						pattern="yyyy-MM-dd a HH:mm" value="${board.boardRegdate}" />, Hit:${board.boardHit}</span>
			</div>
		</div>
		<div class="box-footer">
			<form role="form" method="post">
				<input type="hidden" name="boardNo" value="${board.boardNo}">
				<input type="hidden" name="page" value="${searchCriteria.page}">
				<input type="hidden" name="perPageNum"
					value="${searchCriteria.perPageNum}"> <input type="hidden"
					name="searchType" value="${searchCriteria.searchType}"> <input
					type="hidden" name="keyword" value="${searchCriteria.keyword}">
			</form>
			<div class="box-footer uploadFiles">
				<ul class="malibox-attachments clearfix uploadedFileList"></ul>
			</div>
			<div align="right">
				<button type="submit" class="btn btn-sm btn-primary listBtn">목록</button>
				<button type="submit" class="btn btn-sm btn-info reply">답글</button>
				<c:if test="${login.userId eq board.boardWriter}">
				<button type="submit" class="btn btn-sm btn-warning modBtn">수정</button>
				<button type="submit" class="btn btn-sm btn-danger delBtn">삭제</button>
				</c:if>
			</div>
		</div>
	</div>

	<%@ include file="../include/reply.jsp" %>
	
	<!-- 게시글 첨부파일 Handlebars 파일 템플릿 -->
	<script id="fileTemplate" type="text/x-handlebars-template">
		<li data-src="{{fullName}}">
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="Attachment">
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{originalFileUrl}}" class="mailbox-attachment-name">
					<i class="fa fa-paperclip"></i> {{originalFileName}}
				</a>
			</div>
		</li>
	</script>
	<script type="text/javascript" src="${path}/resources/article_file_upload.js"></script>
	
	<script>
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		var boardNo = "${board.boardNo}";
		
		getFiles(boardNo);
		
		
		$(".delBtn").on("click", function() {
			
			//첨부파일명들을 배열에 저장
			var arr = [];
			$(".uploadedFileList li").each(function() {
				arr.push($(this).attr("data-src"));
			})
			
			//첨부파일 삭제요청
			if(arr.length>0){
				$.post("${path}/article/deleteAll",{files:arr},function(){
					
				});
			}
			
			//삭제처리
			formObj.attr("action", "${path}/article/remove");
			formObj.submit();
		});
		
		$(document)
				.on(
						"click",
						function(event) {
							var formObj = $("form[role='form']"); // ??
							console.log(formObj);

							$(".listBtn").on("click", function() {
								formObj.attr("action", "${path}/board/list");
								formObj.attr("method", "get");
								formObj.submit();
							});
							$(".modBtn").on("click", function() {
								formObj.attr("action", "${path}/board/modify");
								formObj.attr("method", "get");
								formObj.submit();
							});
						
							$(".reply")
									.on(
											"click",
											function() {
												formObj
														.attr("action",
																"${path}/board/reply?boardNo=${board.boardNo}");
												formObj.attr("method", "get");
												formObj.submit();
											});

							$("#modBtn").on("click", function() {
								self.location = "${path}/board/modify"
							});
							$("#listBtn").on("click", function() {
								self.location = "${path}/board/list"
							});

						});
	</script>
</body>
</html>
