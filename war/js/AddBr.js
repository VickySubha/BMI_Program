/**
 * 
 */


$(document).ready(function(){
	var brAdded=false;
	var brAddedInForm=false;
	if(($(window).width())<768)
	{
		if(!brAddedInForm)
		{
			$('#add-form-br').append('<br>');
			brAddedInForm=true;
		}
	}
	$('#my-button').on('click',function(){
		if(!brAdded)
		{
			$('#add-br').append('<br><br><br>');
			brAdded=true;
		}
	});
	
	$('.btn-danger').on('click',function(){
		alert("You are logged out");
	});
	
	
	$(window).resize(function(){
		var window_width=$(window).width();
		if(window_width>750)
		{
			$('#add-br').empty();
			brAdded=false;
			$('#add-form-br').empty();
			brAddedInForm=false;
		}
		else
		{
			if(!brAddedInForm)
			{
				$('#add-form-br').append('<br>');
				brAddedInForm=true;
			}
			if(!brAdded)
			{
				$('#add-br').append('<br><br><br>');
				brAdded=true;
			}
		}
	});
	
});