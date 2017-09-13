<?php

	$shareId = "";
	
	if(isset($_REQUEST['shareId'])) { $shareId = $_REQUEST['shareId']; }
	
	include('dbcon.php');
	
	$QUERY= "select * FROM sharing s , city c, customer co ,vehicle v WHERE s.VEHICLE_ID = v.VEHICLE_ID and c.CITY_ID=s.ARIVAL_CITY_ID AND s.CUSTOMER_ID=co.CUSTOMER_ID AND s.SHARING_ID=$shareId";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	
	if($count>0){
		$cityName = mysql_result($login_query,$i,"CITY_NAME");
		$userName = mysql_result($login_query,$i,"FIRST_NAME");
		$no = 	mysql_result($login_query,$i,"CONTACT_NO");
		$feature = mysql_result($login_query,$i,"FEATURE");
		$veh = mysql_result($login_query,$i,"VEHICLE_NAME");
		$dat = mysql_result($login_query,$i,"ARIVAL_DATE");
		
		
		$response["status"]="OK";
		$response["city"] = $cityName;
		$response["user"] = $userName;
		$response["contact"] = $no;
		$response["feature"] = $feature;
		$response["query"] = $QUERY;
		$response["vehicle"] = $veh;
		$response["dat"] = $dat;
		
		echo json_encode($response);
	}else{
		$response["status"]="ERROR";
		$response["query"] = $QUERY;
		$response["message"]="No record..";
		echo json_encode($response);
	}
	
?>