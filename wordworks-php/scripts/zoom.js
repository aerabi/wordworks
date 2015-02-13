var scaleVal = 1.0;

function scale(selector, val)
{
	$(selector).css('transform', 'scale(' + val + ')');
	$(selector).css('-o-transform', 'scale(' + val + ')');
	$(selector).css('-ms-transform', 'scale(' + val + ')');
	$(selector).css('-moz-transform', 'scale(' + val + ')');
	$(selector).css('-webkit-transform', 'scale(' + val + ')');
}

$(document).mousewheel(function(event) {
	scaleVal += 0.2 * event.deltaY;
	if(scaleVal < 0.7)
		scaleVal = 0.8;
	
	scale('html', scaleVal);
	scale('.word', 1 / (1 + Math.log(scaleVal)));
});

$(document).mousemove(function( event ) {
	$('html').css('transform-origin', event.pageX + " " + event.pageY);
	$('html').css('-o-transform-origin', event.pageX + " " + event.pageY);
	$('html').css('-ms-transform-origin', event.pageX + " " + event.pageY);
	$('html').css('-moz-transform-origin', event.pageX + " " + event.pageY);
	$('html').css('-webkit-transform-origin', event.pageX + " " + event.pageY);
});

$('html').click(function(){
	scaleVal = 1.0;
	scale('html', scaleVal);
	scale('.word', scaleVal);
});