package com.example.smartkitchen;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends Activity {
	ImageButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
//	Button tech;
	String date,details;
	public String outp1="",outp="";
	public String temp,hum,al="";
	
	MediaPlayer catSoundMediaPlayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		b1=(ImageButton)findViewById(R.id.imageButton4);
		b2=(ImageButton)findViewById(R.id.imageButton3);
		b3=(ImageButton)findViewById(R.id.imageButton6);
		b4=(ImageButton)findViewById(R.id.imageButton7);
		b5=(ImageButton)findViewById(R.id.imageButton8);
		b6=(ImageButton)findViewById(R.id.imageButton5);
		b7=(ImageButton)findViewById(R.id.imageButton1);
		b8=(ImageButton)findViewById(R.id.imageButton2);
		b9=(ImageButton)findViewById(R.id.imageButton9);
		
//		tech=(Button)findViewById(R.id.button1);
		
		new getjson().execute();
		
		new savejson().execute();
		
		new savejson1().execute();
		
		catSoundMediaPlayer = MediaPlayer.create(this, R.drawable.alarm);

		
		
		b9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i= new Intent(getApplicationContext(),Tech_view.class);
				startActivity(i);
				
				catSoundMediaPlayer.stop();
				
			}
		});
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Kitchen_basket.class);
				startActivity(i);
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Video_list.class);
				startActivity(i);
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Shop_list.class);
				startActivity(i);
			}
		});
		
		b4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Add_feedback.class);
				startActivity(i);
			}
		});
		
		b5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Meal_calender.class);
				startActivity(i);
			}
		});
		
		b6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Order_detail.class);
				startActivity(i);
			}
		});
		
		b7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Profile.class);
				startActivity(i);
			}
		});
		
		b8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Login.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;

	}

	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"feed/android11/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							
								date = c.getString("tdate");
		                    	details = c.getString("detail");	                    
			                   
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
			         
//			         Toast.makeText(getApplicationContext(), details, 3).show();				       
					      }
			}
	

	
	
//	SAVEJSON
	
	
	 
	    
		private class savejson extends AsyncTask<Void, Void, Void>{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				String url = Login.url+"feed/android11/";
				JSONObject jobj= new JSONObject();
		        try {

		        	jobj.put("detail", details);
		     
					outp= JsonHandler.Postjson(url, jobj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);
		      

				 String currentString = outp;
				 String[] separated = currentString.split("#");
				 String a = separated[0];
				 String b= separated[1]; 

				 addNotification();
				 
				 
				 if(a.equals("found")||b.equals("high"))
				 {
					 Toast.makeText(getApplicationContext(), "Alarm", 5).show();
					
					 catSoundMediaPlayer.start();
				 }
				 else{
					 
					 Toast.makeText(getApplicationContext(), "No data", 5).show();
				 }
				 
		      }
			
			
			
		}
	

	
	
		private void addNotification() {
			 AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			 audioManager.setStreamVolume(
						AudioManager.STREAM_RING, 10,
						AudioManager.FLAG_ALLOW_RINGER_MODES
								| AudioManager.FLAG_PLAY_SOUND);
			  NotificationCompat.Builder builder =
			     new NotificationCompat.Builder(this)
			     .setSmallIcon(R.drawable.ic_launcher)
			     .setContentTitle("Kitchen Monitor")
			     .setContentText(details);

			  Intent notificationIntent = new Intent(this,Home.class);
			  PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
			     PendingIntent.FLAG_UPDATE_CURRENT);
			  builder.setContentIntent(contentIntent);

			  // Add as notification
			  NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			  manager.notify(0, builder.build());
			}

	
		
		
		
		private class savejson1 extends AsyncTask<Void, Void, Void>{
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				
				String url = Login.url+"meal_c/android2/";
				JSONObject jobj= new JSONObject();
		        try {

		        	jobj.put("uid",Login.uid);
		     
					outp1= JsonHandler.Postjson(url, jobj);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);
		      

//					 Toast.makeText(getApplicationContext(), outp1, 5).show();
					 
					 if(outp1.equals("ok"))
					 {
					 	addNotification1();
					 }
					 else
					 {
						 
					 }
					 
		      }
			
			
			
		}
		
		
		
		
		
		private void addNotification1() {
			 AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			 audioManager.setStreamVolume(
						AudioManager.STREAM_RING, 10,
						AudioManager.FLAG_ALLOW_RINGER_MODES
								| AudioManager.FLAG_PLAY_SOUND);
			  NotificationCompat.Builder builder =
			     new NotificationCompat.Builder(this)
			     .setSmallIcon(R.drawable.ic_launcher)
			     .setContentTitle("Meal Calander")
			     .setContentText("Text...");

			  Intent notificationIntent = new Intent(this,Home.class);
			  PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
			     PendingIntent.FLAG_UPDATE_CURRENT);
			  builder.setContentIntent(contentIntent);

			  // Add as notification
			  NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			  manager.notify(0, builder.build());
			}

		
		
	
	
	
}
