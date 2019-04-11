<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<!-- 출력형식을 html로 지정, indent 들여쓰기 -->
	<xsl:output method="html" indent="yes" encoding="utf-8" />
	<xsl:template match="items">	<!-- 루트노드를 찾음 -->
		<html>
			<body>
				<h2>주소록</h2>
				<table border="1">
					<tr>
						<th>이름</th>
						<th>생년월일</th>
						<th>전화</th>
						<th>주소</th>
					</tr>
					<!-- person 태그를 모두 선택 -->
					<xsl:for-each select="person">
						<tr>
							<!-- 태그의 값 -->
							<td><xsl:value-of select="name" /></td>
							<td><xsl:value-of select="birth" /></td>
							<td><xsl:value-of select="tel" /></td>
							<td><xsl:value-of select="address" /></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
		
	</xsl:template>
</xsl:stylesheet>