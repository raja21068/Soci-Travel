package com.netlync.sociotravel;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.netlync.sociotravel.handle.RequestHandler;

public class NewAccount extends Activity{

	Button buttonSubmit;
	EditText editTextFirstName , editTextLastName , editTextEmail , editTextContact , editTextAddress, editTextPassword;
	TextView textViewError;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_account);
		init();
		setOnClick();
	}
	
	private void init(){
		buttonSubmit = (Button)findViewById(R.id.buttonSubmitNewAccount);
		editTextFirstName = (EditText)findViewById(R.id.editTextFirstNameNewAccount);
		editTextLastName = (EditText)findViewById(R.id.editTextLastNameNewAccount);
		editTextEmail = (EditText)findViewById(R.id.editTextEmailNewAccount);
		editTextContact = (EditText)findViewById(R.id.editTextContactNumber);
		editTextAddress = (EditText)findViewById(R.id.editTextAddress);
		textViewError = (TextView)findViewById(R.id.textViewErrorVerify);
		editTextPassword = (EditText)findViewById(R.id.editTextPasswordNewAccount);
	}
	
	private void setOnClick(){
		buttonSubmit .setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String firstName = editTextFirstName.getText().toString();
				String lastName = editTextLastName.getText().toString();
				String contactNo = editTextContact.getText().toString();
				String email = editTextEmail.getText().toString().trim();
				String address = editTextAddress.getText().toString();
				String password = editTextPassword.getText().toString();
				
				if(firstName.equals("")){
					editTextFirstName.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(lastName.equals("")){
					editTextLastName.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(contactNo.equals("")){
					editTextContact.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(email.equals("") || email.contains(" ") ){
					editTextEmail.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(password.equals("")){
					editTextPassword.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				System.out.println("FirstName:"+firstName);
				System.out.println("LastName:"+lastName);
				System.out.println("Email:"+email);
				System.out.println("Password:"+password);
				System.out.println("ContactNo:"+contactNo);
				System.out.println("Arress:"+address);
				JSONObject ob = RequestHandler.createNewAccount(firstName, lastName, email,password,contactNo ,address);
				try {
					String status = ob.getString("status");
					String message = ob.getString("message");
					if(status.equals("OK")){
						Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
						finish();
					}
					else{
						textViewError.setText(message);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
	
}
