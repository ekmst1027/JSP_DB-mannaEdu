<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
// 페이지가 로딩된 후 자동으로 실행되는 코드
$(function() {
	list("1");
});

function list(curPage) {
	var param = {"curPage" : curPage};
	$.ajax({
		url: "${path}/page_servlet/list.do",
		data: param,
		success: function(result) {	// 콜백함수, 서버의 처리가 완료된 후 실행
			$("#result").html(result);	// id가 result인 태그의 내용 변경
		}
	});
}
</script>
</head>
<body>
	<h2>페이지 나누기</h2>
	<div id="result"></div>
</body>
</html>