/**
 * ajax를 이용한 페이징 게시판
 */

let downloadPage = function(tag, keyword, currentPageNo, recordCountPerPage) {

	let data = {
		currentPageNo,
		recordCountPerPage,
		tag,
		keyword
	};
	if (!tag) {
		$('#keyword').val("");
	}

	$.ajax({
		type: "post",
		url: "/search",
		contentType: "application/json",
		dataType: "text",
		data: JSON.stringify(data),
		success: function(data) {

			$('#table-board .removable').remove();

			data = JSON.parse(data);
			let list = data.list;

			let str = "";
			list.forEach(element => {
				let href = "/board/read?bno=" + element.bno + "&currentPageNo=" + currentPageNo + "&recordCountPerPage=" + recordCountPerPage;
				str += "<tr class='removable'><td class='td-bno'>" + element.bno + "</td>";
				str += "<td><a href=" + href + ">" + element.title + "</a></td>";
				str += "<td>" + element.writer + "</td>";
				str += "<td>" + element.regdate + "</td>";
			});
			$('#table-board').append(str);

			delete data.pageInfo.tag;
			delete data.pageInfo.keyword;
			let pageInfo = JSON.stringify(data.pageInfo);
			let paginationStr = `<aui:pagination id='paging-tag' type='image' jsFunction='otherPage' jsonPaginationInfo=${pageInfo} />`;
			$('#paging').html(paginationStr);
		}
	});
	//self.location = "/board/SPList?currentPageNo=" + currentPageNo + "&recordCountPerPage=" + recordCountPerPage + "&tag=" + tag + "&keyword=" + keyword;
}

$(document).ready(function() {
	$('#rcpp option').each(function() {
		if ($(this).val() == $('#rcpp-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});

	$('#tag option').each(function() {
		if ($(this).val() == $('#tag-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});

	let keyword = $('#keyword-constant').val();
	$('#keyword').val(keyword);

	let currentPageNo = $('#page-constant').val();
	$('#table-board tr.' + currentPageNo).css("display", "table-row");

	let pagingbtns = String($('#paging').html()).replaceAll("&nbsp;", "");
	$('#paging').html(pagingbtns);

});

let otherPageList = (page) => {
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	self.location = "/board/SPList?tag=" + tag + "&keyword=" + keyword + "&recordCountPerPage=" + rcpp + "&currentPageNo=" + page;
}

let otherPage = (pageNo) => {
	let fpn = $('#first-page-num').val();
	let lpn = $('#last-page-num').val();

	if (!(fpn <= pageNo && pageNo <= lpn)) {
		otherPageList(pageNo);
		return;
	}

	let currentPageNo = $('#page-constant').val();
	currentPageNo = Number(currentPageNo);
	pageNo = Number(pageNo);
	$('#page-constant').val(pageNo);

	/** 페이지 버튼의 앞뒤에 추가되는 이전, 처음 버튼의 개수 */
	let additivity = 0;

	/** 한 화면의 모든 페이지에 모든 게시물을 보여줄 수 없는 경우 버튼 2개씩 앞뒤에 추가 */
	if ($('#totalRecordCount').val() > lpn * $('#rcpp-constant').val()) {
		additivity = 2;
	}

	/** 현재 페이지 링크로 변환 */
	$('#paging strong').remove();
	let cpn = currentPageNo - 1 + additivity;
	if (cpn == 0) {
		cpn = 1;
		$('#paging a:nth-child(1)').before(`<a href='#' onclick='otherPage(${currentPageNo}); return false' >${currentPageNo}</a>`);
	} else {
		$('#paging a:nth-child(' + cpn + ')').after(`<a href='#' onclick='otherPage(${currentPageNo}); return false' >${currentPageNo}</a>`);
	}

	/** 선택한 페이지 strong 태그로 변환 */
	let pn = pageNo + additivity;
	$('#paging a:nth-child(' + pn + ')').remove();
	if (pn == 1) {
		$('#paging a:nth-child(1)').before(`<strong>${pageNo}</strong>`);
	} else {
		$('#paging a:nth-child(' + (pn - 1) + ')').after(`<strong>${pageNo}</strong>`);
	}

	$('#table-board tr.' + currentPageNo).css("display", "none");
	$('#table-board tr.' + pageNo).css("display", "table-row");

	return;

}

let searchKeyword = () => {
	otherPageList(1);
}

let changeRCPP = (sel) => {

	let currentPageNo = $('#page-constant').val();
	let preRcpp = $('#rcpp-constant').val();

	let boardIndex = (currentPageNo - 1) * preRcpp + 1;
	let newPage = Math.ceil(boardIndex / sel.value);

	otherPageList(newPage);
}


