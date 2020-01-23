<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>회원정보수정</title>
</head>
<body>
<form action ="${path}/user/update" method="post">
	<div>
	<input type="text" name="userId" class="form-control" value="${login.userId}" readonly>
	</div>	
	<div>
	<input type="password" name="userPass" class="form-control" placeholder="비밀번호">
	</div>	
	<div>
	<input type="text" name="userName" class="form-control" value="${login.userName}">
	</div>	
	<div>
	<input type="email" name="userEmail" class="form-control" placeholder="이메일">
	</div>
	<div>
	
	</div>
	<div>
	<input type="text" name="userIntro" class="form-control" placeholder="자기소개">
	</div>
	<button type="submit">가입</button>
</form>
</body>
</html>