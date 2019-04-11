<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- xalan.jar 파일이 필요함 -->
	<h2>jstl xml 예제</h2>
	<x:parse var="xmldata">
		<items>	<!-- 루트노드 1개 -->
			<item>
				<model>Galaxy</model>
				<company>SKT</company>
				<product>SamSung</product>
			</item>
			<item>
				<model>Vega LTE</model>
				<company>SKT</company>
				<product>unKnown</product>
			</item>
			<item>
				<model>IPhone</model>
				<company>KT</company>
				<product>Apple</product>
			</item>
			<item>
				<model>옵티머스</model>
				<company>LG U+</company>
				<product>LG</product>
			</item>
		</items>
	</x:parse>
	<table border="1">
		<tr align="center">
			<th>모델</th>
			<th>통신사</th>
			<th>모델</th>
		</tr>
		<!-- XPath : xml 노드 탐색 -->
		<x:forEach select="$xmldata//item">
			<tr>
				<td><x:out select="./model" /></td>
				<td><x:out select="./company" /></td>
				<td>
					<x:choose>
						<x:when select="./product != 'unKnown'">
							<x:out select="./product" />
						</x:when>
						<x:otherwise>[알수 없음]</x:otherwise>
					</x:choose>
				</td>
			</tr>
		</x:forEach>
	</table>
</body>
</html>