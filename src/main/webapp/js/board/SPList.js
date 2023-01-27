/**
 * ajax를 이용한 페이징 게시판
 */

let downloadPage = function (tag, keyword, currentPageNo, recordCountPerPage) {
	
	let data = {
			currentPageNo,
			recordCountPerPage,
			tag,
			keyword
	};
	if(!tag) {
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
		if($(this).val() == $('#rcpp-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});
	
	$('#tag option').each(function() {
		if($(this).val() == $('#tag-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});
	
	let keyword = $('#keyword-constant').val();
	$('#keyword').val(keyword);
	
	let currentPageNo = $('#page-constant').val();
	$('#table-board tr.' + currentPageNo).css("display", "table-row");

});

let otherPage = (pageNo) => {
	let fpn = $('#first-page-num').val();
	let lpn = $('#last-page-num').val();
	if(fpn <= pageNo && pageNo <= lpn) {
		let currentPageNo = $('#paging strong').text();
		$('#paging *:nth-child(' + currentPageNo + ')').val();
		return;
	}
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	
	downloadPage(tag, keyword, pageNo, rcpp);
}

let searchKeyword = () => {
	//otherPage(1);
}

let changeRCPP = (sel) => {

	let currentPageNo = $('#paging strong').text();
	let preRcpp = $('#rcpp-constant').val();
	
	let boardIndex = ( currentPageNo - 1) * preRcpp + 1;
	let newPage = Math.ceil(boardIndex / sel.value);
	
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	
	downloadPage(tag, keyword, newPage, sel.value);
}


