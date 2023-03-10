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
		self.location = "/board/SPList?currentPageNo=" + page + "&recordCountPerPage=" + rcpp;
	});
	
	getReplies();
});
let getReplies = () => {
	let bno = $('#bno').val();
	let delBtnVal = $('#delButton').val();
	let addBtnVal = $('#addButton').val();
	
	$.ajax({
		type: "get",
		url: '/comments/all/' + bno,
		success: function(data) {
			let str = "";
			$(data).each(function() {
				let marginLeft = (this.depth - 1) * 30;
				str += `<li class='ReplyList' style='margin-left: ${marginLeft}px'>`;
				str += "<label class='reply-writer' title='" + this.writer + "'>" + this.writer + "</label>";
				str += "<label class='reply-text'>" + this.content + "</label>";
				str += "<button class='reply-btns' onclick='deleteReply(" + this.cno + ", &quot;" + this.writer + "&quot;);'>" + delBtnVal + "</button>";
				str += "<button class='reply-btns' onclick='writeReply(" + this.cno + ", &quot;" + this.writer + "&quot;);'>" + addBtnVal + "</button></li>";
				
				preDepth = this.depth;
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
let writeComment = () => {
	let writer = $('#login-id').text();
	let content = $('#writtenReply').val();
	let bno = $('#bno').val();
	$.ajax({
		type:"post",
		url:"/comments/comment",
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
let writeReply = (cno, writer) => {
	let content = prompt();
	if (!content) return;
	$.ajax({
		type:"post",
		url:"/comments/reply",
		headers: {
			"Content-Type": "application/json",
			"X-HTTP-Method-Override": "post"
		},
		dataType: "text",
		data: JSON.stringify({
			writer,
			cno,
			content
		}),
		success: function(data) {
			if(!data) {
				alert("등록 실패 ㅠㅠ");
				return;
			}
			alert(data);
			getReplies();
		}
	});
}