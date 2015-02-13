var h = window.innerHeight - 30;
var w = window.innerWidth - 60;

function fillTheTree(str, word, x, y, parent)
{
	var list = str.substring(0, str.length-4).split("<br>");
	
	// finding radius
	var r = Math.min(h - y, Math.min(w - x, y));
	
	for(i=0; i<list.length; i++)
	{
		var offset = 5;
		var divTop = y + r * Math.cos(Math.PI * (i + offset) / (list.length + 2 * offset -1));
		var divRight = x + r * Math.sin(Math.PI * (i + offset) / (list.length + 2 * offset -1));
		
		// creat the word in space
		var div = document.createElement('div');
		div.className = "word";
		div.id = word + i;
		div.style.top = divTop + "px";
		div.style.right = divRight + "px";
		div.innerHTML = '<a onclick="fillWithWord(this.innerHTML, ' + divRight + ', ' + divTop + ', \'' + (word + i) + '\')">' + list[i] + "</a>";
		document.getElementsByTagName('body')[0].appendChild(div);
	}
	
	for(i=0; i<list.length; i++)
	{
		// connect them to parent
		jsPlumb.ready(function() {
            jsPlumb.connect({
                source:word+i,
                target:parent,
                endpoint:[ "Rectangle", { width:2, height:2 } ],
				anchors:["Right", "Left"],
				paintStyle:{lineWidth:2, strokeStyle:'rgb(243,18,18)'}
            });
        });
	}
}

function fillWithURL(url, word, x, y, id)
{
	$.ajax({
		url: url,
		type: 'GET',
		success: function(responce){
			fillTheTree(responce, word, x, y, id);
		},
		error: function(xhr, textStatus, errorThrown){
			alert('request failed\nxhr: ' + xhr.status + '\ntext status: ' + textStatus + '\nerror: ' + errorThrown);
		}
    });
}

function fillWithWord(word, x, y, id)
{
	if(x == -1) 
		x = 0;
	if(y == -1)
		y = h/2;
	
	if(id == "null")
	{
		var div = document.createElement('div');
		div.className = "word";
		div.id = word;
		div.style.top = y + "px";
		div.style.right = x + "px";
		div.innerHTML = word;
		document.getElementsByTagName('body')[0].appendChild(div);
		
		id = word;
	}
	
	//$(".word").remove();
	fillWithURL("synonym.php?word=" + word, word, x, y, id)
}