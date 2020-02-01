<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- Main Header -->
  <header class="main-header">
  	<div class="jumbotron text-center" style="background-color:#c0bdd0; margin:0px;">
		<h1><strong>스프링 게시판 프로젝트</strong></h1>
	</div>
 	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	  <a class="navbar-brand" href="${path}">Spring</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarsExample03">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="${path}/board/list">게시판 <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">채팅</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">회원메뉴</a>
	        <c:choose>
	        <c:when test="${empty login.userId }">
		        <div class="dropdown-menu" aria-labelledby="dropdown03">
		          <a class="dropdown-item" href="${path}/user/login">로그인</a>
		          <a class="dropdown-item" href="${path}/user/register">회원가입</a>
		        </div>
	        </c:when>
	        <c:otherwise>
	       	 <div class="dropdown-menu" aria-labelledby="dropdown03">
	        	  <a class="dropdown-item" href="${path}/user/logout">로그아웃</a>
	        	  <a class="dropdown-item" href="${path}/user/userUpdate">회원정보수정</a>
	        	  <a class="dropdown-item" href="${path}/user/userList">회원정보확인</a>
	          </div>
	        </c:otherwise>
	        </c:choose>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-md-0">
	      <input class="form-control" type="text" placeholder="Search">
	    </form>
	  </div>
	</nav> 
  </header>
</body>

</html>