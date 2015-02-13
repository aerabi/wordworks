function updateCload(word)
{
	if(word.length > 3)
	{
		document.getElementById('cload').src = "wordtree.php?word=" + word;
	}
}