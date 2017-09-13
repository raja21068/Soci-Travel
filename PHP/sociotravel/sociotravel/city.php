<?php
	$country ="";
	if(isset($_REQUEST['country'])){
		$country = $_REQUEST['country'];
	}
	include('dbcon.php');
	$QUERY= "select * from country where COUNTRY_NAME ='$country'";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	$id = 0;
	
	if($count >0){
		$id = mysql_result($login_query,0,"COUNTRY_ID");
	}
	
	$QUERY= "select * from city WHERE COUNTRY_ID='$id' ORDER BY CITY_NAME ASC";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	$cities = array();
	$citiesId = array();
	
	for($i=0;$i<$count;$i++){
		$citiesId[$i] = mysql_result($login_query,$i,"CITY_ID");
		$cities[$i] = mysql_result($login_query,$i,"CITY_NAME");
	}
	
	$response["city"] = $cities;
	$response["cityId"] = $citiesId;
	
	echo json_encode($response);
	
?>