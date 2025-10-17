<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Formulář</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<?php
if ($_GET["odeslat"]) {
    if ($_FILES["file"]) {
        copy($_FILES["file"]["tmp_name"], "files/" . $_FILES["file"]["name"]);
        echo "nahran";
    }
}

if (!$_GET) {
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
        <input class="input input-alt" placeholder="Soubor" type="file" name="file">
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
                <input type="radio" name="radio" checked="">
                <span>Můž</span>
            </label>
            <label>
                <input type="radio" name="radio">
                <span>Žena</span>
            </label>
            <label>
                <input type="radio" name="radio">
                <span>Něco</span>
            </label>

        </div>
    </div>
    <br>
    Spam?
    <label class="container">
        <input type="checkbox" checked="checked">
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

