/**
 * 
 */
$(document).ready(function() {
	let form = $("form[role='form']");
	$(".btn-primary").on("click", function() {
		form.attr("action", "/board/regist");
		form.submit();
	});
	$(".btn-danger").on("click", function() {
		self.location = "/board/SPList?currentPageNo=1&recordCountPerPage=10";
	});
});