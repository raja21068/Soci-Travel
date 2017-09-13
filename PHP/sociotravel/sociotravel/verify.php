<?php
	$code = "";
	$password = "";
	$email = "";
	
	if(isset($_REQUEST['code'])){ $code = $_REQUEST['code']; }
	if(isset($_REQUEST['password'])){ $password = $_REQUEST['password']; }
	if(isset($_REQUEST['email'])){ $email = $_REQUEST['email']; }
	
	$email = strtoupper($email);
	
	 include('dbcon.php');
	 $QUERY= "SELECT * FROM customer WHERE EMAIL='$email'";
	 $login_query=mysql_query($QUERY);
	 $count=mysql_num_rows($login_query);
	
	$response = array();
	
	if($count > 0){
		$query = "SELECT * FROM customer WHERE EMAIL='$email' AND CODE='$code'";
		$executeQuery = mysql_query($query);
		$rows=mysql_num_rows($executeQuery);
		if($rows>0){
			 $firstName = mysql_result($executeQuery,0,"FIRST_NAME");
			 $lastName = mysql_result($executeQuery,0,"LAST_NAME");
			 $contactNo = mysql_result($executeQuery,0,"CONTACT_NO");
			 $address = mysql_result($executeQuery,0,"ADDRESS");
			 
			 $query = "DELETE FROM customer WHERE EMAIL='$email'";
			 $executeQuery = mysql_query($query);
			 
			 $query = "INSERT INTO customer (FIRST_NAME , LAST_NAME , CONTACT_NO, ADDRESS, EMAIL,USER_PASSWORD, IS_ACTIVATED ) values ( '$firstName', '$lastName','$contactNo' ,'$address' ,'$email','$password' , 1 )";
			 $executeQuery = mysql_query($query);		
			 
			$QUERY= "select * from customer where EMAIL='$email' and USER_PASSWORD='$password' and IS_ACTIVATED='1'";
			$login_query=mysql_query($QUERY);
			$count=mysql_num_rows($login_query);
	
			$user = mysql_result($login_query,0,"FIRST_NAME");
			$last = mysql_result($login_query,0,"LAST_NAME");
			$customerId = mysql_result($login_query,0,"CUSTOMER_ID");
			$response["status"] = "OK";
        		$response["message"] = "";
        		$response["user"] = "$user $last";
        		$response["customerId"] = $customerId;
		 	
		
		}else{
			$response["status"] = "ERROR";
			$response["message"] = "Invalid Code.. Please confirm code through sent Email";	
			$response["query"] = $QUERY;
		}
	
	}else{
		$response["status"] = "ERROR";
		$response["message"] = "This Email is not recognized, Please create new account";
		$response["query"] = $QUERY;
	}
	
	echo json_encode($response);

?>