<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- upload_result.jsp -->
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<!-- cos.jar -->
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%
// 파일업로드 디렉토리
String upload_path = "/Users/kyeongmin/Documents/Java_study/upload";
// 파일업로드 최대사이즈
int size = 10*1024*1024;	// byte 단위
String name = "";
String subject = "";
String filename ="", filename2 = "";
int filesize = 0, filesize2 = 0;
try {
	// request 객체 - 파일 업로드 기능이 없음
	// 파일업로드 지원 클래스(request 객체를 확장)
	// new MultipartRequest(request객체, 업로드경로, 최대사이즈, 파일이름, 인코딩처리방식, 파일이름이 중복되었을때의 처리)
	MultipartRequest multi = new MultipartRequest(request, upload_path, size, 
			"utf-8", new DefaultFileRenamePolicy());
	
	name = multi.getParameter("name");
	subject = multi.getParameter("subject");
	Enumeration files = multi.getFileNames();
	String file1 = (String)files.nextElement();
	String file2 = (String)files.nextElement();
	
	// 업로드한 파일의 이름
	filename = multi.getFilesystemName(file1);
	File f1 = multi.getFile(file1);
	
	// 업로드한 파일의 크기
	filesize = (int)f1.length();
	
	// 업로드한 파일의 이름
	filename2 = multi.getFilesystemName(file2);
	File f2 = multi.getFile(file2);
	
	// 업로드한 파일의 크기
	filesize2 = (int)f2.length();
	
} catch(Exception e) {
	e.printStackTrace();
}
%>
이름 : <%=name %><br>
제목 : <%=subject %><br>
파일1 이름 : <%=filename %><br>
파일1 크기 : <%=filesize %><br>
파일2 이름 : <%=filename2 %><br>
파일2 크기 : <%=filesize2 %><br> 
</body>
</html>