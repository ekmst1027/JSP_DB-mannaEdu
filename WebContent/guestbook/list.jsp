<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
function gb_search() {
	document.form1.action="${path}/guestbook_servlet/list.do";
	document.form1.submit();
}
</script>
</head>
<body>
	<h2>방명록</h2>
	
	<!-- 검색폼 추가 -->
	<form name="form1" method="post">
		<select name="searchkey">
			<c:if test="${searchkey == 'name' }">
				<option value="name" selected>이름</option>
				<option value="content">내용</option>
				<option value="name_content">이름+내용</option>
			</c:if>
			
			<c:if test="${searchkey == 'content' }">
				<option value="name">이름</option>
				<option value="content" selected>내용</option>
				<option value="name_content">이름+내용</option>
			</c:if>
			
			<c:if test="${searchkey == 'name_content' }">
				<option value="name">이름</option>
				<option value="content">내용</option>
				<option value="name_content" selected>이름+내용</option>
			</c:if>
		</select>
		<input type="text" name="search" value="${search }">
		<input type="button" value="조회" onclick="gb_search()">
	</form>
	
	<input type="button" value="글쓰기" onclick="location.href='${path}/guestbook/write.jsp'">
	<c:forEach var="vo" items="${list }">
	<form action="${path }/guestbook_servlet/passwd_check.do" method="post">
		<input type="hidden" name="idx" value="${vo.idx }">
		<table border="1">
			<tr>
				<td>이름</td>
				<td>${vo.name }</td>
				<td>날짜</td>
				<td>${vo.post_date }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td colspan="3">${vo.email }</td>
			</tr>
			<tr>
				<td colspan="4">${vo.content }</td>
			</tr>
			<tr>
				<td colspan="4">
					비밀번호 <input type="password" name="passwd">
					<input type="submit" value="수정/삭제">
				</td>
			</tr>
		</table>
	</form>
	</c:forEach>
</body>
</html>