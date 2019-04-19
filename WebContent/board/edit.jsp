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
	$("#btnUpdate").click(function() {
		document.form1.action = "${path}/board_servlet/update.do";
		document.form1.submit();
	});
	
	$("#btnDelete").click(function() {
		if(confirm("삭제하시겠습니까?")) {
			document.form1.action="${path}/board_servlet/delete.do";
			document.form1.submit();
		}
	});
});
</script>
</head>
<body>
	<h2>수정/삭제</h2>
	<form name="form1" method="post" enctype="multipart/form-data">
		<table border="1" width="700px">
			<tr>
				<td>이름</td>
				<td><input name="writer" id="writer" value="${vo.writer }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input name="subject" id="subject" size="60" value="${vo.subject }"></td>
			</tr>
			<tr>
				<td>본문</td>
				<td>
					<textarea rows="5" cols="60" name="content" id="content">
						${vo.content }
					</textarea>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<c:if test="${vo.filesize > 0 }">
						${vo.filename } ( ${vo.filesize / 1024 } KB )
						<input type="checkbox" name="fileDel">
							첨부파일 삭제
						<br>
					</c:if>
					<input type="file" name="file1">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" id="passwd">
					<c:if test="${param.pwd_error == 'y' }">
						<span style="color:red">비밀번호가 일치하지 않습니다.</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 수정, 삭제를 위한 글번호 -->
					<input type="hidden" name="num" value="${vo.num }">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>