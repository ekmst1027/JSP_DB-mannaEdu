<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function() {	// 현재 페이지를 모두 불러온 후 자동으로 실행되는 코드
	list();
	$("#btnSave").click(function() {	// id가 btnSave인 버튼을 누르면
		insert();
	});
	// 검색 버튼 클릭
	$("#btnSearch").click(function() {
		list();
	});
});

function insert() {
	var writer = $("#writer").val();	// 태그에 입력된 값
	var memo = $("#memo").val();
	var param = {"writer": writer, "memo": memo};	// {"변수": 값}
	$.ajax({
		type: "post",
		url: "${path}/memo_servlet/insert.do",
		data: param,
		success: function() {
			list();	// 목록 갱신
			$("#writer").val("");	// 입력 태그를 초기화시킴
			$("#memo").val("");
		}
	});
}

function list() {
	// 백그라운드에서 실행되는 서블릿 호출
	// 자바스크립트 객체 {"변수명": 값, "변수명": 값}
	var param = {
			"searchkey": $("#searchkey").val(),
			"search": $("#search").val()
	};
	$.ajax({
		type: "post",
		url: "${path}/memo_servlet/list.do",
		data: param,
		success: function(result) {
			// list.do의 출력결과 텍스트를 id가 result인 태그에 추가함
			$("#result").html(result);
		}
	});
}
</script>
</head>
<body>
	<h2>한줄메모장</h2>
	이름 : <input id="writer" size="10">
	메모 : <input id="memo" size="40">
	<input type="button" id="btnSave" value="확인">
	
	<br>
	<select id="searchkey">
		<option value="writer">이름</option>
		<option value="memo">메모</option>
		<option value="writer_memo">이름+메모</option>
	</select>
	<input type="text" id="search" value="${search }">
	<input type="button" id="btnSearch" value="조회">
	
	<div id="result"></div><!-- 메모 내용이 출력될 영역 -->
</body>
</html>


