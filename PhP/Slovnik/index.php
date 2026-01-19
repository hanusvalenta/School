<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }

    require_once ("Db.php");
    Db::connect('127.0.0.1', 'test', 'root', '');
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
    if (isset($_POST["word"]) && $_POST["word"] != "") {
        $word = $_POST["word"];
        $row = Db::queryOne("SELECT * FROM slova WHERE czword = ? OR enword = ?", $word, $word);

        if ($row) 
        {
            echo (strcasecmp($row['czword'], $word) === 0) ? htmlspecialchars($row['enword']) : htmlspecialchars($row['czword']);
        } 
        else 
        {
            echo "Slovo nebylo nalezeno";
        }
    }
    ?>
</body>
</html>