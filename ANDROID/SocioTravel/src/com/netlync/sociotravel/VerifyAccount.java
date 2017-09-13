package com.netlync.sociotravel;

import com.netlync.sociotravel.handle.RequestHandler;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VerifyAccount extends Activity{

	EditText editTextCode, editTextPassword , editTextRetype;
	Button buttonVerify;
	TextView textViewError;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verify);
		init();
		setOnClick();
	}

	private void init(){
		editTextCode = (EditText)findViewById(R.id.editTextCode);
		editTextPassword = (EditText)findViewById(R.id.editTextPasswordVerify);
		editTextRetype = (EditText)findViewById(R.id.editTextRetyepPassword);
		buttonVerify = (Button)findViewById(R.id.buttonVerify);
		textViewError = (TextView)findViewById(R.id.textViewErrorVerify);
	}
	
	private void setOnClick(){
		buttonVerify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String code = editTextCode.getText().toString();
				String password = editTextPassword.getText().toString();
				String retype = editTextRetype.getText().toString();
				
				if(code.equals("")){
					editTextCode.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(password.equals("") || retype.equals("")){
					editTextPassword.setBackgroundColor(Color.parseColor("#FFCCCC"));
					editTextRetype.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				if(!password.equals(retype)){
					textViewError.setText("Password does not match");
					editTextRetype.setBackgroundColor(Color.parseColor("#FFCCCC"));
					return;
				}
				RequestHandler.verifyAccount(code, password);
			}
		});
	}
	
}
