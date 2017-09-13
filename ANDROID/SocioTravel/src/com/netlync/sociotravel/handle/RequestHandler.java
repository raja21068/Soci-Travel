package com.netlync.sociotravel.handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class RequestHandler {
	
	private static final String url = "http://stbb.edu.pk/sociotravel/";
	private static final String login = "signin.php";
	private static final String city = "city.php";
	private static final String newAccount = "accountnew.php";
	private static final String verify = "verify.php";
	private static final String countries = "country.php";
	private static final String addShare = "addSharing.php";
	private static final String searchShare = "searchShare.php";
	
	private static List<NameValuePair> params;
	
	public static String getSignin(String email , String password)throws Exception{
		BufferedReader in = null;
		String data = null;
		try{
			params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("pass", password));
			
			HttpClient client = new DefaultHttpClient();
			System.out.println("Httpclient: "+client);
			
			URI uri = new URI(url+login+"?email="+email+"&pass="+password);
			System.out.println("URI: "+uri);
			
			HttpGet request = new HttpGet();
			System.out.println("request : "+request);
			request.setURI(uri);
			//request.setParams(params);
			
			HttpResponse response = client.execute(request);
			System.out.println("Response: "+response);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer buffer = new StringBuffer();
			String l = "";
			String nl = System.getProperty("line.separator");
			while((l = in.readLine()) != null){
				buffer.append(l+nl); //string nextline
			}
			data = buffer.toString();
			System.out.println(data);
		}finally{
			if(in != null)in.close();
		}
		
		return data;
	}
	
	public static JSONObject login(String email , String password)throws Exception{
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("pass", password));
		
		JSONObject ob = jsonParser.makeHttpRequest(url+login, "GET", params);
		return ob;
	}
	
	public static JSONObject getCities(String countryname){
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("country", countryname));
		JSONObject ob = jsonParser.makeHttpRequest(url+city, "GET", params);
		return ob;
	}
	
	public static JSONObject createNewAccount(String first, String last,String email ,String password,String contact ,String addres){
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("first", first));
		params.add(new BasicNameValuePair("last", last));
		params.add(new BasicNameValuePair("contact", contact));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("address", addres));		
		params.add(new BasicNameValuePair("password",password));
		JSONObject ob = jsonParser.makeHttpRequest(url+newAccount, "GET", params);
		return ob;
	}
	
	public static JSONObject verifyAccount(String code , String password){
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("password", password));
		JSONObject ob = jsonParser.makeHttpRequest(url+verify, "GET", params);
		return ob;	
	}
	
	
	public static JSONObject getContriesAndVehicle(){
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		JSONObject ob = jsonParser.makeHttpRequest(url+countries, "GET", params);
		return ob;
	}
	
	public static JSONObject addShare(String customerId ,int fromCity, int toCity, String date,String time, String vehicle, String feature){		
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("customerId",""+customerId));
		params.add(new BasicNameValuePair("cityIdFrom",""+fromCity));
		params.add(new BasicNameValuePair("cityIdTo",""+toCity));
		params.add(new BasicNameValuePair("goingDate",""+date));
		params.add(new BasicNameValuePair("arrivingtime",""+time));
		params.add(new BasicNameValuePair("vehicle",""+vehicle));
		params.add(new BasicNameValuePair("feature",""+feature));
		JSONObject ob = jsonParser.makeHttpRequest(url+addShare, "GET", params);
		return ob;
	}
	
	
	public static JSONObject searchShare(int cityId , String date){
		JSONParser jsonParser = new JSONParser();
		params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("cityId",""+cityId));
		params.add(new BasicNameValuePair("date",date));
		JSONObject ob = jsonParser.makeHttpRequest(url+searchShare, "GET", params);
		return ob;
	}
	
	public static JSONObject getShareDetail(int shareId){
		return null;
	}
}
