/**
 * 
 */
$(document).ready(function() {
	$('.submit-btn-box input').on("click", function() {
		$('.login-info-box').submit();
	});
	$('.cancel-btn-box input').on("click", function() {
		self.location="/user/login";
	});
});