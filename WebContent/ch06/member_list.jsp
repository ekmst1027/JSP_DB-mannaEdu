<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function view(userid) {
	// 현재 페이지의 폼 태그 하위의 userid 태그의 값을 변경함
	document.form1.userid.value = userid;
	// 폼데이터를 서버에 제출
	document.form1.submit();
}
</script>
</head>
<body>
	<%
	// 서블릿에서 넘겨준 map 변수를 받음 request.getAttribute()
	Map<String, Object> map = (Map<String, Object>) request.getAttribute("map");
	int count = (int)map.get("count");
	List<MemberVO> items = (List<MemberVO>) map.get("list");
	%>
	등록된 회원수 : <%=count %>명
	<table border="1">
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>가입일자</th>
			<th>주소</th>
			<th>전화</th>
		</tr>
		 <% for(MemberVO vo : items) {%>
		 <tr>
		 	<td><a href="#" onclick="view('<%=vo.getUserid()%>')"><%=vo.getName() %></a></td>
		 	<td><%=vo.getUserid() %></td>
		 	<td><%=vo.getPasswd() %></td>
		 	<td><%=vo.getReg_date() %></td>
		 	<td><%=vo.getAddress() %></td>
		 	<td><%=vo.getTel() %></td>
		 </tr>
		 <% } %>
	</table>
	<form name="form1" method="post" action="/jsp02/member_servlet/view.do">
		<input type="hidden" name="userid">
	</form>
	
</body>
</html>