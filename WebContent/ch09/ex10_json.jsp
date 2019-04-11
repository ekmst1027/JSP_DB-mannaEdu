<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(function() {
	// json 파일을 분석하는 함수
	$.getJSON("item.json", function(data, textStatus) {
		console.log(data);
		console.log(textStatus)
		// data - json 데이터
		// textStatus - success/fail
		$("#fruit").append("<tr><td>id</td><td>과일</td><td>가격</td><td>비고</td>");
		// json의 각 행을 처리하는 함수
		$.each(data, function() {
			$("#fruit").append("<tr><td>"+this.id+"</td>"
					+"<td>"+this.name+"</td><td>"+this.price+"</td>"
					+"<td>"+this.description+"</td></tr>");
		});
	});
});
</script>
</head>
<body>
	<h2>JSON 자료의 출력</h2>
	<table id="fruit" border="1">
	
	</table>
</body>
</html>