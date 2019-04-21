<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<h2>상품목록</h2>
	<table border="1" width="500px">
		<tr>
			<th>상품ID</th>
			<th>&nbsp;</th>
			<th>상품명</th>
			<th>가격</th>
		</tr>
		<c:forEach var="row" items="${list }">	<!-- var="개별값" items="집합" -->
			<tr align="center">
				<td>${row.product_id }</td>
				<td>
					<img src="../images/${row.picture_url }" width="100px" height="100px">
				</td>
				<td>
					<a href="${path }/product_servlet/detail.do?product_id=${row.product_id}">
						${row.product_name }
					</a>
				</td>
				<td>${row.price }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>