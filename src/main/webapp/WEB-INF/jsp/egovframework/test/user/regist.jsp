<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer src="/js/user/regist.js"></script>
	<script defer src="/js/notice.js"></script>
</head>
<body>
<input type="hidden" id="hidden_msg" value="${msg }">
<div class="home-logo">
</div>
<div class="home-login-box" style="align:center;text-align:center">
	<div class="login-logo">
		<div><h1><span><spring:message code="label.user.regist"/></span></h1></div>
	</div>
	<form class="login-info-box" method="post">
		<div class="id-box">
			<span><spring:message code="label.id"/></span>
			<input type="text" name="id" placeholder=<spring:message code="label.id.placeholder"/>>
		</div>
		<div class="pw-box">
			<span><spring:message code="label.pw"/></span>
			<input type="password" name="pw" placeholder=<spring:message code="label.pw.placeholder"/>>
		</div>
	</form>
	<div class="submit-btn-box">
		<input type="submit" value="<spring:message code="button.submit"/>">
	</div>
	<div class="cancel-btn-box">
		<input type="submit" value="<spring:message code="button.cancel"/>">
	</div>
</div>
<div class="footer">
</div>
</body>
</html>