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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Mc_list extends Activity {
ListView l1;
Button b1;
TextView t1;
public String m;
String d;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mc_list);
		l1=(ListView)findViewById(R.id.listView1);
		b1=(Button)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
		
		m = getIntent().getStringExtra("m");
		
	if (m.equals("mcbreak"))
			t1.setText("Breakfast Chart");
		else if (m.equals("mclunch"))	
			t1.setText("Lunch Chart");
		else if(m.equals("mcdinner"))
			t1.setText("Dinner Chart");
		
		new getjson().execute();
		
		d=Meal_calender.d;
		
		
		Toast.makeText(getApplicationContext(), m+d, 3).show();
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Mc_list_add.class);
				i.putExtra("m", m);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mc_list, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"meal_c/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String mcid = c.getString("mcid");	
		                    String uid = c.getString("uid");
		                    String mcdate = c.getString("mcdate");
		                    String mctime = c.getString("mctime");
		                    
							if(m.equals("mcbreak")){

			                    String mcbreak = c.getString("mcbreak");
			                    HashMap<String, String> contact =  new HashMap<String, String>();
			                    contact.put("mcbreak", mcbreak);
			                    if(d.equals(mcdate)&& !mcbreak.equals("Pending"))
			                    {
			                             	al.add(contact);
			                    }
							}
							
							if(m.equals("mclunch")){
								String mclunch = c.getString("mclunch");
								HashMap<String, String> contact =  new HashMap<String, String>();
								contact.put("mcbreak", mclunch);
								if(d.equals(mcdate)&& !mclunch.equals("Pending")){
//			                   	     
//			                    	
			                    	al.add(contact);
			                    }
							}
		                    
							if(m.equals("mcdinner")){
								String mcdinner = c.getString("mcdinner");
								HashMap<String, String> contact =  new HashMap<String, String>();
								 contact.put("mcbreak", mcdinner);
								 if(d.equals(mcdate)&& !mcdinner.equals("Pending")){
//			                   	     
//			                    	
			                    	al.add(contact);
			                    }
							}
		                    
		                    
//		                   
//
//		                    
		                    
		                    
		                    
		                   
//		                    
           
		                    
		                    
//		                    else{
//		                    	Toast.makeText(getApplicationContext(), "No Meals Found yet...", 3).show();	
//		                    }
		                  
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
//							Toast.makeText(getApplicationContext(), s, 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No videos Found yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         Toast.makeText(getApplicationContext(), Login.uid, 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(Mc_list.this, al,
			            R.layout.mc_list, new String[]{ "mcbreak"}, 
			               new int[]{R.id.textView1,});
			         l1.setAdapter(adapter);
			      
				}
			}

}
