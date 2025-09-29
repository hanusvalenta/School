<!DOCTYPE html>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <h1>Test</h1>
</body>
</html>

<?php
$text = 'nejaki debil mi odrel auto';

$newtxt = str_replace("debil", '😁' , $text);
echo str_replace("odrel", '😭' , $newtxt);
?>