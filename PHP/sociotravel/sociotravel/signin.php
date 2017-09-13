<?php 
	$email ="";
	$password ="";
	if(isset($_REQUEST['email'])){
		$email = $_REQUEST['email'];
	}
	if(isset($_REQUEST['pass'])){
		$password = $_REQUEST['pass'];
	}
	include('dbcon.php');
	$email = strtoupper($email);
	$QUERY= "select * from customer where EMAIL='$email' and USER_PASSWORD='$password' and IS_ACTIVATED='1'";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	$user ="";
	$last ="";
	$customerId = "";
	
	if ($count > 0){
		$user = mysql_result($login_query,0,"FIRST_NAME");
		$last = mysql_result($login_query,0,"LAST_NAME");
		$customerId = mysql_result($login_query,0,"CUSTOMER_ID");
		$response["status"] = "OK";
        	$response["message"] = "";
        	$response["user"] = "$user $last";
        	$response["customerId"] = $customerId;
		
		/*$QUERY= "select * from country order by COUNTRY_NAME ASC";
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
		*/
	
		echo json_encode($response);
	}
	else{
		$response["status"] = "ERROR";
        $response["message"] = "INVALID EMAIL OR PASSWORD..";
 
		echo json_encode($response);
	}
	
?>