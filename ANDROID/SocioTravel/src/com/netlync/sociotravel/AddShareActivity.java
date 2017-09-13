package com.netlync.sociotravel;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.netlync.sociotravel.bean.CityBean;
import com.netlync.sociotravel.handle.RequestHandler;

public class AddShareActivity extends Activity{

	Button buttonDateSelect, buttonAddShare;
	DatePicker datePicker;
	EditText editTextFeature;
	TextView textViewDateSelected, textViewUser;
	Spinner spinnerCountry, spinnerCityTo, spinnerCityFrom, spinnerVehicle;
	TimePicker timePicker;
	Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addshare);
		init();
		setOnClick();
		setSpinnerChange();
		setCountries();
		
		bundle = getIntent().getExtras();	
		
	}
	
	public void init(){
		buttonDateSelect = (Button)findViewById(R.id.buttonDateAddShare);
		datePicker = (DatePicker)findViewById(R.id.datePickerAddShare);
		datePicker.setVisibility(View.GONE);
		textViewDateSelected = (TextView)findViewById(R.id.textViewDateSelectAddShare);
		textViewUser = (TextView)findViewById(R.id.textViewUserAddShare);
		spinnerCountry = (Spinner)findViewById(R.id.spinnerCountryAddShare);
		spinnerCityTo = (Spinner)findViewById(R.id.spinnerToCityAddShare);
		spinnerCityFrom = (Spinner)findViewById(R.id.spinnerFromCityAddShare);
		spinnerVehicle = (Spinner)findViewById(R.id.spinnerVehicleAddShare);
		buttonAddShare = (Button)findViewById(R.id.buttonAddShare);
		timePicker = (TimePicker)findViewById(R.id.timePickerAddShare);
		editTextFeature = (EditText)findViewById(R.id.editTextFeature);
	}
	
	public void setCountries(){
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
			
			JSONArray vehicleArr = ob.getJSONArray("vehicle");
			ArrayList<String> vehicles = new ArrayList<String>();
			for(int i=0;i<vehicleArr.length();i++){
				vehicles.add((String)vehicleArr.get(i));
			}
		
			ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, vehicles);
				vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerVehicle.setAdapter(vehicleAdapter);
			
			setCities(countries.get(0));
			
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "Couldn't get countries and vehicle", Toast.LENGTH_LONG).show();
			
		}
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
	
	public void setOnClick(){
		buttonDateSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if( datePicker.getVisibility() == View.VISIBLE){
					datePicker.setVisibility(View.GONE);
					buttonDateSelect.setText("Select Date");
					textViewDateSelected.setText(""+datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear());
				}else{
					datePicker.setVisibility(View.VISIBLE);
					buttonDateSelect.setText("Press again to select date");
				}
				
			}
		});
		
		buttonAddShare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				CityBean beanFrom= (CityBean)spinnerCityFrom.getSelectedItem();
				CityBean beanTo= (CityBean)spinnerCityTo.getSelectedItem();
				String date = textViewDateSelected.getText().toString();
				int hr = timePicker.getCurrentHour();
				int min = timePicker.getCurrentMinute();
				String veh = (String)spinnerVehicle.getSelectedItem();
				String customerId = bundle.getString("customerId");
				String feature = editTextFeature.getText().toString();
				
				String[] s = date.split("-");
				date = s[2]+"-"+s[1]+"-"+s[0];
				
				JSONObject ob = RequestHandler.addShare(customerId, beanFrom.getCityId(), beanTo.getCityId(), date, hr+":"+min+":00", veh, feature);
				try{
					System.out.println(ob.get("query"));
					Toast.makeText(getApplicationContext(), ""+ob.getString("message"), Toast.LENGTH_LONG).show();	
					
				}catch(Exception ex){
					Toast.makeText(getApplicationContext(), "Error getting response", Toast.LENGTH_LONG).show();
					ex.printStackTrace();
				}
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
			spinnerCityTo.setAdapter(dataAdapter);
			spinnerCityFrom.setAdapter(dataAdapter);
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "Exception occured at setCity>SigninPro", Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}
	
}
