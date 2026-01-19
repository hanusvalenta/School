<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    require_once ("Db.php");
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Formulář</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Slovnik</h1>
    <form action="<?php $_SERVER["PHP_SELF"]?>" method="post" enctype="multipart/form-data">
        <div class="form-control">
            <input class="input input-alt" placeholder="Zadej slovo" type="text" name="word">
            <span class="input-border input-border-alt"></span>
        </div>
    </form>
    <?php
    
    ?>
</body>
</html>