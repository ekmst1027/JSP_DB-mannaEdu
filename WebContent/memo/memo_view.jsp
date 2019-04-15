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
	$("#btnUpdate").click(function() {
		var writer = $("#writer").val();
		var memo = $("#memo").val();
		if(writer == "") {
			alert("이름을 입력하세요");
			$("#writer").focus();
			return;
		}
		if(memo == "") {
			alert("메모를 입력하세요");
			$("#memo").focus();
			return;
		}
		document.form1.action="${path}/memo_servlet/update.do";
		document.form1.submit();
	});
	
	$("#btnDelete").click(function() {
		// 확인 버튼을 클릭하면 true
		if(confirm("삭제하시겠습니까?"))	{
			document.form1.action = "${path}/memo_servlet/del.do";
			document.form1.submit();
		}
	});
});
</script>
</head>
<body>
	<h2>메모 수정</h2>
	<form name="form1" id="form1" method="post">
		<table border="1" style="width: 550px">
			<tr>
				<td>이름</td>
				<!-- input 태그의 value 속성 ==> 태그에 표시되는 기본값 -->
				<td><input type="text" name="writer" id="writer" value="${vo.writer }" /></td>
			</tr>
			<tr>
				<td>메모</td>
				<td><input type="text" name="memo" id="memo" size = "60" value="${vo.memo }" /></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<!-- 수정, 삭제할 때 idx가 꼭 필요한 경우 hidden 태그에 저장 -->
					<input type="hidden" name="idx" id="idx" value="${vo.idx }">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>