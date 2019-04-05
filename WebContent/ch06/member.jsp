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
	list();
	$("#btnSave").click(function() {
		insert();
	});
});

function insert() {
	// { 변수명:값, 변수명:값 }
	var param = {"userid":$("#userid").val(),
			"passwd":$("#passwd").val(),
			"name":$("#name").val(),
			"address":$("#address").val(),
			"tel":$("#tel").val()};
	$.ajax({
		type : "post",
		url : "/jsp02/member_servlet/join.do",
		data : param,
		success: function() {
			list();
			// 입력값 초기화
			$("#userid").val("");
			$("#passwd").val("");
			$("#name").val("");
			$("#address").val("");
			$("#tel").val("");
			$("#userid").focus();	// 입력포커스 이동
		}
	});
}

function list() {
	// 비동기적인 방식으로 호출(백그라운드에서 실행됨)
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/member_servlet/list.do",
		success : function(result) {
			$("#memberList").html(result);
		}
	});
}
</script>
</head>
<body>
	<h2>회원관리</h2>
	
	아이디 <input id="userid"><br>
	비번 <input type="password" id="passwd"><br>
	이름 <input id="name"><br>
	주소 <input id="address"><br>
	전화 <input id="tel">
	<button id="btnSave">추가</button>
	
	<div id="memberList"></div>	<!-- 회원목록을 출력할 영역 -->
</body>
</html>