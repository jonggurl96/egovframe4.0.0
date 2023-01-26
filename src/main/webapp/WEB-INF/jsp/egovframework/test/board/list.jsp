<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<script>
var msg = "${msg}";
if (msg != null && msg != "") {
	alert(msg);
}
console.log(alert);
</script>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.list" /></title>
</head>
<body>
<div class="board-table">
	<table>
		<tr>
			<td><spring:message code="board.bno" /></td>
			<td><spring:message code="board.title" /></td>
			<td><spring:message code="board.writer" /></td>
			<td><spring:message code="board.regdate" /></td>
		</tr>
		
		<c:forEach items="${list }" var="board">
			<tr>
				<td><c:out value="${board.bno }" /></td>
				<td><a href="/board/read?bno=${board.bno }"><c:out value="${board.title }"/></a></td>
				<td><c:out value="${board.writer }" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate }"/></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
<div class="box-footer">
	<a href="/board/regist"><spring:message code="button.regist" /></a>
</div>
</body>
</html>