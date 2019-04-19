<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
// 페이지가 로딩되면
$(function() {
	// 목록 버튼을 클릭하면
	$("#btnList").click(function() {
		location.href="${path}/board_servlet/list.do";
	});
	
	// 수정, 삭제 화면(편집화면)으로 이동
	$("#btnEdit").click(function() {
		document.form1.action="${path}/board_servlet/pass_check.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
	<h2>상세화면</h2>
	<form name="form1" method="post">
		<table border="1" width="700px">
			<tr>
				<td width="10%" align="center">날짜</td>
				<td width="40%">${vo.reg_date }</td>
				<td width="10%">조회수</td>
				<td width="40%">${vo.readcount }</td>
			</tr>
			<tr>
				<td align="center">이름</td>
				<td colspan="3">${vo.writer }</td>
			</tr>
			<tr>
				<td align="center">제목</td>
				<td colspan="3">${vo.subject }</td>
			</tr>
			<tr>
				<td align="center">본문</td>
				<td colspan="3">${vo.content }</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td colspan="3">
					<input type="password" name="passwd" id="passwd">
					<c:if test="${param.message == 'error' }">
						<span style="color:red">비밀번호가 일치하지 않습니다.</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="center">첨부파일</td>
				<td colspan="3">
					<c:if test="${vo.filesize > 0}">
						${vo.filename }
						(${vo.filesize } bytes)
						<a href="${path }/board_servlet/download.do?num${vo.num}">[다운로드]</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<!-- 게시물번호 -->
					<input type="hidden" name="num" value="${vo.num }">
					<input type="button" value="수정/삭제" id="btnEdit">
					<input type="button" value="답변" id="btnReply">
					<input type="button" value="목록" id="btnList">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>