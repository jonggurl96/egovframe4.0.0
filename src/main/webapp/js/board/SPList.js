/**
 * ajax를 이용한 페이징 게시판
 */

let getDate = (num) => {
	let date = new Date(num);
	
	let month = date.getMonth() + 1;
	let day = date.getDate();
	let hours = date.getHours();
	let minutes = date.getMinutes();
	
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	hours = hours < 10 ? "0" + hours : hours;
	minutes = minutes < 10 ? "0" + minutes : minutes;

	return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes;
}

let downloadPage = function (tag, keyword, page, rcpp, totalRecordCount) {
	
	let data = {
			page,
			rcpp,
			totalRecordCount
	};
	if(tag === "all") {
		$('#keyword').val("");
	}
	else {
		data["tag"] = tag;
		data["keyword"] = keyword;
	}
	
	$.ajax({
		type: "post",
		url: "/search",
		contentType: "application/json",
		dataType: "text",
		data: JSON.stringify(data),
		success: function(data) {
		
			$('#table-board.removable').remove();
			
			data = JSON.parse(data);
			let str = "";
			
			data.forEach(element => {
				let href = "/board/read?bno=" + element["bno"] + "&page=" + page + "&rcpp=" + rcpp;
				str += "<tr class='removable'><td class='td-bno'>" + element["bno"] + "</td>";
				str += "<td><a href=" + href + ">" + element["title"] + "</a></td>";
				str += "<td>" + element["writer"] + "</td>";
				str += "<td>" + getDate(element["regdate"]) + "</td>";
			});
			$('#table-board').append(str);
		}
	});
}

$(document).ready(function() {
	$('#rcpp option').each(function() {
		if($(this).val() == $('#rcpp-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});
	let rcpp = $('#rcpp').val();
	
	$('#tag option').each(function() {
		if($(this).val() == $('#tag-constant').val()) {
			$(this).attr("selected", "selected");
		}
	});
	let tag = $('#tag').val();
	
	let keyword = $('#keyword-constant').val();
	$('#keyword').val(keyword);
	
	let pageNo = $('#paging strong').text();
	let totalRecordCount = $('#totalRecordCount').val();
	downloadPage(tag, keyword, pageNo, rcpp, totalRecordCount);
	
});

let redirect = (tag, keyword, pageNo, rcpp) => {
	let redirectURL = "/board/SPList?page=" + pageNo + "&rcpp=" + rcpp;
	if(!(tag == "all")) {
		redirectURL += "&tag=" + tag + "&keyword=" + keyword;
	}
	self.location = redirectURL;
}

function otherPage(pageNo) {
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	let rcpp = $('#rcpp').val();
	console.log(pageNo);
	
	redirect(tag, keyword, pageNo, rcpp);
}

let searchKeyword = () => {
	otherPage(1);
}

let changeRCPP = (sel) => {

	let currentPageNo = $('#paging strong').text();
	let preRcpp = $('#rcpp-constant').val();
	
	let boardIndex = ( currentPageNo - 1) * preRcpp + 1;
	let newPage = Math.ceil(boardIndex / sel.value);
	
	let tag = $('#tag').val();
	let keyword = $('#keyword').val();
	
	redirect(tag, keyword, newPage, sel.value);
}
