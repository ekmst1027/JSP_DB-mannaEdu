<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function() {
	$("#btnSave").click(function() {
		// 첨부파일의 확장자 체크
		var filename = form1.file1.value;	// 첨부파일의 이름
		var start = filename.lastIndexOf(".") + 1;	// 마지막 마침표의 위치
		if(start != -1) {
			var ext = filename.substring(start, filename.length);
			if(ext == "jsp" || ext == "exe" || ext == "jar") {
				alert("업로드할 수 없는 파일입니다.");
				return;
			}
		}
		// 폼데이터를 서버에 제출
		document.form1.submit();
	});
});
</script>
</head>
<body>
	<h2>글쓰기</h2>
	<form name="form1" method="post"
		action="${path }/board_servlet/insert.do"
		enctype="multipart/form-data">
		<table border="1" width="700px">
			<tr>
				<td align="center">이름</td>
				<td><input name="writer" id="writer"></td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td><input name="subject" id="subject" size="60"></td>
			</tr>
			<tr>
				<td align="center">본문</td>
				<td><textarea rows="5" cols="60" name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<td align="center">첨부파일</td>
				<td>
					<input type="file" name="file1">
					<c:if test="${param.message == 'error' }">
						<span style="color:red;">업로드할 수 없는 파일입니다.</span>
					</c:if>					
				</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="확인" id="btnSave">
				</td>
			</tr>
				
		</table>
		
	</form>
</body>
</html>