<?php

function OpenCon()
 {
 $dbhost = "shareddb-w.hosting.stackcp.net";
 $dbuser = "huparidatabase-3134395390";
 $dbpass = "Anfaas@123";
 $db = "huparidatabase-3134395390";


 $conn = new mysqli($dbhost, $dbuser, $dbpass,$db) or die("Connect failed: %s\n". $conn -> error);

 return $conn;
 }
 
function CloseCon($conn)
 {
 $conn -> close();
 }
   
?>