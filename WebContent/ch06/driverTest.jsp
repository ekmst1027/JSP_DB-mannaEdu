<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 그냥 예시(나중에 아이디 비번 바꿔서 실행) -->
<%
Connection conn = null;
try {
	String jdbcUrl = "jdbc:mysql://localhost:3306/java?useSSL=false&serverTimezone=UTC";
	String dbId = "id";
	String dbPass = "ps";
	// jdbc 드라이버 로딩
	Class.forName("com.mysql.cj.jdbc.Driver");
	conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
	out.println("MySQL에 접속되었습니다.");
} catch(Exception e) {
	out.println("MySQL 접속 에러...");
	e.printStackTrace();
}
%>
</body>
</html>