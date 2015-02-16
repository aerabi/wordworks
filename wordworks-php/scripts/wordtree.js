var h = window.innerHeight - 30;
var w = window.innerWidth - 60;

var expandeds = []

function fillTheTree(str, word, x, y, parent, green)
{
	var list = str.substring(0, str.length-4).split("<br>");
	
	// finding radius
	var r = Math.min(h - y, Math.min(w - x, y));
	
	var newWords = [];
	for(i=0; i<list.length; i++)
	{
		if(expandeds.indexOf(list[i]) == -1)
		{
			newWords.push(list[i]);
			expandeds.push(list[i]);
		}
	}
	
	if(expandeds.indexOf(word) == -1)
	{
		for(i=0; i<newWords.length; i++)
		{
			var offset = 5;
			var divTop = y + r * Math.cos(Math.PI * (i + offset) / (newWords.length + 2 * offset));
			var divRight = x + r * Math.sin(Math.PI * (i + offset) / (newWords.length + 2 * offset));
			
			var id_word = newWords[i].split(',');
			var id = id_word[0];
			var words = id_word[1];
			
			// creat the word in space
			var div = document.createElement('div');
			div.className = "word";
			div.id = newWords[i].split(',')[0];
			div.style.top = divTop + "px";
			div.style.right = divRight + "px";
			div.innerHTML = '<a onclick="fillWithWord(this.innerHTML, ' + divRight + ', ' + divTop + ', \'' + id + '\')" onContextMenu="fillWithWordGreen(this.innerHTML, ' + divRight + ', ' + divTop + ', \'' + id + '\')">' + words + "</a>";
			document.getElementsByTagName('body')[0].appendChild(div);
			
			expandeds.push(newWords[i]);
		}
	}
		
	expandeds.push(word);
	
	var color;
	if(parent.substring(0, 3) == 'syn' && !green)
		color = 'blue';
	else
		color = 'green';
	
	for(i=0; i<list.length; i++)
	{
		// connect them to parent
		jsPlumb.ready(function() {
            jsPlumb.connect({
                source:list[i].split(',')[0],
                target:parent,
                endpoint:[ "Rectangle", { width:2, height:2 } ],
				anchors:["Right", "Left"],
				paintStyle:{lineWidth:1, strokeStyle:color}
            });
        });
	}
}

function fillWithURL(url, word, x, y, id, green)
{
	$.ajax({
		url: url,
		type: 'GET',
		success: function(responce){
			fillTheTree(responce, word, x, y, id, green);
		},
		error: function(xhr, textStatus, errorThrown){
			alert('request failed\nxhr: ' + xhr.status + '\ntext status: ' + textStatus + '\nerror: ' + errorThrown);
		}
    });
}

function fillWithWord(word, x, y, id)
{	
	if(id == "null" && x == -1 && y == -1)
	{
		x = 0;
		y = h/2;
		
		var div = document.createElement('div');
		div.className = "word";
		div.id = word;
		div.style.top = y + "px";
		div.style.right = x + "px";
		div.innerHTML = word;
		document.getElementsByTagName('body')[0].appendChild(div);
		
		id = word;
		
		fillWithURL("tree.php?word=" + word, word, x, y, id, false);
	}
	else
	{
		fillWithURL("tree.php?word=" + id, word, x, y, id, false);
	}
	
}

function fillWithWordGreen(word, x, y, id)
{
	fillWithURL("tree.php?green=1&word=" + word.substring(1, word.length-2), word, x, y, id, true);
}