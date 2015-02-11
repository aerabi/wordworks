function updateCload(word)
{
	if(word.length > 2)
	{
		document.getElementById('cload').src = "wordcload.php?word=" + word;
	}
}