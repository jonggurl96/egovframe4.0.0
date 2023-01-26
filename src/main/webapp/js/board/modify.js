/**
 * 
 */

$(document).ready(function() {
	let form = $("form[role='form']");
	$(".btn-primary").on("click", function() {
		form.attr("action", "/board/modify");
		form.submit();
	});
	$(".btn-danger").on("click", function() {
		let bno = $('#bno').val();
		let page = $('#page').val();
		let rcpp = $('#rcpp').val();
		self.location = "/board/read?bno=" + bno + "&currentPageNo=" + page + "&recordCountPerPage=" + rcpp;
	});
});
