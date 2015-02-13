<?php
if(isset($_GET['word']))
{				
	$handle = fopen("dictionary.txt", "r");
	if ($handle)
	{
		while (($line = fgets($handle)) !== false)
		{
			if(strpos($line, $_GET['word']) !== false)
				foreach(explode(" ", $line) as $word)
					if(!is_numeric($word) && strlen($word)>1)
					{
						echo $word . "<br>";
						break;
					}
		}

		fclose($handle);
	}
}
else
{
	echo 'سلام';
}
?>