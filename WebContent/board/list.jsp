<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "../include/header.jsp" %>
<script>
$(function() {
	$("#btnWrite").click(function() {
		location.href="${path}/board/write.jsp";
	});
});
</script>
</head>
<body>
	<h2>게시판</h2>
	
	<button type="button" id="btnWrite">글쓰기</button>
	
	<table border="1" width="900px">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
			<th>다운로드</th>
		</tr>
		<c:forEach var="vo" items="${list }"> <!-- var="개별값" items="집합" -->
			<tr align="center">
				<td>${vo.num }</td>	<!-- 실제로는 vo.getNum()이 호출됨 -->
				<td>${vo.writer }</td>
				<td>
					<a href="${path }/board_servlet/view.do?num=${vo.num}">
						${vo.subject }
					</a>
					<c:if test="${vo.comment_count > 0 }">
						[ ${vo.comment_count } ]					
					</c:if>
				</td>
				<td>${vo.reg_date }</td>
				<td>${vo.readcount }</td>
				<td>
					<c:if test="${vo.filesize > 0 }">
						<a href="${path }/board_servlet/download.do?num=${vo.num }">
							<img src="../images/file.gif">
						</a>
					</c:if>
				</td>
				<td>${vo.down }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>