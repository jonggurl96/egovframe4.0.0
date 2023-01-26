<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">
var msg = "${msg}";
if (msg != null && msg != "") {
	alert(msg);
}
console.log(alert);
</script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function otherPage(pageNo) {
	self.location = "/board/paginatedList?page=" + pageNo + "&rcpp=" + $("#rcpp").val();
}

$(document).ready(function() {
	$('select option').each(function() {
		if($(this).val() == "${pageInfo.recordCountPerPage}") {
			$(this).attr("selected", "selected");
		}
	})
	
});

let changeRCPP = (sel) => {
	
	let firstBno = $('.td-bno').eq(0).text();
	let boardIndex = ( ${pageInfo.currentPageNo} - 1) * ${pageInfo.recordCountPerPage} + 1;
	let newPage = Math.ceil(boardIndex / sel.value);
	
	self.location="/board/paginatedList?page=" + newPage + "&rcpp=" + sel.value;
}

let searchKeyword = () => {
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	
	self.location="/board/search?tag=" + tag + "&keyword=" + keyword + "&rcpp=" + rcpp;
}

</script>
<head>
<meta charset="UTF-8">
<title><spring:message code="title.list" /></title>
</head>
<body>

<%@ include file="../user/loginInfo.jspf" %>

<div class="div-body" style="align:center; height:800px background-color:#ffffff">
	<div class="div-body-header" style="text-align:center; background-color:aqua">
		<div class="board-title">
			<h1><span>BOARD<br></span></h1>
			<h3><span>with eGovFramework</span></h3>
		</div> <!-- /.board-title -->
	</div> <!-- /.div-body-header -->
	
	<div class="board-table">
		<div class="board-help-box" style="background:#555555; margin:auto">
			<div class="search-box" style="align:left; text-align:left;float:left">
				<select name="tag" id="tag">
					<option value="title"><spring:message code="board.title"/></option>
					<option value="content"><spring:message code="board.content"/></option>
					<option value="writer"><spring:message code="board.writer"/></option>
				</select>
				<input id="keyword" type="text" placeholder="<spring:message code='placeholder.search'/>">
				<button onclick="searchKeyword();"><spring:message code="button.search"/></button>
			</div>
			<div class="pagination-config" style="align:right; text-align:right;float:right">
				<spring:message code="recordCountPerPage"/>:&nbsp;
				<select name="rcpp" id="rcpp" onchange="changeRCPP(this);">
					<option value=10>10</option>
					<option value=20>20</option>
					<option value=30>30</option>
				</select>
			</div>
		</div>
		<div style="clear:both;background:#ffff99">
			<table>
				<tr>
					<th><spring:message code="board.bno" /></th>
					<th><spring:message code="board.title" /></th>
					<th><spring:message code="board.writer" /></th>
					<th><spring:message code="board.regdate" /></th>
				</tr>
				
				<c:forEach items="${list }" var="board">
					<tr>
						<td class="td-bno"><c:out value="${board.bno }" /></td>
						<td><a href="/board/read?bno=${board.bno }&page=${pageInfo.currentPageNo}&rcpp=${pageInfo.recordCountPerPage}"><c:out value="${board.title }"/></a></td>
						<td><c:out value="${board.writer }" /></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate }"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div> <!-- /.board-table -->
	
	<div class="board-pagination">
		<!-- pagination -->
		<div id="paging">
        	<ui:pagination paginationInfo = "${pageInfo}" type="image" jsFunction="otherPage" />
        	<!-- <input type="hidden" name="page"> -->
        </div>
	</div> <!-- board-pagination -->
	
	<div class="box-footer">
		<a href="/board/regist"><spring:message code="button.regist" /></a>
	</div>
</div>

</body>
</html>