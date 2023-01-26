<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>   
	<script defer src="/js/board/read.js"></script>
	<script defer src="/js/notice.js"></script>
    <link rel="stylesheet" href="/css/test/user/loginInfo.css">
    <link rel="stylesheet" href="/css/test/board/read.css">
    <!-- <link rel="stylesheet" href="/src/main/webapp/css/test/board/read.css"> -->
	<title><spring:message code="title.read" /></title>
</head>
<body>

<%@ include file="../user/loginInfo.jspf" %>

<input type="hidden" id="hidden_msg" value="${msg}">
<spring:eval expression="@propertiesService.getString('delButton')" var="delButton"/>
<input type="hidden" id="delButton" value="${delButton }">

<form role="form" method="post">
	<input type="hidden" name="bno" value="${vo.bno}" id="bno">
	<input type="hidden" name="page" value="${cri.page}" id="page">
	<input type="hidden" name="rcpp" value="${cri.rcpp}" id="rcpp">
</form>

<div class="main-content">
	<div class="written-info-box">
		<div class="writer-left-box">
			<c:out value="${vo.writer}" />
			<input type="hidden" value="${vo.writer }" id="writer">
			<!-- 작성자: jonggurl96 -->
		</div>
		<div class="written-date-right-box">
			<%-- <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.regdate }"/> --%>
			<p><span>${vo.regdate }</span></p>
			<!-- 작성일자: 2023-01-12 13:38 -->
		</div>
	</div>

	<div class="title-box">
		<c:out value="${vo.title }"/>
		<!-- 제목 -->
	</div>

	<div class="content-box">
		<div>
			<textarea id="written-content-textarea" rows="3" readonly="readonly"><c:out value="${vo.content }" /></textarea>
		</div>
		<!-- <textarea id="written-content-textarea" rows="3" readonly="readonly">순대국밥 먹고 혼코노</textarea> -->
	</div>
	
	<div class="content-button-box">
		<button type="submit" class="btn btn-warning"><spring:message code="button.modify"/></button>
		<button type="submit" class="btn btn-danger"><spring:message code="button.delete"/></button>
		<button type="submit" class="btn btn-primary"><spring:message code="button.list"/></button>
		<!-- <button type="submit" class="btn btn-warning">수정하기</button>
		<button type="submit" class="btn btn-danger">삭제하기</button>
		<button type="submit" class="btn btn-primary">목록으로</button> -->
	</div>
</div>

<div class="reply-main-box">
	<div class="reply-regist-box">
		<spring:message code="placeholder.regist.reply" var="placeholder"/>
		<textarea id="writtenReply" rows="3" placeholder="${placeholder}"></textarea>
		<button onclick="writeComment();"><spring:message code="reply.submit"/></button>
		<!-- <textarea id="writtenReply" rows="3" placeholder="댓글을 입력하세요."></textarea>
		<button id="writeReplyBtn" onclick="writeReply();">등록</button> -->
	</div>
	<div class="replies-box">
		<ul id="replies">
			<li class="ReplyList">
				<label class="reply-writer" title="jonggurl96">jonggurl96</label>
				<label class="reply-text">111111</label>
				<button class="del-reply-btn" onclick='console.log(null);'>삭제</button>
			</li>
			<li class="ReplyList">
				<label class="reply-writer" title="user12">user12</label>
				<label class="reply-text">만약에 댓글이 굉장히 길면 자동으로 개행되어야 하는데 잘 되었으면 좋겠다.</label>
				<button class="del-reply-btn" onclick='console.log(null);'>삭제</button>
			</li>
			<li class="ReplyList">
				<label class="reply-writer" title="user00">user00</label>
				<label class="reply-text">222222</label>
				<button class="del-reply-btn" onclick='console.log(null);'>삭제</button>
			</li>
		</ul>
	</div>
</div>

</body>
</html>