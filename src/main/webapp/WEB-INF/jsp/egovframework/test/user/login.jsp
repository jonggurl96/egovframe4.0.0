<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer src="/js/user/login.js"></script>
	<script defer src="/js/notice.js"></script>
	<link rel="stylesheet" href="/css/test/user/login.css">
	<!-- <link rel="stylesheet" href="/src/main/webapp/css/test/user/login.css"> -->
</head>
<body>

<input type="hidden" id="hidden_msg" value="${msg}">

<div class="home-login-box">
	<div class="login-logo">
		<div><h1><span><spring:message code="label.login"/></span></h1></div>
		<!-- <div><h1><span>로그인</span></h1></div> -->
	</div>
	<div class="login-components">
		<div class="account-input-box">
			<form class="login-info-box" method="post" action="/user/loginPost">
				<div class="id-box">
					<label><span><spring:message code="label.id"/></span></label>
					<input type="text" name="id" placeholder=<spring:message code="label.id.placeholder"/>>
					<!-- <label class="login-info-label"><span>ID</span></label>
					<input type="text" name="id" placeholder="ID"> -->
				</div>
				<div class="pw-box">
					<label><span><spring:message code="label.pw"/></span></label>
					<input type="password" name="pw" placeholder=<spring:message code="label.pw.placeholder"/>>
					<!-- <label class="login-info-label"><span>PW</span></label>
					<input type="password" name="pw" placeholder="PW"> -->
				</div>
			</form>
		</div>
		<!-- /.account-input-box -->
		<div class="login-btn-box">
			<input type="submit" value="<spring:message code="label.login"/>">
			<!-- <input type="submit" value="로그인"> -->
		</div>
	</div>

	<div class="regist-btn-box">
		<input type="submit" value="<spring:message code="label.user.regist"/>">
		<!-- <input type="submit" value="회원가입"> -->
	</div>
</div>

</body>
</html>