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
<h1>
	${login.userId} 안녕하세요~
</h1>

<%@ include file="include/main_footer.jsp" %>

</body>
</html>
