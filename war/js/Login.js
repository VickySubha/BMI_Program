/**
 * 
 */

$(document).ready(function(){
    var clicked=false;
	var $height=$(window).height();
	var $width=$(window).width();
	var tbHeight=Math.floor($height/2.6);
	var tbWidth=Math.floor($width/3.4);
	var inputWidth=Math.floor($width/5.265);
	var fontSize=14;
	$('main-form').css('height',tbHeight);
	$('main-form').css('margin-top',(($height/2)-(tbHeight/2)-50));
	if($width<895)
	{
		$('input[type="text"]').css('width',inputWidth);
		$('input[type="password"]').css('width',inputWidth);
		fontSize=Math.floor($width/50);
		if(fontSize>14)
			fontSize=14;
		if(fontSize<12)
			fontSize=12;
		$('body').css('font-size',fontSize);
	}
	
	window.onresize=function(){
		$height=$(window).height();
		$width=$(window).width();
		tbHeight=Math.floor($height/2.6);
		tbWidth=Math.floor($width/3.4);
		inputWidth=Math.floor($width/5.265);
		$('table').css('height',tbHeight);
		$('table').css('margin-top',(($height/2)-(tbHeight/2)-50));
		if($width<895)
		{
			$('input[type="text"]').css('width',inputWidth);
			$('input[type="password"]').css('width',inputWidth);
			fontSize=Math.floor($width/50);
			if(fontSize>14)
				fontSize=14;
			if(fontSize<12)
				fontSize=12;
			$('body').css('font-size',fontSize);
		}
	};
	
	$('#my-button').on('click',function(){
		if(!clicked)
		{
			$('table').css('margin-top',0);
			$('table').css('margin-left',($width/2)-(tbWidth/2));
		}
		else
		{
			$('table').css('margin-top',($height/2)-(tbHeight/2)-50);
			$('table').css('margin-left','auto');
			$('table').css('margin-right','auto');
		}
		clicked=!clicked;
	});
	
});
