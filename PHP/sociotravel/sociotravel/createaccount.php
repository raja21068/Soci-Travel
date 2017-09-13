<?php
	$firstName = "";
	$lastName ="";
	$email ="";
	$contactNo = "";
	$address = "";
	$password = "";
	
	if( isset($_REQUEST['first']) ){ $firstName = $_REQUEST['first']; }
	if( isset($_REQUEST['last']) ){ $lastName = $_REQUEST['last']; }
	if( isset($_REQUEST['email']) ){ $email =  $_REQUEST['email']; }
	if( isset($_REQUEST['contact']) ){ $contactNo=$_REQUEST['contact']; }
	if( isset($_REQUEST['address']) ){ $address = $_REQUEST['address']; }
	if( isset($_REQUEST['password']) ){ $password = $_REQUEST['password']; }
	
	include('dbcon.php');
	
	$email = strtoupper($email);
	
	$QUERY= "select * from customer where EMAIL='$email' AND IS_ACTIVATED='1'";
	$login_query=mysql_query($QUERY);
	$count=mysql_num_rows($login_query);
	
	$response = array();
	
	if($count > 0){
		$response["status"] = "ERROR";
		$response["message"] = "This email is already has account";
	}
	else{
	$code = rand(1000,9999);
		//$QUERY= "INSERT INTO customer (FIRST_NAME , LAST_NAME , EMAIL,USER_PASSWORD,CONTACT_NO,ADDRESS ,IS_ACTIVATED) values ('$firstName','$lastName','$email','$password','$contactNo','$address',  1 )";
		$QUERY= "INSERT INTO customer (FIRST_NAME , LAST_NAME , CONTACT_NO, ADDRESS, EMAIL, IS_ACTIVATED,CODE ) values ( '$firstName', '$lastName','$contactNo' ,'$address' ,'$email', 0, '$code' )";
		
		$login_query=mysql_query($QUERY);
		$response["status"] = "OK";
		$response["message"] = $QUERY;
		
		mail($email, "ACCOUNT CONFIRMATION","This is your SOCIO TRAVEL Account confirmation CODE $code  .your information $firstName - $lastName - $contactNo - $address.. IF YOU DID NOT REQUEST FOR CREATE ACCOUNT PLEASE IGNORE THIS", "From: SOCIO TRAVEL" );
	}
	
	echo json_encode($response);
?>