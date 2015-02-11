<?php
if(isset($_GET['word']))
	$word = $_GET['word'];
else
	$word = "واژه";
?>

<html lang="en">

	<head>
		<script src="scripts/jquery-2.1.0.min.js"></script>

		<meta charset="utf-8">
		<meta name="description" content="Word Cloud">
		<title>Word Cloud</title>
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<LINK href="images/SquareAngellandros.ico" rel="SHORTCUT ICON">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	</head>

	<body>
	
		<script src="scripts/wordcload.js"></script>
		<script lang="javascript">
			fillWithURL("synonym.php?word=<?php echo $word ?>");
		</script>
		
	</body>

</html>