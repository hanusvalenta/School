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
<?php
define("ROOT_DIR", dirname(__FILE__));

Db::connect("localhost", "PHP", "root", "");

function wc_upload_image_return_url($image_submit) {
    if (empty($image_submit) || $image_submit['error'] != 0) {
        return "Nic nenahrano";
    }

    $fileType = $image_submit["type"];
    $fileSize = $image_submit["size"];

    if ($fileSize / 1024 > 2048) {
        return "File size must be less than 2MB.";
    }

    $allowedTypes = [
            "image/png", "image/gif", "image/jpg", "image/jpeg",
            "application/pdf", "application/zip",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    ];

    if (!in_array($fileType, $allowedTypes)) {
        return "Spatny typ souboru";
    }

    $filename = date("Y_m_d_H_i_s") . $image_submit["name"];
    $uploadPath = ROOT_DIR . "/uploads/" . $filename;

    if (is_uploaded_file($image_submit["tmp_name"])) {
        if (!move_uploaded_file($image_submit["tmp_name"], $uploadPath)) {
            return "Error moving file.";
        }
        return "uploads/" . $filename;
    } else {
        return "Possible file upload attack.";
    }
}

$IsCompelete = false;

if (isset($_POST["odeslat"])) {
    if (isset($_POST["name"]) && $_POST["name"] != "" && $_POST["name"] != null && $_POST["name"] != "") {
        $IsCompelete = true;
    }

    if ($IsCompelete == false) {
        echo "Vypln vsechy pole";
    }
    if ($IsCompelete == true) {
        echo $_POST["name"];
        echo "<br>";
        echo $_POST["nick"];
        echo "<br>";
        echo $_POST["email"];
        echo "<br>";
        echo $_POST["password"];
        echo "<br>";
        echo $_POST["password2"];
        echo "<br>";
        echo $_POST["tel"];
        echo "<br>";
        echo $_POST["age"];
        echo "<br>";

        $uploaded_file_url = wc_upload_image_return_url($_FILES["soubor"]);

        echo "<img src='" . $uploaded_file_url . "' alt='Soubor' style='width: 200px; height: 200px; padding: 10px;'>";

        echo "<br>";
        echo $_POST["age"];
        echo "<br>";
        echo $_POST["radio"];
        echo "<br>";
        if ($_POST["checkbox"] == "on") {
            echo "checkbox true";
        }
        else {
            echo "checkbox false";
        }
        echo "<br>";

        Db::insert('lidi', [
            'jmeno' => $_POST['name'],
            'prijmeni' => $_POST['nick']
        ]);
    }
}

if (!$IsCompelete) {
?>
<form action="<?php $_SERVER["PHP_SELF"]?>" method="post" enctype="multipart/form-data">
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