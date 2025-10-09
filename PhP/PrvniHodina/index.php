<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Formulář</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="index.php" method="get" class="form">
        <div class="form-control">
            <input class="input input-alt" placeholder="Jmeno" required="" type="text" name="name">
            <span class="input-border input-border-alt"></span>
        </div>
        <div class="form-control">
            <input class="input input-alt" placeholder="Nick" required="" type="text" name="name">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Email" required="" type="email" name="email">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Heslo" required="" type="password" name="password">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Heslo znovu" required="" type="password" name="password">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Telefon" required="" type="tel" name="password">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Soubor" required="" type="file" name="password">
            <span class="input-border input-border-alt"></span>
        </div>
        <br>
        <div class="form-control">
            <input class="input input-alt" placeholder="Věk" required="" type="date" name="password">
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

        <input type="submit" class="primary-button" name="odeslat" value="Odeslat">
    </form>

    <?php
    if(isset($_GET['submit'])){
        
    }
    ?>
</body>
</html>

