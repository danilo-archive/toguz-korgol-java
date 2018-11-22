<?php
// Copyright Dominic Swaine 2018-present.  https://www.dominicswaine.com

define("api_basepath", "https://lab-project.dominicswaine.com/apis/");

function api_call($relative_path, $post_data){
    $options = array(
        'http' => array(
            'header'  => "Content-type: application/x-www-form-urlencoded\r\n",
            'method'  => 'POST',
            'content' => http_build_query($post_data)
        )
    );
    $context  = stream_context_create($options);
    $response = file_get_contents(api_basepath . $relative_path, false, $context);
    //echo "Response: " . $response;
    return json_decode($response,true);
}