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
import android.widget.Toast;

public class Video_comment_list extends Activity {
	ListView l1;
	Button b1;
	public String vid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_comment_list);
		l1=(ListView)findViewById(R.id.listView1);
		b1=(Button)findViewById(R.id.button1);
		vid=getIntent().getStringExtra("cid");
		Toast.makeText(getApplicationContext(), vid, 3).show();
		new getjson().execute();
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Video_comment_add.class);
				i.putExtra("cid", vid);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_comment_list, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"comment/android3/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							
								String cid = c.getString("cid");
		                    	String comid = c.getString("comid");	                    
			                    String uid = c.getString("uid");
			                    String com = c.getString("com");
			                    String uname=c.getString("uname");
			                    if(vid.equals(cid))
			                    {
			                    	 HashMap<String, String> contact =  new HashMap<String, String>();
					                    contact.put("comid", comid);
					                    contact.put("uid", uid);
					                    contact.put("cid", cid);
					                    contact.put("com", com);
					                   	contact.put("uname", uname);  
					                    al.add(contact);
			                    }
			                   
//		                    }
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No Comments Found yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Comments Found yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         Toast.makeText(getApplicationContext(), Login.uid, 3).show();
					         
					         ListAdapter adapter = new SimpleAdapter(Video_comment_list.this, al,
					            R.layout.video_comment_list, new String[]{ "com","uname"}, 
					               new int[]{R.id.textView3, R.id.textView4,});
				         l1.setAdapter(adapter);
					      }
			}
}
