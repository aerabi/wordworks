<?php
if(isset($_GET['word']))
	$word = $_GET['word'];
else
	$word = "واژه";
?>

<html lang="en">

	<head>
		<script src="scripts/jquery-2.1.0.min.js"></script>
		<script src="scripts/jquery-ui.js"></script>
		<script src="scripts/jquery.jsPlumb-1.4.1-all-min.js"></script>
		<script src="scripts/jquery-mousewheel.js"></script>

		<meta charset="utf-8">
		<meta name="description" content="Word Tree">
		<title>Word Tree</title>
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<LINK href="images/SquareAngellandros.ico" rel="SHORTCUT ICON">
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	</head>

	<body oncontextmenu="return false;">
	
		<script src="scripts/wordtree.js"></script>
		<script src="scripts/zoom.js"></script>
		<script lang="javascript">
			fillWithWord("<?php echo $word ?>", -1, -1, "null");
		</script>
		
	</body>

</html>