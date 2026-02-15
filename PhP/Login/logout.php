<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    session_start();
    session_unset();
    session_destroy();

    // Clear the cookie as well if you want a full reset
    setcookie("onEnter", "", time() - 3600);

    Redirect("index.php");
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <p>You've logged out</p>
    <a href="index.php">back</a>
</body>
</html>