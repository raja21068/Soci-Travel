package com.netlync.sociotravel;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netlync.sociotravel.handle.RequestHandler;

public class MainActivity extends Activity{
	
	Button buttonSignIn , buttonNewAccount , buttonVerifyAccount;
	EditText editTextEmail , editTextPassword;
	Intent intentNewAccount , intentVerifyAccount , intentProfile;
	TextView textViewError;
	ProgressBar progressBar;
	
	Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initVariables();
		setListenerToAllButton();
		setKeyListener();
	}
	
	//initialize all variables
	public void initVariables(){
		buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
		buttonNewAccount = (Button)findViewById(R.id.buttonNewAccount);
		buttonVerifyAccount = (Button)findViewById(R.id.buttonVerifyAccount);
		editTextEmail = (EditText)findViewById(R.id.editTextEmailSignin);
		editTextPassword = (EditText)findViewById(R.id.editTextPasswordSignin);
		textViewError = (TextView)findViewById(R.id.textViewErrorVerify);
		progressBar = (ProgressBar)findViewById(R.id.progressBarSignin);
		
		intentNewAccount = new Intent("com.netlync.sociotravel.NEWACCOUNT");
		intentVerifyAccount = new Intent("com.netlync.sociotravel.VERIFYACCOUNT");
		intentProfile = new Intent(MainActivity.this,UserActivity.class);
	}
	
	public void setListenerToAllButton(){
			buttonSignIn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					textViewError.setText("");
					new SigninTask().execute();
					
				}
			});
			
			buttonNewAccount.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					startActivity(intentNewAccount);
				}
			});
			
			buttonVerifyAccount.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					startActivity(intentVerifyAccount);
				}
			});
		
	}
	
	public void setKeyListener(){
		editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View view, boolean focused) {
				if(focused){
					view.setBackgroundColor(Color.parseColor("#FFFFFF"));
					textViewError.setText("");
				}
			}
		});
		editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View view, boolean focused) {
				if(focused){
					view.setBackgroundColor(Color.parseColor("#FFFFFF"));
				}
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 getMenuInflater().inflate(R.menu.main, menu);
		 return true;
	}
	
	private class SigninTask extends AsyncTask<Void, Void, String>{

		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(Void... params) {
			
			String email = editTextEmail.getText().toString().trim();
			String pass = editTextPassword.getText().toString().trim();
			if(email.equals("")){
				editTextEmail.setBackgroundColor(Color.parseColor("#FFCCCC"));
				return null;
			}
			if(pass.equals("")){
				editTextPassword.setBackgroundColor(Color.parseColor("#FFCCCC"));
				return null;
			}
			if(email.contains(" ")){
				textViewError.setText("Invalid email");
				return null;
			}
			bundle = new Bundle();
			
			String message="";
			String status = "";
			JSONObject ob = null;
			try{
				ob=RequestHandler.login(email, pass);
				message = ob.getString("message");
				status = ob.getString("status");
				
				if(status.equals("OK")){
					String user = ob.getString("user");
					int id = ob.getInt("customerId");
					
					bundle.putString("user", user);
					bundle.putString("customerId", ""+id);
					intentProfile.putExtras(bundle);
					System.out.println("Starting activity");
					startActivity(intentProfile);
					finish();
				}
				else { return message; }
			}catch(Exception ex){
				ex.printStackTrace();
			}
		
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			if(result!=null)textViewError.setText(""+result);
			super.onPostExecute(result);
		}
		
	}
	
}