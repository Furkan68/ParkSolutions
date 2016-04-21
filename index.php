<?php
$servername = "furkancetin.nl:3306";
$username = "username";
$password = "password";
$dbname = "sensor";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}


$data = array("data" => array(array("distance" => "", "available" => false)));

$sql = "SELECT distance FROM data WHERE id=1";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
		$dis = (float) $row['distance'];
		
        $data['data'][0]['distance'] = $dis;
		if($dis <= 30){
			$data['data'][0]['available'] = false;
		}else{
			$data['data'][0]['available'] = true;
		}
    }
} else {
    $data['data'][0]['distance'] = -1;
}

mysqli_close($conn);

header('Content-Type: application/json');
//header('Refresh: 1');

echo json_encode($data);
?>