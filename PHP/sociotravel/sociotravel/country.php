<?php

	include('dbcon.php');
	$QUERY= "select * from country order by COUNTRY_NAME ASC";
	$login_query=mysql_query($QUERY);
	$count= mysql_num_rows($login_query);
	$country = array();
	for($i=0; $i<$count;$i++){
		$country[$i] =  mysql_result($login_query,$i,"COUNTRY_NAME");
	}
	$response["country"] = $country;
	
	$QUERY= "select * from vehicle order by VEHICLE_NAME ASC";
	$login_query=mysql_query($QUERY);
	$count= mysql_num_rows($login_query);
	$vehicle = array();
	for($i=0; $i<$count;$i++){
		$vehicle[$i] =  mysql_result($login_query,$i,"VEHICLE_NAME");
		
	}
	$response["vehicle"] = $vehicle;
	echo json_encode($response);
?>