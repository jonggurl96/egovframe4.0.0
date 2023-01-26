/**
 * 
 */
$(document).ready(function() {
	$('.login-btn-box input').on("click", function() {
		$('.login-info-box').submit();
	});
	$('.regist-btn-box input').on("click", function() {
		self.location="/user/regist";
	});
});