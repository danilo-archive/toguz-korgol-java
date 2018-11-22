<?php require_once("../Member_Functions/user_session.php"); ?>
<!DOCTYPE html>
<html>

<head>
    <title>Game Rental</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>

<body style="background-color:#FAFAFA;">
    <?php include("components/navigator.php");include("components/search.html");?>

    <main class="container">
        <h3>Our games</h3>
        <div class="row">
            <?php include("components/game_tiles.php"); ?>
        </div>
    </main>

    <?php include("components/footer.html"); ?>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>