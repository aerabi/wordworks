<?php
if(isset($_GET['green']))
	$green = true;
else
	$green = false;

function showEm($word)
{
	$handle = fopen("tree.txt", "r");
	if ($handle)
	{
		while (($line = fgets($handle)) !== false)
		{
			$parent_child = explode("\t", $line);
			$parent = $parent_child[0];
			
			if(strpos($parent, $word) !== false)
			{
				$childs = explode(", ", $parent_child[1]);
				
				foreach($childs as $child)
				{
					
					echo $child . "<br>";
					break;
				}
			}
		}

		fclose($handle);
	}
}

if(isset($_GET['word']))
{				
	if($green)
	{
		foreach(explode('،', $_GET['word']) as $word)
		{
			showEm($word);
		}
	}
	else
	{
		showEm($_GET['word']);
	}
}
else
{
	echo '????';
}
?>