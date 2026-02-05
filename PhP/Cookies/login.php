<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    session_start();
    $_SESSION["id"] = 123456;

    //require_once ("Db.php");
    //Db::connect('127.0.0.1', 'test', 'root', '');
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Nookie</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <p>You've logged in</p>
    <a href="index.php">back</a>
</body>
</html>