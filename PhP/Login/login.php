<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    session_start();
    require_once ("Db.php");
    Db::connect('127.0.0.1', 'Users', 'admin', '');

    $message = "";

    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["username"]) && isset($_POST["password"])) {
        $username = $_POST["username"];
        $password = $_POST["password"];

        // Querying based on the columns in your image: id, username, password
        $user = Db::queryOne("SELECT * FROM users WHERE username = ? AND password = ?", $username, $password);

        if ($user) {
            $_SESSION["id"] = $user['id'];
            $_SESSION["username"] = $user['username'];
            Redirect("index.php");
        } else {
            $message = "Invalid username or password.";
        }
    }
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <?php if ($message) echo "<p style='color:red;'>$message</p>"; ?>
    
    <form action="login.php" method="post">
        <div class="form-control">
            <input class="input input-alt" placeholder="Username: " type="text" name="username" required>
            <input class="input input-alt" placeholder="Password: " type="password" name="password" required>
            <button type="submit">Login</button>
            <span class="input-border input-border-alt"></span>
        </div>
    </form>

    <p><a href="index.php">back</a></p>
</body>
</html>