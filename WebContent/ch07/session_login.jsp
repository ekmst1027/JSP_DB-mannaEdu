<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<h2>세션 로그인</h2>
	<form method="post" name="form1" action="${path }/session_servlet/login.do">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input name="userid" id="userid"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input name="password" id="passwd"></td>
			</tr>		
			<tr>
				<td colspan="2" align="center"><input type="submit" id="btnLogin" value="로그인"></td>
			</tr>
		</table>
	</form>

</body>
</html>