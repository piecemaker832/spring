<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>MVC</title>
</head>
<body>
	<script>
		alert("아이디와 비밀번호를 확인하여 주세요.");
		self.location = "${path}/user/login";
	</script>
</body>
</html>