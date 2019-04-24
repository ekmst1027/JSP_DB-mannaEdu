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
<!-- 관리자 로그인의 경우 관리자 메뉴 표시 -->
<c:if test="${sessionScope.admin_userid != null }">
	<%@ include file="../include/admin_menu.jsp" %>
</c:if>
<!-- 일반 사용자 로그인의 경우 일반사용자용 메뉴 표시  -->
<c:if test="${sessionScope.admin_userid == null }">
	<%@ include file="../include/menu.jsp" %>
</c:if>

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
					<!-- 관리자에게만 [편집] 링크 표시 -->
					<c:if test="${sessionScope.admin_userid != null }">
						<a href="${path }/admin_servlet/edit.do?product_id=${row.product_id}">[편집]</a>
					</c:if>
				</td>
				<td>${row.price }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>