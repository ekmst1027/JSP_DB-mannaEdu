<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Integer countNum = (Integer) session.getAttribute("counter");
int num = 0;
// counter 이름의 세션값을 저장
// counter란 이름의 세션값이 없으면
if(countNum == null) {
	countNum = 1;	// 초기값
} else {
	num = countNum.intValue();	// 정수로 변환
	num++;	// 증가 처리
	countNum = num;
}
// 세션값 변경
session.setAttribute("counter", countNum);
%>
당신은 <%=countNum.intValue() %>번째 방문하셨습니다.<br>
<%
String counter = Integer.toString(num);
for(int i = 0; i < counter.length(); i++) {
	// 문자열.charAt(인덱스) : 문자열의 n번째 문자 리턴
	String img = "<img src='../images/" + counter.charAt(i) + ".gif'>";
	out.println(img);
}
%>
</body>
</html>