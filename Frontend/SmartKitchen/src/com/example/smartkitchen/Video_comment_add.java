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
import android.widget.Toast;

public class Video_comment_add extends Activity {
	EditText e1;
	Button b1;
	public String cid,perm="OK";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_comment_add);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		cid=getIntent().getStringExtra("cid");
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String cm = e1.getText().toString();
				if (cm.length() > 50) {
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

			String url = Login.url+"comment/android/";
		
			JSONObject jobj= new JSONObject();
	        try {
	        	
	        	
	        	jobj.put("com",e1.getText().toString());
	        	jobj.put("uid",Login.uid);
	        	jobj.put("cid",cid);
	        	
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
	        	 
	        	 Toast.makeText(getApplicationContext(), "Comment added...", 3).show();
	        	 Intent i=new Intent(getApplicationContext(),Video_comment_list.class);
	        	 i.putExtra("cid",cid);
				startActivity(i);
	        	 
	        	 
	         }
	         else
	         {
	        	  
	        	 Toast.makeText(getApplicationContext(), "Failed...", 3).show();
	         }
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_comment_add, menu);
		return true;
	}

}
