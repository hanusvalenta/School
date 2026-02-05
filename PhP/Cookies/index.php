<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

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
    <?php
        if (isset($_COOKIE["onEnter"])){
            setcookie("onEnter", $_COOKIE["onEnter"] + 1, time() + 5);
            echo $_COOKIE["onEnter"];
        }
        else {
            echo "no nookie";
            setcookie("onEnter", 1, time() + 5);
        }
    ?>
    <br>
    <img src="nookie.jpg">
</body>
</html>