<?php
    function Redirect($url, $permanent = false)
    {
        header('Location: ' . $url, true, $permanent ? 301 : 302);
        exit();
    }
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Formulář</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<?php
$IsCompelete = false;

if (isset($_GET["odeslat"])) {
    if (isset($_GET["name"]) && $_GET["name"] != "" && isset($_GET["nick"]) && $_GET["nick"] != "" && isset($_GET["email"]) && $_GET["email"] != "" && isset($_GET["password"]) && $_GET["password"] != "" &&isset($_GET["password2"]) && $_GET["password2"] != "" &&isset($_GET["tel"]) && $_GET["tel"] != "" &&isset($_GET["soubor"]) && $_GET["soubor"] != "" &&isset($_GET["age"]) && $_GET["age"] != "" && $_GET["radio"] == "m" &&isset($_GET["checkbox"]) && $_GET["checkbox"] != "") {
        $IsCompelete = true;
    }

    if ($IsCompelete == false) {
        echo "Vypln vsechy pole";
    }
    if ($IsCompelete == true) {
        echo $_GET["name"];
        echo "<br>";
        echo $_GET["nick"];
        echo "<br>";
        echo $_GET["email"];
        echo "<br>";
        echo $_GET["password"];
        echo "<br>";
        echo $_GET["password2"];
        echo "<br>";
        echo $_GET["tel"];
        echo "<br>";
        echo $_GET["soubor"];
        echo "<br>";
        echo $_GET["age"];
        echo "<br>";
            
        

        echo "<br>";
        echo $_GET["age"];
        echo "<br>";
        echo $_GET["radio"];
        echo "<br>";
        echo $_GET["checkbox"];
        echo "<br>";

    }
}

if (!$IsCompelete) {
?>
<form action="<?php $_SERVER["PHP_SELF"]?>" method="get" enctype="multipart/form-data">
    <div class="form-control">
        <input class="input input-alt" placeholder="Jmeno" type="text" name="name">
        <span class="input-border input-border-alt"></span>
    </div>
    <div class="form-control">
        <input class="input input-alt" placeholder="Nick" type="text" name="nick">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Email" type="email" name="email">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Heslo" type="password" name="password">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Heslo znovu" type="password" name="password2">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Telefon" type="tel" name="tel">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Soubor" type="file" name="soubor">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="form-control">
        <input class="input input-alt" placeholder="Věk"  type="date" name="age">
        <span class="input-border input-border-alt"></span>
    </div>
    <br>
    <div class="mydict">
        <div>
            <label>
                <input type="radio" name="radio" checked="" value="m">
                <span>Můž</span>
            </label>
            <label>
                <input type="radio" name="radio" value="f">
                <span>Žena</span>
            </label>
            <label>
                <input type="radio" name="radio" value="n">
                <span>Něco</span>
            </label>

        </div>
    </div>
    <br>
    Spam?
    <label class="container">
        <input type="checkbox" checked="checked" name="checkbox">
        <div class="checkmark"></div>
    </label>
    <br>
    <br>

    <input type="submit" class="primary-button" name="odeslat">
</form>
<?php
}
?>
</body>
</html>

