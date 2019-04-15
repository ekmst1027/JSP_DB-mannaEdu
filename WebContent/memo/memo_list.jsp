<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function() {
	$("#chkAll").click(function() {
		// 선택자.prop("속성") : 체크상태가 true이면
		if($("#chkAll").prop("checked")) {
			// input 태그의 name이 idx인 태그의 checked 속성을 true로 설정
			$("input[name=idx]").prop("checked", true);
		} else {
			$("input[name=idx]").prop("checked", false);
		}
	});
	$("#btnAllDel").click(function() {
		document.form1.action = "${path}/memo_servlet/delete_all.do";
		document.form1.submit();
	});
});

function memo_del(idx) {
	location.href = "${path}/memo_servlet/del.do?idx=" + idx;
}
</script>
</head>
<body>
	<form method="post" name="form1">
		<table border="1">
			<tr>
				<th><input type="checkbox" id="chkAll"></th>
				<th>번호</th>
				<th>이름</th>
				<th>메모</th>
				<th>날짜</th>
				<th><input type="button" value="선택삭제" id="btnAllDel"></th>
			</tr>
			<!-- var="개별변수" items="집합변수" -->
		<c:forEach var="row" items="${list }">
			<tr>
				<td><input type="checkbox" name="idx" value="${row.idx }"></td>
				<td>${row.idx }</td>
				<td>${row.writer }</td>
				<td><a href="${path}/memo_servlet/view.do?idx=${row.idx}">${row.memo }</a></td>
				<td>${row.post_date }</td>
				<td><input type="button" value="삭제" onclick="memo_del('${row.idx}')"></td>
			</tr>
		</c:forEach>
		</table>
	</form>
	
</body>
</html>