 package com.example.smartkitchen;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Add_feedback extends Activity {
EditText e1;
ImageButton b1;
public static String perm="Ok";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_feedback);
		
		e1=(EditText)findViewById(R.id.editText1);
		b1=(ImageButton)findViewById(R.id.imageButton1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String feed = e1.getText().toString();
				if (feed.length() > 50) {
					e1.setText("");
					e1.setError("Length too long...");
				}
				
				
				if(!e1.getText().toString().equals(""))
				{
				
					new savejson().execute();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Fill", 3).show();
				}
				
				
			}
		});
	}

	
	 private class savejson extends AsyncTask<Void, Void, Void>{
			private static final boolean True = false;

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub

				String url = Login.url+"feed/android/";
			
				JSONObject jobj= new JSONObject();
		        try {
		        	
		        	jobj.put("feed",e1.getText().toString());
		        	jobj.put("lid",Login.uid);
		     
		        	
		        	String s=JsonHandler.Postjson(url, jobj);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);  
		         if(!perm.equals("error"))
		         {
		        	 
		        	 Toast.makeText(getApplicationContext(), "Feedback added...", 3).show();
		        	 Intent ii=new Intent(getApplicationContext(),Home.class);
						startActivity(ii);
		        	 
		        	 
		         }
		         else
		         {
		        	  
		        	 Toast.makeText(getApplicationContext(), "Please type any feedback....", 3).show();
		         }
		      }
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_feedback, menu);
		return true;
	}

}
