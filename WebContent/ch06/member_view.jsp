<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(function() {
	// 수정 버튼을 누르면
	$("#btnUpdate").click(function() {
		document.form1.action = "/jsp02/member_servlet/update.do";
		document.form1.submit();
	});
	
	// 삭제 버튼을 누르면
	$("#btnDelete").click(function() {
		if(confirm("삭제하시겠습니까?")){
			document.form1.action = "/jsp02/member_servlet/delete.do";
			document.form1.submit();
		}
	});
});
</script>
</head>
<body>
<%
MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
	<form name="form1" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><%=vo.getUserid() %></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" value="<%=vo.getPasswd() %>"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="name" value="<%=vo.getName() %>"></td>
			</tr>
			<tr>
				<td>회원가입일자</td>
				<td><%=vo.getReg_date() %></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input name="address" value="<%=vo.getAddress() %>"></td>
			</tr>
			<tr>
				<td>전화</td>
				<td><input name="tel" value="<%=vo.getTel() %>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="userid" value="<%=vo.getUserid() %>">
					<button type="button" id="btnUpdate">수정</button>
					<button type="button" id="btnDelete">삭제</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>