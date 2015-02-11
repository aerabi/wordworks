var h = window.innerHeight - 30;
var w = window.innerWidth - 60;


function fillTheCload(str)
{
	var list = str.split("<br>");
	
	for(i=0; i<list.length; i++)
	{
		var divTop = Math.floor(Math.random() * h);
		var divRight = Math.floor(Math.random() * w);
		
		// creat the word in space
		var div = document.createElement('div');
		div.className = "word";
		div.style.top = divTop + "px";
		div.style.right = divRight + "px";
		div.innerHTML = '<a onclick="fillWithWord(this.innerHTML)">' + list[i] + "</a>";
		document.getElementsByTagName('body')[0].appendChild(div);
	}
}

function fillWithURL(url)
{
	$.ajax({
		url: url,
		type: 'GET',
		success: function(responce){
			fillTheCload(responce);
		},
		error: function(xhr, textStatus, errorThrown){
			alert('request failed\nxhr: ' + xhr.status + '\ntext status: ' + textStatus + '\nerror: ' + errorThrown);
		}
    });
}

function fillWithWord(word)
{
	$(".word").remove();
	fillWithURL("synonym.php?word=" + word)
}