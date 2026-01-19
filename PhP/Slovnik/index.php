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
    if (isset($_POST["word"]) && $_POST["word"] != "") {
        $env = [];
        if (file_exists(__DIR__ . '/.env')) {
            $lines = file(__DIR__ . '/.env', FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
            foreach ($lines as $line) {
                if (strpos(trim($line), '#') === 0) continue;
                $parts = explode('=', $line, 2);
                if (count($parts) === 2) $env[trim($parts[0])] = trim($parts[1]);
            }
        }
        $apiKey = isset($env['KEY']) ? $env['KEY'] : '';
        $wordEncoded = urlencode($_POST["word"]);
        $detectUrl = "https://translation.googleapis.com/language/translate/v2/detect?key=" . $apiKey . "&q=" . $wordEncoded;

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $detectUrl);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $detectResponse = curl_exec($ch);
        $detectData = json_decode($detectResponse, true);

        $detectedLang = isset($detectData['data']['detections'][0][0]['language']) ? $detectData['data']['detections'][0][0]['language'] : 'en';
        $targetLang = ($detectedLang == 'cs') ? 'en' : 'cs';

        $url = "https://translation.googleapis.com/language/translate/v2?key=" . $apiKey . "&q=" . $wordEncoded . "&target=" . $targetLang;
        curl_setopt($ch, CURLOPT_URL, $url);
        $response = curl_exec($ch);
        curl_close($ch);

        $data = json_decode($response, true);
        if (isset($data['data']['translations'][0]['translatedText'])) {
            echo "<h2>" . $data['data']['translations'][0]['translatedText'] . "</h2>";
        }
    }
    ?>
</body>
</html>