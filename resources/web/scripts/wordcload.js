var w = window.innerWidth;
var h = window.innerHeight;

for(i=0; i<30; i++)
{
	var top = Math.floor(Math.random() * h);
	var right = Math.floor(Math.random() * w);
	
	// creat the word in space
	var div = document.createElement('div');
	div.id = 'd'+i;
	div.className = 'word';
	div.style.right = right + "px";
	div.style.top = top + "px";
	div.innerHTML = "word" + i;
	document.getElementsByTagName('body')[0].appendChild(div);
}