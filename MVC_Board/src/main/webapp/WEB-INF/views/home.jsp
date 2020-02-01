<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%@ include file="include/head.jsp" %>
	<title>Home</title>
</head>
<body>
<%@ include file="include/main_header.jsp" %>
<div class="container">
		<h1>스프링 게시판 프로젝트</h1>
		<c:if test="${not empty login.userId}">
		<p>${login.userId} 안녕하세요</p>
		</c:if>
		<c:if test="${empty login.userId}">
		<p>안녕하세요</p>
		</c:if>
</div>

<%@ include file="include/main_footer.jsp" %>

</body>
</html>
