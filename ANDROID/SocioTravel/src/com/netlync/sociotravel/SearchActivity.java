package com.netlync.sociotravel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.netlync.sociotravel.bean.CityBean;
import com.netlync.sociotravel.handle.RequestHandler;

public class SearchActivity extends Activity{
	
	Bundle bundle;
	Button buttonSearch , buttonDateSelect;
	DatePicker datepicker;
	Spinner spinnerCountry , spinnerCity;
	TextView response , textViewSelectedDate;
	Intent intentSearchList; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		init();
		
		bundle = getIntent().getExtras();
		
		JSONObject ob = RequestHandler.getContriesAndVehicle();
		try{
			JSONArray arr = ob.getJSONArray("country");
			ArrayList<String> countries = new ArrayList<String>();
			for(int i=0;i<arr.length();i++){
				countries.add((String)arr.get(i));
			}
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, countries);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerCountry.setAdapter(dataAdapter);
						
			setCities(countries.get(0));
			
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "Couldn't get countries and vehicle", Toast.LENGTH_LONG).show();
			
		}
		
		String user = bundle.getString("user");
		
		response.setText(user);
		setSpinnerChange();
		setOnClick();
	}
	
	public void init(){
		response = (TextView)findViewById(R.id.textViewUser);
		spinnerCountry = (Spinner)findViewById(R.id.spinnerCountry);
		spinnerCity = (Spinner)findViewById(R.id.spinnerCity);
		datepicker = (DatePicker)findViewById(R.id.datePicker);
		buttonSearch = (Button)findViewById(R.id.buttonSearch);
		buttonDateSelect = (Button) findViewById(R.id.buttonDateSelect);
		textViewSelectedDate = (TextView)findViewById(R.id.textViewDateShow);
		intentSearchList = new Intent("com.netlync.sociotravel.SEARCHVIEWACTIVITY");
	}
	
	public void setSpinnerChange(){
		spinnerCountry.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int arg2, long arg3) {
				//Toast.makeText(parent.getContext(), ""+spinnerCountry.getSelectedItem(), Toast.LENGTH_LONG).show();
				setCities((String)spinnerCountry.getSelectedItem());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				System.out.println("OnNothing...");
			}
		
		});
	}
	
	public void setCities(String countryName){
		
		JSONObject ob = RequestHandler.getCities(countryName);
		System.out.println(">>> "+ob);
		try{
			JSONArray arr = ob.getJSONArray("city");
			JSONArray cityId = ob.getJSONArray("cityId");
			
			ArrayList<CityBean> cities = new ArrayList<CityBean>();
			
			for(int i=0;i<arr.length();i++){
				cities.add(new CityBean(cityId.getInt(i),arr.getString(i)));
			}
			
			ArrayAdapter<CityBean> dataAdapter = new ArrayAdapter<CityBean>(this,
					android.R.layout.simple_spinner_item, cities);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerCity.setAdapter(dataAdapter);	
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "Exception occured at setCity>SigninPro", Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}

	public void setOnClick(){
		// Button Date Select
		buttonDateSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if( datepicker.getVisibility() == View.VISIBLE){
					datepicker.setVisibility(View.GONE);
					buttonDateSelect.setText("Select Date");
					textViewSelectedDate.setText(""+datepicker.getDayOfMonth()+"-"+datepicker.getMonth()+"-"+datepicker.getYear());
				}else{
					datepicker.setVisibility(View.VISIBLE);
					buttonDateSelect.setText("Press again to select date");
				}
				
			}
		});
		
		// Button Search
		buttonSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				CityBean b = (CityBean)spinnerCity.getSelectedItem();
				String date = textViewSelectedDate.getText().toString();
				if(!date.trim().equals("")){
					String[] s = date.split("-");
					date = s[2]+"-"+s[1]+"-"+s[0];
				}
				JSONObject ob = RequestHandler.searchShare(b.getCityId(), date);
				try{
					ArrayList data = new ArrayList<Map<String, String>>();
					ArrayList ids = new ArrayList();
					
					JSONArray arr = ob.getJSONArray("names");
					JSONArray arrPhone = ob.getJSONArray("phone");
					JSONArray arrTime = ob.getJSONArray("time");
					JSONArray arrIds = ob.getJSONArray("ids");
					
					for(int i=0;i<arr.length();i++){
						Map<String, String> datum = new HashMap<String, String>(2);
						datum.put("title", ""+arr.get(i));
						datum.put("phone", arrTime.get(i)+" - "+arrPhone.get(i));
						data.add(datum);
						ids.add(arrIds.get(i));
					}
					bundle.putStringArrayList("data", data);
					bundle.putStringArrayList("ids", ids);
				}catch(Exception ex){
					ex.printStackTrace();
					Toast.makeText(getApplicationContext(), ""+ex, Toast.LENGTH_LONG).show();
				}
				intentSearchList.putExtras(bundle);
				startActivity(intentSearchList);
			}
		});
		
	}
	
}
