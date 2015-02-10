var h = window.innerHeight;
var w = window.innerWidth;

for(i=0; i<30; i++)
{
	var divTop = Math.floor(Math.random() * h);
	var divRight = Math.floor(Math.random() * w);
	
	// creat the word in space
	var div = document.createElement('div');
	div.id = 'd'+i;
	div.className = 'word';
	div.style.top = divTop + "px";
	div.style.right = divRight + "px";
	div.innerHTML = "<a>word" + i + "</a>";
	document.getElementsByTagName('body')[0].appendChild(div);
}