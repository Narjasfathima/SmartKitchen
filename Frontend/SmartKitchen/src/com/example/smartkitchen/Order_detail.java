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
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Order_detail extends Activity {
ListView l1;
ImageButton b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		
		l1=(ListView)findViewById(R.id.listView1);
		b1=(ImageButton)findViewById(R.id.imageButton1);
		new getjson().execute();
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Shop_pdt_cart.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"order/android2/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
								
		                    String pname = c.getString("pname");
		                    String ptype = c.getString("ptype");
		                    String price = c.getString("price");
		                    String pqty = c.getString("pqty");
		                    String oqty = c.getString("oqty");
		                    String oprice = c.getString("oprice");
		                    String bdate = c.getString("bdate");
		                    String uid = c.getString("uid");
		                    String bid = c.getString("bid");
		                    String bstatus = c.getString("bstatus");

		                    
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("uid", uid);
		                    contact.put("bstatus", bstatus);
		                    
		                    contact.put("bid", bid);
		                    contact.put("oprice", oprice);
		                    contact.put("pname", pname);
		                    contact.put("ptype", ptype);
		                    contact.put("price", price);
		                    contact.put("pqty", pqty);
		                    contact.put("oqty", oqty);
		                    contact.put("oprice", oprice);
		                    contact.put("bdate", bdate);
		                    if(uid.equals(Login.uid)&& bstatus.equals("not delivered"))
		                    {
		                    al.add(contact);
		                    }
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No Orders Found yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Orders Found yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         Toast.makeText(getApplicationContext(), Login.uid, 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(Order_detail.this, al,
			            R.layout.order_details, new String[]{ "pname","ptype","pqty","price","oqty","oprice"}, 
			               new int[]{R.id.textView7, R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,});
			         l1.setAdapter(adapter);
			      }
			}


}
