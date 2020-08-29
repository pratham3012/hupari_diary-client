<?php
 $dbhost = "shareddb-w.hosting.stackcp.net";
 $dbuser = "anfaas-48f2";
 $dbpass = "Anfaas@123";
 $db = "huparidatabase-3134395390";


 $conn = new mysqli($dbhost, $dbuser, $dbpass,$db) or die("Connect failed: %s\n". $conn -> error);


$sql = "SELECT * FROM `mcategory`";
$result = $conn->query($sql);
$outp = $result->fetch_all(MYSQLI_ASSOC);
echo json_encode($outp);
$conn->close();
?>