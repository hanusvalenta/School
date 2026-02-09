<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    session_start();

    require_once ("Db.php");
    Db::connect('127.0.0.1', 'Users', 'root', '');
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Ahoj</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <?php
        if (isset($_COOKIE["onEnter"])){
            echo "Cookie value: " . htmlspecialchars($_COOKIE["onEnter"]);
            setcookie("onEnter", $_COOKIE["onEnter"] + 1, time() + 3600);
        }
        else {
            echo "no nookie :c";
            setcookie("onEnter", 1, time() + 3600);
        }

        echo "<br>";

        if (isset($_SESSION["id"])){
            echo "Your session id is: " . htmlspecialchars($_SESSION["id"]);
            if (isset($_SESSION["username"])) {
                echo " | Logged in as: " . htmlspecialchars($_SESSION["username"]);
            }
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
</body>
</html>