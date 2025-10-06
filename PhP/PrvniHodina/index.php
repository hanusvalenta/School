<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Formulář</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="formHead">
        <h1>Formulář</h1>
        <img src="Form.jpg" style="scale: 0.1">
    </div>

    <form action="index.php" method="get">
        <div class="formbox">
            Jmeno/Nick:   <input type="text" name="jmeno">
        </div>
        <br>
        <div class="formbox">
            Email:   <input type="email" name="email">
        </div>
        <br>
        <div class="formbox">
            Heslo:   <input type="password" name="heslo">
        </div>
        <br>
        <div class="formbox">
            Heslo znovu:   <input type="password" name="heslo2">
        </div>
        <br>
        <div class="formbox">
            Telefon:   <input type="tel" name="tel">
        </div>
        <br>
        <div class="formbox">
            Fotka:   <input type="file" accept="image/*">
        </div>
        <br>
        <div class="formbox">
            Vek:   <input type="date" name="Vek">
        </div>
        <br>
        <div class="formbox">
            Pohlavi:   <select name="pohlavi">
                <option>Muz</option>
                <option>Zena</option>
                <option>Bojova helikoptera</option>
            </select>
        </div>
        <br>
        <div class="formbox">
            <input type="checkbox" name="spam" checked> Chci dostavat zip bomby do emailu
        </div>
        <br>
        <br>

        <input type="submit" name="odeslat" value="Odeslat">
    </form>

    <?php
    if(isset($_GET['submit'])){

    }
    ?>
</body>
</html>

