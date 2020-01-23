<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- REQUIRED SCRIPTS -->
<!-- Bootstrap 3.3.7 -->
 <script src="${path}/resources/bootstrap/js/bootstrap.min.js"></script>

<%--Handlebars--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>

<!-- jQuery Validation Plugin -->
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<%--lightbox js --%>
<script scr="${path}/bower_components/lightbox/js/lightbox.js"></script>
<!-- jQuery -->
	<script>
		var result="${msg}";
		if(result == "regSuccess"){
			alert("게시글 등록이 완료 되었습니다.");
		} else if(result == "modSuccess"){
			alert("게시글 수정이 완료 되었습니다.")
		} else if(result == "delSuccess"){
			alert("게시글 삭제가 완료 되었습니다.")
		}
	</script>

