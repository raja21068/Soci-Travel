<?php
	$cityId = "";
	$date = "";
	
	include('dbcon.php');
	
	if(isset($_REQUEST['cityId'])) { $cityId = $_REQUEST['cityId']; }
	if(isset($_REQUEST['date'])) {  $date= $_REQUEST['date']; }
	
	$QUERY= "select * from sharing as s, customer c, city ct WHERE ct.CITY_ID=s.ARIVAL_CITY_ID AND s.DESTINATION_CITY_ID=$cityId AND s.ARIVAL_DATE='$date' AND c.CUSTOMER_ID=s.CUSTOMER_ID";
	
	if($date == ""){
		$QUERY= "select * from sharing as s, customer c, city ct WHERE ct.CITY_ID=s.ARIVAL_CITY_ID AND s.DESTINATION_CITY_ID=$cityId  AND c.CUSTOMER_ID=s.CUSTOMER_ID";
		//$QUERY= "select * from sharing as s, customer c, city ct";	
	}
	$login_query=mysql_query($QUERY);
	$count= mysql_num_rows($login_query);
	
	$share = array();
	$phone = array();
	$time = array();
	$ids = array();
	for($i=0; $i<$count;$i++){
		$share[$i] =  mysql_result($login_query,$i,"CITY_NAME");
		$phone[$i] =  mysql_result($login_query,$i,"CONTACT_NO");
		$time[$i] =   mysql_result($login_query,$i,"ARIVAL_TIME");
		$ids[$i] = mysql_result($login_query,$i,"SHARING_ID");
		
	}
	
	$response["names"] = $share;
	$response["phone"] = $phone;
	$response["time"] = $time;
	$response["ids"] = $ids;
	
	echo json_encode($response);
	
?>