<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/head.jsp" %>
<meta charset="EUC-KR">
<title>스프링 프로젝트</title>
</head>
<body>
<script>
	$(document).ready(function(){
		$("#btnSave").click(function(){
			var boardTitle = $("#boardTitle").val();
			var boardContent = $("#boardContent").val();
			
			if(boardTitle == ""){
				alert("제목을 입력하세요");
				document.writeForm.boardTitle.focus();
				return false;
			}
			
			if(boardContent == ""){
				alert("내용을 입력하세요");
				document.writeForm.boardContent.focus();
				return false;
			}
			
			document.writeForm.submit();
		});
	});
</script>
	<%@ include file="../include/main_header.jsp"%>
	<form name="writeForm" role="form" id="writeForm" method="post" enctype="multipart/form-data"
		action="${path}/board/write">
		<div class="container">
			<div>
				<h2>게시글 작성</h2>
			</div>
			<div>
				<input type="hidden" name="boardNo" value="${board.boardNo}">
				<input type="hidden" name="boardGrpno" value="${board.boardGrpno}">
				<input type="hidden" name="boardGrplvl" value="${board.boardGrplvl}">
				<input type="hidden" name="boardLvl" value="${board.boardLvl}">
				<div class="mb-3">
					<label for="boardTitle">제목</label> <input class="form-control"
						id="boardTitle" name="boardTitle" placeholder="제목을 입력해주세요">
				</div>
				<div class="mb-3">
					<label for="boardContent">내용</label>
					<textarea class="form-control" id="boardContent"
						name="boardContent" rows="20" placeholder="내용을 입력해주세요"
						style="resize: none;"></textarea>
				</div>
				<div class="form-group uploadDiv">
				<input type="file" name="uploadFile" multiple><br>
				</div>
				<div>
					<ul class="mailbox-attachments clearfix uploadedFileList"></ul>
				</div>
				<div class="mb-3">
					<label for="boardWriter">작성자</label> <input class="form-control"
						id="boardWriter" name="boardWriter" value="${login.userId}"
						readonly>
				</div>
			</div>
			<div align="right">
				<button class="btn btn-primary listBtn" type="button">목록</button>
				<button class="btn btn-secondary" type="reset">초기화</button>
				<button class="btn btn-success" type="submit" id="btnSave">저장</button>
			</div>

		</div>
	</form>
	
	<%@ include file="../include/plugin_js.jsp"%>
	
	<script id="fileTemplate" type="text/x-handlebars-template">
		<li>
			<span class="mailbox-attachment-icon has-img">
				<img src = "{{imgSrc}}" alt="Attachment">
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{originalFileUrl}}" class="mailbox-attachment-name">
					<i class="fa fa-paperclip"></i>{{originalFileName}}
				</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
					<i class="fa fa-fw fa-remove"></i>
				</a>
			</div>
		</li>
	</script>
	
	<script>
	var fileTemplate = Handlebars.compile($("#fileTemplate").html());
	
	
		$(document).ready(function(e) {
				var formObj = $("form[role='form']");
				console.log(formObj);
				
				
				$(".listBtn").on("click",function() {
							self.location = "${path}/board/list?page=${searchCriteria.page}"
							+ "&perPageNum=${searchCriteria.perPageNum}"
							+ "&searchType=${searchCriteria.searchType}"
							+ "&keyword=${searchCriteria.keyword}"
						});
				
				var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
				var maxSize = 10485760
				
				function checkExtension(fileName, fileSize){
					
					if(fileSize > maxSize){
						alert("파일 사이즈 초과");
						return false;
					}
					
					if(regex.test(fileName)){
						alert("해당 종류의 파일은 업로드 할 수 없습니다.");
						return false;
					}
					
					return true;
				}
				
 				$("input[type='file']").change(function(e){
 				e.preventDefault();
				var formData = new FormData();
				var inputFile = $("input[name=uploadFile]");
				var files = inputFile[0].files;
				
				for(var i = 0; i < files.length; i++){
					if(!checkExtension(files[i].name,files[i].size) ){
						return false;
					}
					formData.append("file",files[i]);
				}
					uploadFile(formData)
					console.log(formData);
 				});
				
 				function uploadFile(formData){
					$.ajax({
						url : "${path}/article/file/upload",
						data: formData,
						dataType : "text",
						processData: false,
						contentType: false,
						type: "POST",
						success : function(data){
							console.log(data);
							printFiles(data); // 첨부파일 출력 메서드 호출
							$(".noAttach").remove();
						}
					});
 				}
 				
 				//첨부파일 출력
 				function printFiles(data) {
 					//파일 정보 처리
 					var fileInfo = getFileInfo(data);
 					// Handlebars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
 					var html = fileTemplate(fileInfo);
 					// Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML을 DOM에 주입
 					$(".uploadedFileList").append(html);
 					// 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
 					if(fileInfo.fullName.substr(12,2) === "s_"){
 						// 마지막에 추가된 첨부파일 템플릿 선택자
 						var that = $(".uploadedFileList li").last();
 						// lightbox 속성 추가
 						that.find(".mailbox-attachment-name").attr("data-lightbox","uploadImages");
 						// 파일 아이콘에서 이미지 아이콘으로 변경
 						that.find(".fa-paperclip").attr("class","fa fa-camera");
 					}
 				}
 				
 				//게시글 입력/수정 submit 처리시에 첨부파일 정보도 함께 처리
 				function  filesSubmit(that) {
 					var str = "";
 					$(".uploadedFileList .delBtn").each(function(index){
 						str += "<input type='hidden' name='files[" + index + "]' value ='" + $(this).attr("href") + "'>"
 					});
 					that.append(str);
 					that.get(0).submit();
 				}
 				
 				// 파일 삭제(입력페이지) : 첨부파일만 삭제처리
 				function deleteFileWrtPage(that) {
 					var url = "${path}/article/file/delete";
 					deleteFile(url, that);
 				}
 				
 				// 파일 삭제 AJAX 통신
 				function deleteFile(url,that) {
 					$.ajax({
 						url : url,
 						type : "post",
 						data : {fileName: that.attr("href")},
 						dataType : "text",
 						success : function (result) {
 							if(result === "DELETED") {
 								alert("삭제되었습니다.");
 								that.parents("li").remove();
 							}
 						}
 					});
 				}
 				
 				//파일 정보 처리
 				function getFileInfo(fullName){
 					var originalFileName; //화면에 출력할 파일명
 					var imgSrc; //썸네일 or 파일아이콘 이미지 파일 출력 요청 URL
 					var originalFileUrl; //원본파일 요청 URL
 					var uuidFileName; // 날짜경로를 제외한 나머지 파일명(UUID_파일명.확장자)
 					
 					//이미지 파일이면
 					if(checkImageType(fullName)) {
 						imgSrc = "${path}/article/file/display?fileName="+fullName; // 썸네일 이미지 링크
 						uuidFileName = fullName.substr(14);
 						var originalImg = fullName.substr(0,12) + fullName.substr(14);
 						// 원본 이미지 요청 링크
 						originalFileUrl = "${path}/article/file/display?fileName=" + originalImg;
 					} else {
 						imgSrc = "${path}/resources/files/fileIcon.png" //파일 아이콘 이미지 링크
 						uuidFileName = fullName.substr(12);
 						//파일 다운로드 요청 링크
 						originalFileUrl = "${path}/article/file/display?fileName=" + fullName;
 					}
 					
 					originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);
 					
 					return {originalFileName: originalFileName, imgSrc: imgSrc, originalFileUrl: originalFileUrl, fullName:fullName};
 				}
 				
 				function checkImageType(fullName){
 					var pattern = /jpg$|gif$|png$|jpeg$/i;
 					return fullName.match(pattern);
 				}
				
 				$(document).on("click",".delBtn",function(event){
 					event.preventDefault();
 					var that = $(this);
 					deleteFileWrtPage(that);
 				});
 				
 				$("#writeForm").submit(function(event){
 					event.preventDefault();
 					var that = $(this);
 					filesSubmit(that);
 				})
			});
	</script>
</body>
</html>
