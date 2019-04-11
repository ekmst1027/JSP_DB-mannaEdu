<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- set var="변수명" value="값" -->
<!-- page request session application -->
<!-- EL 변수값 설정 -->
<c:set var="num" value="<%=new Integer(100) %>" scope="page" />
<c:set var="num" value="200" scope="request" />
<c:set var="num" value="300" scope="session" />
<c:set var="num" value="400" scope="application" />
<!-- EL 변수값 출력 -->
<c:out value="${pageScope.num }" /><br>
${pageScope.num }<br>
<c:out value="${requestScope.num }" /><br>
${requestScope.num }<br>
<c:out value="${sessionScope.num }" /><br>
${sessionScope.num }<br>
<c:out value="${applicationScope.num }" /><br>
${applicationScope.num }<br>
<!-- if test="조건식" -->
<c:if test="${num > 10 }">
	${num } > 10
</c:if>
<%
int num = (Integer)pageContext.getAttribute("num");
if(num > 10) {
	out.println("java로 출력한 " + num + "<br>");
}
pageContext.setAttribute("num", 100);
request.setAttribute("num", 100);
session.setAttribute("num", 100);
application.setAttribute("num", 100);

out.println(pageContext.getAttribute("num"));
out.println(request.getAttribute("num"));
out.println(session.getAttribute("num"));
out.println(application.getAttribute("num"));
%>
 
</body>
</html>