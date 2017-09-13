package com.netlync.sociotravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity{

	TextView textViewUser;
	Button buttonAdd , buttonSearch;
	Bundle bundle;
	Intent searchActivity, addActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avtivity_user);
		init();
		setOnClick();
		
		bundle = getIntent().getExtras();
		String user = bundle.getString("user");
		textViewUser.setText(user);
		
		
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Toast.makeText(getApplicationContext(), "OnPause()", Toast.LENGTH_SHORT).show();
	}



	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Toast.makeText(getApplicationContext(), "OnRestart()", Toast.LENGTH_SHORT).show();
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Toast.makeText(getApplicationContext(), "OnResume()", Toast.LENGTH_SHORT).show();
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(getApplicationContext(), "OnStart()", Toast.LENGTH_SHORT).show();
	}



	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}



	public void init(){
		textViewUser = (TextView)findViewById(R.id.textViewUser);
		buttonAdd = (Button)findViewById(R.id.buttonShare);
		buttonSearch = (Button)findViewById(R.id.buttonSearchShare);
	
		searchActivity = new Intent("com.netlync.sociotravel.SEARCHACTIVITY");
		addActivity = new Intent("com.netlync.sociotravel.ADDSHAREACTIVITY");
	}
	
	public void setOnClick(){
		buttonAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(){
					public void run(){
						addActivity.putExtras(bundle);
						startActivity(addActivity);
					}
				}.start();
			}
		});
		
		buttonSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(){
					public void run(){
						searchActivity.putExtras(bundle);
						startActivity(searchActivity);
					}
				}.start();
			}
		});
	}
}
