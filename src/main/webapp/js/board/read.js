/**
 * 
 */
$(document).ready(function() {
	let form = $("form[role='form']");
	let writer = $('#writer').val();
	let id = $('#login-id').text();
	
	if(id != writer) {
		$(".btn-warning").attr("style", "display: none");
		$(".btn-danger").attr("style", "display: none");
	} else {
		$(".btn-warning").on("click", function() {
			form.attr("action", "/board/modify");
			form.attr("method", "get");
			form.submit();
		});
		$(".btn-danger").on("click", function() {
			form.attr("action", "/board/remove");
			form.submit();
		});
	}
	
	$(".btn-primary").on("click", function() {
		let page = $('#page').val();
		let rcpp = $('#rcpp').val();
		self.location = "/board/SPList?page=" + page + "&rcpp=" + rcpp;
	});
	
	getReplies();
});
let getReplies = () => {
	let bno = $('#bno').val();
	let delBtnVal = $('#delButton').val();
	
	$.ajax({
		type: "get",
		url: '/comments/all/' + bno,
		success: function(data) {
			let str = "";
			$(data).each(function() {
				str += "<li class='ReplyList'>";
				str += "<label class='reply-writer' title='" + this.writer + "'>" + this.writer + "</label>";
				str += "<label class='reply-text'>" + this.content + "</label>";
				str += "<button class='del-reply-btn' onclick='deleteReply(" + this.cno + ", &quot;" + this.writer + "&quot;);'>" + delBtnVal + "</button></li>";
			});
			$('#replies').html(str);
		}
	});
}
let deleteReply = (cno, writer) => {
	let id = $('#login-id').text();
	if(id != writer) {
		alert("작성자만 삭제 가능합니다.");
		return;
	}
	$.ajax({
		type: "delete",
		url: "/comments/" + cno,
		success: function(data) {
			alert(data);
			getReplies();
		}
	});
}
let writeReply = () => {
	let writer = $('#login-id').text();
	let content = $('#writtenReply').val();
	let bno = $('#bno').val();
	$.ajax({
		type:"post",
		url:"/comments/",
		headers: {
			"Content-Type":"application/json",
			"X-HTTP-Method-Override": "post"
		},
		dataType: "text",
		data: JSON.stringify({
			content,
			writer,
			bno
		}),
		success: function(msg) {
			alert(msg);
			getReplies();
			$('#writtenReply').val("");
		}
	});
}