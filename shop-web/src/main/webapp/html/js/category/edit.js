$(document).ready(function(){
	$('input.back').click(function(){
		parent.history.back();
		return false;
	});
});