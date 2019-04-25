<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<!-- 구글 차트 호출을 위한 js 파일 -->
<script src="http://www.google.com/jsapi"></script>
<script>
	// 구글 차트 라이브러리 로딩
	google.load('visualization', '1', {
		'packages' : [ 'corechart' ]
	});
	
	// 로딩이 완료되면 drawChart 함수 호출
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var jsonData = $.ajax({
			url : "${path}/chart_servlet/chart_money_list.do",
			dataType : "json",
			async : false
		}).responseText;
		console.log(jsonData);
		// 데이터 테이블 생성
		var data = new google.visualization.DataTable(jsonData);
		// 차트를 출력할 div
		// LineChart, ColumnChart, PieChart
		var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
// 		var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
// 		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		
		chart.draw(data, {
			title : "차트 예제",
			width : 600,
			height : 440
		});
	}
</script>
</head>
<body>
	<!-- 차트 출력 영역 -->
	<div id="chart_div"></div>
	<!-- 차트 새로고침 버튼 -->
	<button id="btn" type="button" onclick="drawChart()">refresh</button>
</body>
</html>