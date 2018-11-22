<?php
// Copyright Dominic Swaine 2018-present.
// https://www.dominicswaine.com

require_once("../Member_Functions/api_connect.php");
require_once("../Models/Game.php");

$response = api_call("games/list",array());
if(array_key_exists("data",$response)){
    $games = array_map(function($r){return new Game(...array_values($r));},$response["data"]);
    foreach ($games as $game){

        echo /** @lang html */
        '
            <div class="col s3">
                <div class="card">
                    <div class="card-image waves-effect waves-block waves-light" style="background-color:#ECEFF1;">
                        <img class="activator" src="'.$game->getImageUrl().'" style="width:238px;height:148px;object-fit:contain;">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator black-text">
                            '.substr($game->getName(), 0, 12).'
                            <i class="material-icons right">more_vert</i>
                        </span>
                    </div>
                    <div class="card-reveal">
                        <span class="card-title grey-text text-lighten-1">
                            '.substr($game->getName(), 0, 25).'
                            <i class="material-icons right">close</i>
                        </span>
                        <p><a href="#">BooK</a></p>
                    </div>
                </div>
            </div>
        ';

    }
}