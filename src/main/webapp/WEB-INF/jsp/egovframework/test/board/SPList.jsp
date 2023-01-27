<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="aui" uri="/WEB-INF/lib/ajax-ui" %>
<!DOCTYPE html>
<html>
<head>
	<script defer src="http://code.jquery.com/jquery-latest.js"></script>
	<script defer src="/js/board/SPList.js"></script>
	<script defer src="/js/notice.js"></script>
    <link rel="stylesheet" href="/css/test/board/SPList.css">
    <!-- <link rel="stylesheet" href="/src/main/webapp/css/test/board/SPList.css"> -->
    <link rel="stylesheet" href="/css/test/user/loginInfo.css">
	<meta charset="UTF-8">
	<title><spring:message code="title.list" /></title>
    <!-- <title>글 목록</title> -->
</head>
<body>
<input type="hidden" id="hidden_msg" value="${msg}">
<%@ include file="../user/loginInfo.jspf" %>

<div class="div-body">
	<div class="board-table">
		<div class="board-help-box">
			<div class="search-box">
				<select name="tag" id="tag">
					<option value="" selected="selected"> --- </option>
					<option value="title"><spring:message code="board.title"/></option>
					<option value="content"><spring:message code="board.content"/></option>
					<option value="writer"><spring:message code="board.writer"/></option>
                    <!-- <option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option> -->
				</select>
				<input id="keyword" type="text" placeholder="<spring:message code='placeholder.search'/>">
                <!-- <input id="keyword" type="text" placeholder="검색어 입력"> -->
				<button onclick="searchKeyword();"><spring:message code="button.search"/></button>
                <!-- <button onclick="searchKeyword();">검색</button> -->
			</div>
			<div class="pagination-config">
				<spring:message code="recordCountPerPage"/>:&nbsp;
                <!-- 게시글 수:&nbsp; -->
				<select name="rcpp" id="rcpp" onchange="changeRCPP(this);">
					<option value=10 selected="selected">10</option>
					<option value=20>20</option>
					<option value=30>30</option>
				</select>
			</div>
		</div>
		<div class="main-table">
			<table id="table-board">
				<tr class="not-removable">
					<th><spring:message code='board.bno' /></th>
					<th><spring:message code='board.title' /></th>
					<th><spring:message code='board.writer' /></th>
					<th><spring:message code='board.regdate' /></th>
                </tr>
                
                <c:forEach items="${list }" var="vo" varStatus="status">
                	<fmt:parseNumber var="pageNum" integerOnly='true' value='${Math.floor(status.index div pageInfo.recordCountPerPage) + 1 }' />
                	<tr class="${pageNum }" style="display: none" >
                		<td class="td-bno">${vo.bno }</td>
                		<td><a href="/board/read?bno=${vo.bno}&page=1&rcpp=10">${vo.title }</a></td>
                		<td>${vo.writer }</td>
                		<td>${vo.regdate }</td>
                	</tr>
                </c:forEach>
                <!-- <tr>
                    <th>bno</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일자</th>
				</tr>

                <tr class="removable">
                    <td>10106</td>
                    <td>글 제목</td>
                    <td>user00</td>
                    <td>2023-10-12 10:31</td>
                </tr>
                <tr class="removable">
                    <td>10106</td>
                    <td>글 제목</td>
                    <td>user00</td>
                    <td>2023-10-12 10:31</td>
                </tr>
                <tr class="removable">
                    <td>10106</td>
                    <td>글 제목</td>
                    <td>user00</td>
                    <td>2023-10-12 10:31</td>
                </tr> -->

				<!-- <script> downloadPage()...... </script> -->				
			</table>
			
			<div class="board-pagination">
				<!-- pagination -->
				<div id="paging">
					<ui:pagination paginationInfo = "${pageInfo}" type="image" jsFunction="otherPage" />
		        </div>
			</div> <!-- board-pagination -->
			
		</div>
        <!-- /.main-table -->
        <div class="box-footer">
            <a href="/board/regist" class="button-write"><spring:message code="button.regist" /></a>
            <!-- <a href="/board/regist" class="button-write">글쓰기</a> -->
        </div>
	</div> <!-- /.board-table -->
	
	<div class="constants-box">
        <input type="hidden" id="totalRecordCount" value="${pageInfo.totalRecordCount }">
		<input type="hidden" id="page-constant" value="${pageInfo.currentPageNo }">
		<input type="hidden" id="rcpp-constant" value="${pageInfo.recordCountPerPage }">
		<input type="hidden" id="tag-constant" value="${tag }">
		<input type="hidden" id="keyword-constant" value="${keyword }">
		<input type="hidden" id="first-page-num" value="${pageInfo.firstPageNoOnPageList }">
		<input type="hidden" id="last-page-num" value="${pageInfo.lastPageNoOnPageList }">
	</div>
</div>

</body>
</html>