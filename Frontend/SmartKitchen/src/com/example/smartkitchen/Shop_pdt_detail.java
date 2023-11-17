package com.example.smartkitchen;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Shop_pdt_detail extends Activity {
	TextView t2,t3,t4,t5;
	ImageButton b1;
	EditText e1;
	ImageView i1;
	public String pid,pname,ptype,pimage,pqty,price,sid;
	public String perm="OK";
	public int  q;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_pdt_detail);
		
		
		try
    	{
    		if (android.os.Build.VERSION.SDK_INT > 9) 
    		{
    			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy);
    		}
    	}
    	catch(Exception e)
    	{
    		
    	}
		
		
		t2=(TextView)findViewById(R.id.textView3);
		t3=(TextView)findViewById(R.id.textView4);
		t4=(TextView)findViewById(R.id.textView5);
		t5=(TextView)findViewById(R.id.textView6);
		
		e1=(EditText)findViewById(R.id.editText1);
		
		i1=(ImageView)findViewById(R.id.imageView1);
		
		b1=(ImageButton)findViewById(R.id.imageButton1);
	
		
		pid=getIntent().getStringExtra("pid");
		pname=getIntent().getStringExtra("pname");
		ptype=getIntent().getStringExtra("ptype");
		pimage=getIntent().getStringExtra("pimage");
		price=getIntent().getStringExtra("price");
		pqty=getIntent().getStringExtra("pqty");
		sid=getIntent().getStringExtra("sid");
		
		t2.setText(pname);
		t3.setText(ptype);
		t4.setText(pqty);
		t5.setText(price);
		
		i1.setImageBitmap(downloadBitmap(Login.url+"static/"+pimage));
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String qt = e1.getText().toString();
//				String qtPattern = "[0-9]";
//				if (!qt.matches(qtPattern)) {
//					e1.setText("");
//					e1.setError("Invalid format...");
//				}
				if (qt.length() > 2) {
					e1.setText("");
					e1.setError("Out of stock...");
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

			String url = Login.url+"order/android/";
		
			JSONObject jobj= new JSONObject();
	        try {
	        	String n=e1.getText().toString();
	        	 q=Integer.parseInt(n);
	            	
	            jobj.put("sid", sid);
	        	jobj.put("uid", Login.uid);
	        	jobj.put("oqty",q);
	        	jobj.put("pid",pid);
	        	
	        	
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
	        	 
	        	 Toast.makeText(getApplicationContext(), "cart details added...", 3).show();
	        	 Intent i=new Intent(getApplicationContext(),Shop_pdt_cart.class);
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
		getMenuInflater().inflate(R.menu.shop_pdt_detail, menu);
		return true;
	}
	
	
	 private Bitmap downloadBitmap(String url) {
		    HttpURLConnection urlConnection = null;
		    try {
		        URL uri = new URL(url);
		        urlConnection = (HttpURLConnection) uri.openConnection();
		        int statusCode = urlConnection.getResponseCode();
		        if (statusCode != HttpStatus.SC_OK) {
		            return null;
		           
		        }

		        InputStream inputStream = urlConnection.getInputStream();
		        if (inputStream != null) {
		            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
		            return bitmap;
		        }
		    } catch (Exception e) {
		        urlConnection.disconnect();
		        Log.w("ImageDownloader", "Error downloading image from " + url);
		    } finally {
		        if (urlConnection != null) {
		            urlConnection.disconnect();
		        }
		    }
		    return null;
		}
	 
	 

}
