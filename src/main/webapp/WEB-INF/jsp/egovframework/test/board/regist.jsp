<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer src="/js/board/regist.js"></script>
	<link rel="stylesheet" href="/css/test/user/loginInfo.css">
	<link rel="stylesheet" href="/css/test/board/regist.css">
	<title><spring:message code="title.write" /></title>
	<!-- <link rel="stylesheet" href="/src/main/webapp/css/test/board/regist.css"> -->
</head>
<body>
<%@ include file="../user/loginInfo.jspf" %>
<div class="regist-main-box">
	<form role="form" method="post">
		<div class="title-regist-box">
			<input type="text" name="title" id="regist-title" placeholder="Enter Title" >
		</div>
		<div class="content-regist-box">
			<textarea name="content" id="regist-content" rows="3" placeholder="Enter Content"></textarea>
		</div>
		<input type="hidden" name="writer" value="${loginInfo.id}">
		<!-- <input type="hidden" name="writer" value="jonggurl96"> -->
		
	</form>
</div>
<div class="regist-btns-box">
	<button type="submit" class="btn btn-primary"><spring:message code="button.regist" /></button>
	<button type="submit" class="btn btn-danger"><spring:message code="button.cancel" /></button>
	<!-- <button type="submit" class="btn btn-primary">글쓰기</button>
	<button type="submit" class="btn btn-danger">취소</button> -->
</div>
</body>
</html>