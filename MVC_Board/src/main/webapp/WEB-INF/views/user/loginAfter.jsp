<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
</head>
<body>
	<script>
		alert("이미 로그인중입니다.");
		self.location = "${path}";
	</script>
</body>
</html>