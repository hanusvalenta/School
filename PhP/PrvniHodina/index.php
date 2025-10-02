<!DOCTYPE html>
<html>
<head>
    <title>Test</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Test</h1>

    <form action="index.php" method="get">
        <input type="text" name="text">
        <input type="password" name="heslo">

        <br>
        <input type="checkbox" name="check" value="check" checked>

        <input type="radio" name="radio" value="on" checked>
        <input type="radio" name="radio" value="off">

        <input type="file" name="file">

        <select name="select" size="5" multiple>
            <option value="lol">lol</option>
            <option value="nah">lol</option>
        </select>

        <input type="submit" name="submit">
    </form>

    <?php
    if(isset($_GET['submit'])){
        $radio = $_GET['radio'];
        if ($radio == 'on') {
            echo "😀";
        }
        else if ($radio == 'off') {
            echo "🤨";
        }
    }
    ?>
</body>
</html>

