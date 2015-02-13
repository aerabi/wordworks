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
	scale('.word', 1 - 0.5 * Math.log(scaleVal));
});