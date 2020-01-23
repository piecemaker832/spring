<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/main_header.jsp" %>
	<table border="1">
		<tbody>
			<tr>
				<th style="width: 100px">아이디</th>
				<th style="width: 100px">닉네임</th>
				<th style="width: 100px">이메일</th>
				<th style="width: 210px">가입일시</th>
				<th style="width: 210px">최근로그인 일시</th>
			</tr>
			<c:forEach items="${user}" var="user">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td>${user.userEmail}</td>
					<td><fmt:formatDate value="${user.userJoinTime}"
							pattern="yyyy-MM-dd a HH:mm" /></td>
					<td><fmt:formatDate value="${user.userLoginTime}"
							pattern="yyyy-MM-dd a HH:mm" /></td>
				</tr>
			</c:forEach>
		</tbody>
		<button type="button" id="home">home</button>
	</table>
	<script>
	$(document).on("click",function(event){
		$("#searchBtn").on("click",function(event) {
		self.location = 
			"${path}/board/list${pageMaker.makeQuery(1)}"
			+ "&searchType=" + $("select option:selected").val()
			+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
		});
	});
	
		$("#home").on("click", function() {
			self.location = "${path}"
		});
	</script>
</body>
</html>