<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>AJAX TEST PAGE</h2>
	
	<ul id="replies"></ul>
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		let bno = 10102;
		let listAjax = (bno) => {
			$.getJSON("/replies/all/" + bno, function(data) {
				console.log(data);
				var str = "";
				
				$(data).each(function() {
					str += "<li data-rno='" + this.rno+"' class='replyLi'>"
						+ this.rno + ":" + this.replytext
						+ "</li>";
				});
				
				$('#replies').html(str);
			});
		}
		listAjax(bno);
	</script>
</body>
</html>