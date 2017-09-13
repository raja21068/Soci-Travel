package com.netlync.sociotravel;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchViewActivity extends Activity{

	ListView listView;
	Bundle bundle;
	ArrayList ids;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_view);
		init();
		
		bundle = getIntent().getExtras();
		ArrayList  data =bundle.getStringArrayList("data");
		ids = bundle.getStringArrayList("ids");
		
		SimpleAdapter adapter = new SimpleAdapter(this, data,
		                                              android.R.layout.simple_list_item_2,
		                                              new String[] {"title", "phone"},
		                                              new int[] {android.R.id.text1,
		                                                         android.R.id.text2});
		listView.setAdapter(adapter);
		    
	}
	
	public void init(){
		listView = (ListView)findViewById(R.id.listViewSearch);

		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int integer,
					long arg3) {
				
				new LoadDetailShare().execute(0);
			}
			
		});
		
	}
	
	private class LoadDetailShare extends AsyncTask<Integer, Void, Void>{
		
		Dialog dialog ;
		TextView tvName, tvPhone, tvFrom;
		ProgressBar progressBar;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			dialog = new Dialog(SearchViewActivity.this);
            // Include dialog.xml file
            dialog.setContentView(R.layout.dialog);
            // Set dialog title
            dialog.setTitle("Details");
            dialog.show();
            
            progressBar = (ProgressBar)dialog.findViewById(R.id.progressBarDialog);
            progressBar.setVisibility(View.VISIBLE);
            
            tvName = (TextView)dialog.findViewById(R.id.textViewNameDialog);
            tvPhone = (TextView)dialog.findViewById(R.id.textViewPhoneDialog);
            tvFrom = (TextView)dialog.findViewById(R.id.textViewFromDialog);
		
            Button b = (Button)dialog.findViewById(R.id.buttonOk);
            b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
		}
		
		@Override
		protected Void doInBackground(Integer... arg0) {
			progressBar.setVisibility(View.GONE);
            return null;
		}
			
		@Override
		protected void onPostExecute(Void result) {
			
			super.onPostExecute(result);
		}
	}
	
}
