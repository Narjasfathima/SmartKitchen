package com.example.smartkitchen;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Kitchen_basket extends Activity {
Spinner s1,s2,s3,s4,s5;
ImageButton b1;
ListView l1;
public String[] ingred,inid;
String s,n;
String i1,i2,i3,i4,i5;


String rcnt;

ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kitchen_basket);
		
		s1=(Spinner)findViewById(R.id.spinner1);
		s2=(Spinner)findViewById(R.id.spinner2);
		s3=(Spinner)findViewById(R.id.spinner3);
		s4=(Spinner)findViewById(R.id.spinner4);
		s5=(Spinner)findViewById(R.id.spinner5);
		
		
		l1=(ListView)findViewById(R.id.listView1);
		
		new getjson().execute();
		
		
		
		
		b1=(ImageButton)findViewById(R.id.imageButton1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				
				new savejson().execute();
				
			}
		});
		
		
		
		s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				i1=inid[arg2];
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		s2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				i2=inid[arg2];
				
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		s3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				i3=inid[arg2];
				
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		s4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				i4=inid[arg2];
				
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		s5.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				i5=inid[arg2];
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

		});

		
		
		
	}
	private class savejson extends AsyncTask<Void, Void, Void>{
		private static final boolean True = false;

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			String url = Login.url+"kitchen_b/tech/";
		
			JSONObject jobj= new JSONObject();
	        try {
	        	
	        	jobj.put("in1",i1);
	        	jobj.put("in2",i2);
	        	jobj.put("in3",i3);
	        	jobj.put("in4",i4);
	        	jobj.put("in5",i5);
	     
	        	
	        	s=JsonHandler.Postjson(url, jobj);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null; 
		}
		
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);  
//	         if(!perm.equals("error"))
//	         {
//	        	 
	        	 Toast.makeText(getApplicationContext(), "Preparing...", 3).show();
//	        	 Intent ii=new Intent(getApplicationContext(),Home.class);
//					startActivity(ii);
//	        	 
	        	 
//	        	 new getjson2().execute();
	        	 Intent i= new Intent(getApplicationContext(),Kb_recipie.class);
					startActivity(i);
					
					
	        	 
	        	 
	        	 
//	         }
//	         else
//	         {
//	        	  
	        	 Toast.makeText(getApplicationContext(), s, 3).show();
//	         }
	      }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kitchen_basket, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String url = Login.url+"kitchen_b/android1/";
			JSONArray jdata=JsonHandler.GetJson(url);
			
			 inid=new String[jdata.length()];
			 ingred=new String[jdata.length()];
			 
			if(jdata!=null)
			{
				try {
				for (int i = 0; i < jdata.length(); i++) {
                    JSONObject c;
					c = jdata.getJSONObject(i);
					String id=c.getString("inid");
					String in =c.getString("ingred");
					 inid[i]=id;
					 ingred[i]=in;
					 }
					 
					 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				
			}
			return null;
		}
		
		@Override
	      protected void onPostExecute(Void result) {
	         super.onPostExecute(result);

	         ArrayAdapter<String> ad=new ArrayAdapter<String>(Kitchen_basket.this, android.R.layout.simple_spinner_item,ingred);
			 s1.setAdapter(ad);
			 s2.setAdapter(ad);
			 s3.setAdapter(ad);
			 s4.setAdapter(ad);
			 s5.setAdapter(ad);
	         
	      }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class getjson2 extends AsyncTask<Void, Void, Void>{
		
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"kitchen_b/tech/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
							rcnt=Integer.toString(jdata.length());
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
		                     n = c.getString("name");	                    
		                    
		               
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("n", Integer.toString(i));
		                  
		                   
		                    
		                  
//		                    al.add(contact);
		                    
			                    al.add(contact);
		                   
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         ListAdapter adapter = new SimpleAdapter(Kitchen_basket.this, al,
			            R.layout.res, new String[]{ "n"}, 
			               new int[]{R.id.textView1});
			         l1.setAdapter(adapter);
			         Toast.makeText(getApplicationContext(), rcnt, 3).show();
			      }
			}
	
	
	
}
