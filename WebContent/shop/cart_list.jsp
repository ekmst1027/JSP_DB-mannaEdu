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
	$("#btnList").click(function() {
		location.href="${path}/product_servlet/list.do";
	});
	
	// 장바구니 비우기 버튼 클릭
	$("#btnDelete").click(function() {
		if(confirm("장바구니를 비우시겠습니까?")) {
			location.href="${path}/cart_servlet/deleteAll.do";
		}
	});
});
</script>
</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<form id="form1" name="form1" method="post" action="${path }/cart_servlet/update.do">
		<table border ="1" width="400px">
			<tr>
				<th>상품명</th>
				<th>단가</th>
				<th>수량</th>
				<th>금액</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach var="row" items="${list }">
				<tr>
					<td><span>${row.product_name }</span></td>
					<td>${row.price }</td>
					<td>
						<input type="number" sylte="width: 50px;"
							min="0" max="100" name="amount" value="${row.amount }">
						<input type="hidden" name="product_id" value="${row.product_id }">
						<!-- 장바구니 일련번호 -->
						<input type="hidden" name="cart_id" value="${row.cart_id }">
					</td>
					<td>${row.money }</td>
					<td><a href="${path }/cart_servlet/delete.do?cart_id=${row.cart_id}">삭제</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="right">
					<c:if test="${map.sumMoney > 0 }">
						장바구니 금액 합계 :
							<fmt:formatNumber value="${map.sumMoney }" pattern="#,###,###" /><br>
						배송료 : ${map.fee }<br>
						총합계 :
							<fmt:formatNumber value="${map.sum }" pattern="#,###,###" />
					</c:if>
					<c:if test="${map.sumMoney == 0 }">
						장바구니가 비었습니다.
					</c:if>
				</td>
			</tr>
		</table>
		<button id="btnUpdate">수정</button>
	</form>
	
	<button type="button" id="btnDelete">장바구니 비우기</button>
	<button type="button" id="btnList">상품목록</button>

</body>
</html>