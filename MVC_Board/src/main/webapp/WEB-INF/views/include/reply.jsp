<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form id="commentForm" name="commentForm" method="post">
			<br> <br>
			<div>
				<div>
					<span><strong>Comments</strong></span> <span id="replyCount"></span>
				</div>
				<div>
					<c:if test="${not empty login.userId}">
					<table class="table">
						<tr>
							<td><textarea style="width: 1100px" rows="3" cols="30"
									id="comment" name="comment" placeholder="댓글을 입력하세요"></textarea>
								<br>
								<div>
									<button type="button" id="addBtn" class="btn pull-right btn-success replyAddBtn">등록</button>
								</div></td>
						</tr>
					</table>
					</c:if>
					<c:if test="${empty login.userId}">
						<a href="${path}/user/login" style="margin-top: 10px; margin-bottom:20px" class="btn btn-secondary" role="button">
							로그인 한 사용자만 댓글 등록이 가능합니다.
						</a>
					</c:if>
				</div>
			</div>
		</form>
	
	<div class="box box-success replybox">
		<div class="box-body repliesDiv"></div>
		<div class="box-footer box-tool">
			<div class="text-center">
				<ul class="pagination justify-content-center">

				</ul>
			</div>
		</div>
	</div>

	<%--댓글 수정을 위한 modal 영역--%>
	<div class="modal fade" id="modModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">댓글수정</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" data-rno>
					<input type="hidden" class="replyNo" />
					<%--<input type="text" id="replytext" class="form-control"/>--%>
					<textarea class="form-control" id="replyText" rows="3"
						style="resize: none"></textarea>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-primary modalModBtn">수정</button>
				</div>
			</div>
		</div>
	</div>

	<%--댓글 삭제를 위한 modal 영역--%>
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">댓글 삭제</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<input type="hidden" class="replyNo" />
				</div>
				<div class="modal-body" data-rno>
					<p>댓글을 삭제하시겠습니까?</p>
					<input type="hidden" class="replyNo" />
					<!-- hidden으로 넣는 이유?? -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">아니요.</button>
					<button type="button" class="btn btn-primary modalDelBtn">네.
						삭제합니다.</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="../include/plugin_js.jsp"%>
	

	<script id="replyTemplate" type="text/x-handlebars-template">

    {{#each.}}
    <div class="post replyDiv" style="border-bottom : 10px solid white; background-color: #f3f2e18f;" data-replyNo={{replyNo}}>
        <div class="user-block">
			<span>
                <%--작성자 이름--%>
                <a href="#" style="font-weight:bold;"><font size="4" color="#0B610B">{{replyWriter}}</a></font>
			</span>
			<c:if test="${login.userId eq board.boardWriter}">
			<span style="display: inline-block; width: 95%; text-align:right;">
                <%--댓글 삭제 버튼--%>
                <a href="#" class="replyDelBtn" data-toggle="modal" data-target="#delModal"><font color="#da3e3e">
                    <i class="fa fa-times"> 삭제</i>
                </a></font>
                <%--댓글 수정 버튼--%>
                <a href="#" class="replyModBtn" data-toggle="modal" data-target="#modModal"><font color="#5d93d6">
                    <i class="fa fa-edit"> 수정</i>
                </a></font>
			</span>
			</c:if>
			<br/>
            <%--댓글 작성일자--%>
            <font size="2">{{prettifyDate regDate}}</font>
        </div>
        <%--댓글 내용--%>
        <div class="oldReplyText">{{{escape replyText}}}</div>
        <br/>
    </div> 
    {{/each}} <%-- c:forEach 와 같은 배열의 루프처리 --%>
	</script>
	
	<script>
	////Handlebar.js(이하 핸들바)는 자바스크립트의 템플릿 엔진 중 하나이다. 먼저 템플릿 엔진이라 하면 프로그램 로직과 프레젠테이션 계층을 분리하기위한 수단
    $(document).ready(function () {
        /*======================================== 댓글 CRUD 관련 ========================================*/
        // ---------------------------------------- 댓글 목록, 페이징 ----------------------------------------
        // 전역변수 선언
        // 게시글 번호
        var boardNo = ${board.boardNo};
        console.log(boardNo)
        // 댓글 페이지 초기화
        var replyPage = 1;
        
        // 댓글 내용 줄바꿈, 공백 처리를 위한 문자열 처리
        Handlebars.registerHelper("escape", function(replytext) {
            var text = Handlebars.Utils.escapeExpression(replytext);
            text = text.replace(/(\r\n|\n|\r)/gm, "<br/>");
            text = text.replace(/( )/gm, "&nbsp;");
            return new Handlebars.SafeString(text);
        });
        // 댓글 등록일자 출력을 위한 날짜/시간 문자열 처리
        Handlebars.registerHelper("prettifyDate", function (timeVale) {
            var dateObj = new Date(timeVale);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth() + 1;
            var date = dateObj.getDate();
            var hours = dateObj.getHours();
            var minutes = dateObj.getMinutes();
            // 2자리 숫자 맞추기
            month < 10 ? month = '0' + month : month;
            date < 10 ? date = '0' + date : date;
            hours < 10 ? hours = '0' + hours : hours;
            minutes < 10 ? minutes = '0' + minutes : minutes;
            return year + "-" + month + "-" + date + " " + hours + ":" + minutes;
        });
        
        getReplies("${path}/reply/" + boardNo + "/" + replyPage);
        
        // 댓글 갯수, 목록, 하단페이징 출력 호출 함수
        function getReplies(repliesUri) {
            // 댓글 목록 가져오기
            $.getJSON(repliesUri, function (data) {
                // 1. 댓글 갯수 출력 함수 호출
                printReplyCount(data.pageMaker.totalCount);
                // 2. 댓글 목록 출력 함수 호출
                printReplies(data.replies, $(".repliesDiv"), $("#replyTemplate"));
                // 3. 댓글 하단 페이징 출력 함수 호출
                printReplyPaging(data.pageMaker, $(".pagination"));
            });
        };
        
        // 1. 댓글 갯수 출력, 댓글 보기 버튼 활성/비활성 함수
        function printReplyCount(totalCount) {
        	
        	var replyCount = $("#replyCount");
        	var replybox = $(".replybox");
        	
        	if (totalCount == 0) {
                replyCount.html(" 댓글이 없습니다. 의견을 남겨주세요.");
                replybox.remove();
                return;
            }
        	
        	replyCount.html(" 댓글 ("+totalCount+")");
        }
        
        // 2. 댓글 목록 출력 함수
        function printReplies(replyArr, targetArea, templateObject) { //data.replies , $(".repliesDiv"), $("#replyTemplate")
            var replyTemplate = Handlebars.compile(templateObject.html());
            var html = replyTemplate(replyArr);
            $(".replyDiv").remove();
            targetArea.html(html);
        };
        
        
        // 3. 하단 페이징 출력 함수
         function printReplyPaging(pageMaker, targetArea) {
            var str = "";
            if (pageMaker.prev) {
                str += "<li class=\"page-item\"><a class=\"page-link\" href='" + (pageMaker.startPage - 1) + "'>이전</a></li>";
            }
            for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
                var strClass = pageMaker.criteria.page == i ? "active" : "";
                str += "<li class=\"page-item\" " + strClass + "><a class=\"page-link\" href='" + i + "'>" + i + "</a></li>";
            }
            if (pageMaker.next) {
                str += "<li class=\"page-item\"><a href='" + (pageMaker.endPage + 1) + "'></a></li>"
            }
            targetArea.html(str);
        }; 
        
        // 댓글 페이지 번호 클릭 이벤트
        $(".pagination").on("click", "li a", function (event) {
            event.preventDefault();
            replyPage = $(this).attr("href");
            getReplies("${path}/reply/"+boardNo+"/"+replyPage);
        });
        
        // ---------------------------------------- 댓글 입력 ----------------------------------------
        // 댓글 저장 버튼 클릭 이벤트
        $(".replyAddBtn").on("click", function () {
            // 입력 form 선택자
            var replyTextObj = $("#comment");
            var replyWriter = "${login.userId}";
            var replyText = replyTextObj.val();
            
			var comment = $("#comment").val();
			
			if(comment == ""){
				alert("댓글 내용을 입력하세요");
				document.commentForm.comment.focus();
				return false;
			}
            
            // 댓글 입력처리 수행
            $.ajax({
                type: "post",
                url: "${path}/reply/",
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    boardNo:boardNo,
                    replyText:replyText,
                    replyWriter:replyWriter
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "regSuccess") {
                        alert("댓글이 등록되었습니다.");
                        replyPage = 1;  // 페이지 1로 초기화
                        getReplies("${path}/reply/" + boardNo + "/" + replyPage); // 댓글 목록 호출
                        replyTextObj.val("");   // 댓글 입력창 공백처리
                    }
                }
            });
        });
        // ---------------------------------------- 댓글 수정 ----------------------------------------
        // 댓글 수정을 위해 modal창에 선택한 댓글의 값들을 세팅
        $(".repliesDiv").on("click", ".replyDiv", function (event) {
            var reply = $(this);
            console.log(reply);
            $(".replyNo").val(reply.attr("data-replyNo"));
            $("#replytext").val(reply.find(".oldReplytext").text());
        });
        // modal 창의 댓글 수정버튼 클릭 이벤트
        $(".modalModBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            var replyText = $("#replyText").val();
            $.ajax({
                type: "put",
                url: "${path}/reply/" + replyNo,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "PUT"
                },
                dataType: "text",
                data: JSON.stringify({
                    replyText:replyText
                }),
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "modSuccess") {
                        alert("댓글이 수정되었습니다.");
                        getReplies("${path}/reply/" + boardNo + "/" + replyPage); // 댓글 목록 호출
                        $("#modModal").modal('hide'); // modal 창 닫기
                    }
                }
            })
        });
        // ---------------------------------------- 댓글 삭제 ----------------------------------------
        $(".modalDelBtn").on("click", function () {
            var replyNo = $(".replyNo").val();
            $.ajax({
                type: "delete",
                url: "${path}/reply/" + replyNo,
                headers: {
                    "Content-Type" : "application/json",
                    "X-HTTP-Method-Override" : "DELETE"
                },
                dataType: "text",
                success: function (result) {
                    console.log("result : " + result);
                    if (result == "delSuccess") {
                        alert("댓글이 삭제되었습니다.");
                        getReplies("${path}/reply/" + boardNo + "/" + replyPage); // 댓글 목록 호출
                        $("#delModal").modal("hide"); // modal 창 닫기
                    }
                }
            });
        });
    });
	</script>

</body>
</html>
