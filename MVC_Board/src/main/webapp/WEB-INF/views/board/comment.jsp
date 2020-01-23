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
</head>
<body class="hold-transition sidebar-mini">

	<div class="wrapper">

		<div class="content-wrapper">
			<section class="content container-fluid">
				<div class="col-lg-12">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">글제목 : ${board.boardTitle}</h3>
						</div>
						<div class="box-body" style="height: 300px">
							내용<br>${board.boardContent}</div>
						<div class="box-footer">
							<div class="user-block">
								<span class="username"> <a href="#">${board.boardWriter}</a>
								</span> <span class="description"><fmt:formatDate
										pattern="yyyy-MM-dd a HH:mm" value="${board.boardRegdate}" /></span>
							</div>
						</div>
						<div class="box-footer">
							<form role="form" method="post">
								<input type="hidden" name="boardNo" value="${board.boardNo}">
								<input type="hidden" name="page" value="${searchCriteria.page}">
								<input type="hidden" name="perPageNum"
									value="${searchCriteria.perPageNum}"> <input
									type="hidden" name="searchType"
									value="${searchCriteria.searchType}"> <input
									type="hidden" name="keyword" value="${searchCriteria.keyword}">
							</form>
							<button type="submit" class="listBtn">
								<i class="fa fa-list"></i>목록
							</button>
							<div>
								<button type="submit" class="reply">답글</button>
								<button type="submit" class="modBtn">수정</button>
								<button type="submit" class="delBtn">삭제</button>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!-- /.content-wrapper -->

	</div>


	<script>
		$(document).on("click", function(event) {
			var formObj = $("form[role='form']"); // ??
			console.log(formObj);

			$(".modBtn").on("click", function() {
				formObj.attr("action", "${path}/board/modify");
				formObj.attr("method", "get");
				formObj.submit();
			});
			$(".delBtn").on("click", function() {
				formObj.attr("action", "${path}/board/remove");
				formObj.attr("method", "post");
				formObj.submit();
			});
			$(".reply").on("click", function() {
				formObj.attr("action", "${path}/board/reply?boardNo=${board.boardNo}");
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
