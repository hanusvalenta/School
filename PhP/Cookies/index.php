<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    session_start();

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
            echo $_COOKIE["onEnter"];
            setcookie("onEnter", $_COOKIE["onEnter"] + 1, time() + 5);
        }
        else {
            echo "no nookie :c";
            setcookie("onEnter", 1, time() + 5);
        }

        echo "<br>";

        if (isset($_SESSION["id"])){
            echo "Your session id is: ";
            echo $_SESSION["id"];
        }
        else {
            echo "no starfish :c";
        }
    ?>
    <br>
    <a href="login.php">Login</a>
    <br>
    <a href="logout.php">Logout</a>
    <br>
    <img src="nookie.jpg">
</body>
</html>