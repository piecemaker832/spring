<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp" %>
<title>회원정보수정</title>
</head>
<body>
<%@ include file="../include/main_header.jsp" %>
<form action ="${path}/user/update" id="userUpdateForm" method="post">
		<div class="container bg-light rounded shadow-sm"
			style="padding-top: 80px; padding-bottom: 200px">
			<h2 class="text-center">
				<strong>회원정보수정</strong>
			</h2>
			<div class="form-group">
				<span style="color:red;"><strong>*</strong></span>&nbsp;
				 <strong>아이디</strong>
				<input type="text" id="userId" class="form-control"
					placeholder="아이디" maxlength="20" value="${login.userId}" readonly>
			</div>
			<div class="form-group">
				<span style="color:red;"><strong>*</strong></span>&nbsp;
				 <strong>비밀번호</strong>
				<input type="password" id="userPass" class="form-control"
					placeholder="비밀번호" maxlength="20">
			</div>
			<div class="form-group">
				<span style="color:red;"><strong>*</strong></span>&nbsp;
				 <strong>비밀번호 확인</strong>
				<input type="password" id="userPass2" class="form-control"
					placeholder="비밀번호 재확인" maxlength="20">
			</div>
			<div class="form-group">
				<span style="color:red;"><strong>*</strong></span>&nbsp;
				 <strong>닉네임</strong>
				<input type="text" id="userName" class="form-control"
					placeholder="닉네임" maxlength="20" value="${login.userName}">
			</div>
			<div class="form-group">
				<span style="color:red;"><strong>*</strong></span>&nbsp;
				 <strong>이메일</strong>
				<input type="email" id="userEmail" class="form-control"
					placeholder="이메일" maxlength="20">
			</div>
			<div>
			<!-- ?? -->
			</div>
			<div align="center">
			<button class="btn btn-primary" id="join" type="submit">가입</button>
			</div>
		</div>
	</form>
	
	
	
	<script>
	
		function ValidationCheck() {
			var validate = true;
			var hanChecked = /^[가-힣a-zA-Z0-9]{2,10}$/; //한글과 영어, 숫자만 사용하였는지 검사
			var idChecked = /^[0-9a-zA-Z]{5,15}$/; // 아이디 유효성검사(5자리 이상 15자리 이하)

			if(!$("#registerForm #userId").val()) {
				alert("아이디를 입력 하세요.");
				$("#registerForm #userId").focus();
				validate = false;
				return false;
			} else if (!idChecked.test($("#registerForm #userId").val())){
				alert("아이디는 5자~15자리 사이로 만들어 주세요.");
				return false;
			}else if(!$("#registerForm #userPass").val()) {
				alert("비밀번호를 입력하세요.");
				$("#registerForm #userPass").focus();
				return false;
			}else if($("#registerForm #userPass").val().length > 16 || $("#registerForm #userPass").val().length < 8) {
				alert("비밀번호는 8 ~ 16 자리로 입력해주세요.");
				return false;
			}else if(!$("#registerForm #userPass2").val()) {
				alert("비밀번호 확인을 입력하세요.");
				$("#registerForm #userPass2").focus();
				return false;
			}else if($("#registerForm #userPass").val() != $("#registerForm #userPass2").val()) {
				alert("비밀번호가 일치 하지 않습니다.");
				$("#registerForm #userPass2").focus();
				return false;
			} else if (!$("#registerForm #userName").val()) {
				alert("닉네임을 입력 하세요.");
				$("#registerForm #userName").focus();
				return false;
			} else if (!hanChecked.test($("#registerForm #userName").val())){
				alert("닉네임이 잘못 되었습니다.");
				return false;
			}else {
				return true;
			}

		}
		
		$("#join").click(function(){
			var validate = ValidationCheck();
			if(validate == false) return false;
		})
	</script>
</body>
</html>