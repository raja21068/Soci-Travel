<?php
	
	$time = "";
	$cityIdTo = "";
	$cityIdFrom = "";
	$goingDate = ""; 
	$vehicle ="";
	$customerId = "";
	$feature ="";
	
	if(isset($_REQUEST['arrivingtime'])) { $time = $_REQUEST['arrivingtime']; }
	if(isset($_REQUEST['cityIdTo'])) { $cityIdTo = $_REQUEST['cityIdTo']; }
	if(isset($_REQUEST['cityIdFrom'])) { $cityIdFrom = $_REQUEST['cityIdFrom']; }
	if(isset($_REQUEST['goingDate'])) { $goingDate = $_REQUEST['goingDate']; }
	if(isset($_REQUEST['vehicle'])) { $vehicle = $_REQUEST['vehicle']; }
	if(isset($_REQUEST['customerId'])) { $customerId = $_REQUEST['customerId']; }
	if(isset($_REQUEST['feature'])) { $feature = $_REQUEST['feature']; }
	
	include('dbcon.php');
	
	$QUERY= "SELECT VEHICLE_ID FROM vehicle WHERE VEHICLE_NAME='$vehicle'";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	$vehicleId = 0;
	
	if($count > 0){
		$vehicleId =  mysql_result($login_query , 0, "VEHICLE_ID");
	}
	
	$QUERY= "INSERT INTO sharing ( VEHICLE_ID, CUSTOMER_ID, ARIVAL_CITY_ID, DESTINATION_CITY_ID, ARIVAL_DATE,ARIVAL_TIME,FEATURE ) values 	($vehicleId,$customerId, $cityIdTo,$cityIdFrom, '$goingDate', '$time' ,'$feature' )";
	$login_query=mysql_query($QUERY);
	//$count=mysql_num_rows($login_query);
	if($login_query){
		$response["status"] = "OK";
		$response["message"]="Your Travel Successfully Shared..";
		$response["query"] = $QUERY;
	}
	else{
		$response["status"] = "ERROR";
		$response["message"]="Your Travel Is not Added..";
		$response["query"] = $QUERY;
	}
	echo json_encode($response);
	
?>