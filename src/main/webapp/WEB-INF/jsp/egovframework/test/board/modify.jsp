<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>   
	<script defer src="/js/board/modify.js"></script>
	<link rel="stylesheet" href="/css/test/board/modify.css">
	<link rel="stylesheet" href="/css/test/user/loginInfo.css">
	<title><spring:message code="title.modify"/></title>

	<!-- <link rel="stylesheet" href="/src/main/webapp/css/test/board/modify.css"> -->
</head>
<body>
<%@ include file="../user/loginInfo.jspf" %>

<div class="modify-main-box">
	<form role="form" method="post">
		<input type="hidden" value="${cri.page}" name="page" id="page"/>
		<input type="hidden" value="${cri.rcpp}" name="rcpp" id="rcpp"/>
		<input type="hidden" value="${vo.bno}" name="bno" id="bno"/>
		<div class="title-modified-box">
			<input type="text" name="title" value="${vo.title}" id="modified-title" placeholder="${vo.title}">
			<!-- <input type="text" name="title" value="오늘 스케줄" id="modified-title" placeholder="오늘 스케줄"> -->
		</div>
		<div class="content-modified-box">
			<textarea name="content" rows="3" id="modified-content" placeholder="${vo.content}"><c:out value="${vo.content}" /></textarea>
			<!-- <textarea name="content" rows="3" id="modified-content" placeholder="순대국밥 먹고 혼코노 가기">순대국밥 먹고 혼코노 가기</textarea> -->
		</div>
	</form>
</div>
<div class="modify-btns-box">
	<button type="submit" class="btn btn-primary"><spring:message code="button.modify" /></button>
	<button type="submit" class="btn btn-danger"><spring:message code="button.cancel" /></button>
	<!-- <button type="submit" class="btn btn-primary">수정하기</button>
	<button type="submit" class="btn btn-danger">취소</button> -->
</div>
</body>
</html>