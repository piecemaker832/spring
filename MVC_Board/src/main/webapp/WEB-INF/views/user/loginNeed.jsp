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
		alert("로그인 이후 접근 가능합니다.");
		self.location = "${path}/user/login";
	</script>
</body>
</html>