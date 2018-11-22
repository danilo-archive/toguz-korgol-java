<nav class="teal darken-3" id="top_bar">
    <div class="container">
        <div class="nav-wrapper">
            <a href="./" class="brand-logo">GameOn</a>
            <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            <ul id="nav" class="right hide-on-med-and-down">
                <?php
                if(isLoggedIn()){
                    echo "<li><a href=''>".$_SESSION["user"]->getEmail()."</a></li>";
                    echo "<li><a href='../Member_Functions/session_management.php?action=logout'>Log-out</a></li>";
                } else {
                    echo "<li><a href='login.php'>Login</a></li>";
                    echo "<li><a href='register.php'>Register</a></li>";
                }
                ?>
            </ul>
        </div>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
    <li><a href="sass.html">Sass</a></li>
    <li><a href="badges.html">Components</a></li>
    <li><a href="collapsible.html">Javascript</a></li>
    <li><a href="mobile.html">Mobile</a></li>
</ul>