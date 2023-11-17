package com.example.smartkitchen;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Kb_recipie extends Activity {
ListView l1;
String rcnt;

ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kb_recipie);
		l1=(ListView)findViewById(R.id.listView1);
		new getjson2().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kb_recipie, menu);
		return true;
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
		                    String n = c.getString("name");	                    
		                    String re = c.getString("rec");	 
		               
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("n", n);
		                    contact.put("re", re);
		                  
		                   
		                    
		                  
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
			         ListAdapter adapter = new SimpleAdapter(Kb_recipie.this, al,
			            R.layout.kb_recipie, new String[]{ "n","re"}, 
			               new int[]{R.id.textView1,R.id.textView2});
			         l1.setAdapter(adapter);
			         Toast.makeText(getApplicationContext(), rcnt, 3).show();
			      }
			}
	
	

}
