/**
 * 
 */

$(document).ready(function(){
	$('#main-form').css('margin-top',(($(window).height())/2)-($('#main-form').height()/2)-($('.navbar').height())-50);	
	window.onresize=function(){
		$('#main-form').css('margin-top',(($(window).height())/2)-($('#main-form').height()/2)-($('.navbar').height())-50);
	};
	
});