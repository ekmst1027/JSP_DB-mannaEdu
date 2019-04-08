<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="common.Util.Util" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// 쿠키값을 불러옴
String count = Util.getCookie(request.getCookies(), "count");
int intCount = 0;
// 최근 방문 시간을 쿠키에 저장
Date date = new Date();	// 날짜 객체 생성
long now_time = date.getTime();	// 현재 시각
String visitTime = Util.getCookie(request.getCookies(), "visit_time");
long visit_time = 0;
// 쿠키가 존재하면
if(visitTime != null && !visitTime.equals("")) {
	visit_time = Long.parseLong(visitTime);
}

// 쿠키변수가 없으면(첫방문)
if(count == null || count.equals("")) {
	response.addCookie(new Cookie("count", "1"));	// 쿠키변수 생성
	// 방문시간을 쿠키에 저장
	response.addCookie( new Cookie("visit_time", Long.toString(now_time)));
	out.println("첫방문을 환영합니다.");
} else {
	// 최근 방문시간과 현재 시간을 비교
	long period = now_time - visit_time;
	out.println("현재 : " + now_time + "<br>");
	out.println("최근 : " + visit_time + "<br>");
	out.println("시차 : " + period + "<br>");
	intCount = Integer.parseInt(count);	// 스트링을 숫자로
	if(period > 3 * 1000) {
		// 카운트 증가
		intCount++;
		response.addCookie( new Cookie("count", Integer.toString(intCount)));
		// 방문시간 업데이트
		response.addCookie( new Cookie("visit_time", Long.toString(now_time)));
	}
	out.println("방문횟수 : " + intCount + "<br>");
	String counter = Integer.toString(intCount);
	// 스트링의 글자를 하나씩 분석하여 이미지 출력
	// 문자열.charAt(i) : i번째 글자
	for(int i = 0; i < counter.length(); i++) {
		String img = "<img src='../images/" + counter.charAt(i) + ".gif'>";
		out.println(img);
	}
}
%>
</body>
</html>