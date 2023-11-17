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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Shop_list extends Activity {
ListView l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_list);
		
		l1=(ListView)findViewById(R.id.listView1);
		new getjson().execute();
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Shop_pdt_list.class);
				
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				i.putExtra("sname", hmap.get("sname"));
				i.putExtra("splace", hmap.get("splace"));
				i.putExtra("sphno", hmap.get("sphno"));
				i.putExtra("semail", hmap.get("semail"));
				i.putExtra("status", hmap.get("status"));
				i.putExtra("sid", hmap.get("sid"));
				i.putExtra("lid", hmap.get("lid"));
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_list, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"shop/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							
							
		                    String sid = c.getString("sid");	
		                    String sname = c.getString("sname");
		                    String splace = c.getString("splace");
		                    String sphno = c.getString("sphno");
		                    String semail = c.getString("semail");
		                    String status = c.getString("status");
		                    String lid = c.getString("lid");
		                   

		                    if(status.equals("accept"))
		                    {
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("sid", sid);
		                    contact.put("sname", sname);
		                    contact.put("splace", splace);
		                    contact.put("sphno", sphno);
		                    contact.put("semail", semail);
		                    contact.put("status", status);
		                    contact.put("lid", lid);
		                    
           
		                    	al.add(contact);
		                    }
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No Shops Found yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Shops Found yet...", 3).show();
					}
					return null;
				}
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         Toast.makeText(getApplicationContext(), Login.uid, 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(Shop_list.this, al,
			            R.layout.shop_list, new String[]{ "sname","splace"}, 
			               new int[]{R.id.textView1, R.id.textView2,});
			         l1.setAdapter(adapter);
			      }
	}
}
