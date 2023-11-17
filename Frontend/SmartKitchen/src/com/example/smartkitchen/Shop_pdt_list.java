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

public class Shop_pdt_list extends Activity {
	ListView l1;
	public String shopid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_pdt_list);
		l1=(ListView)findViewById(R.id.listView1);
		shopid=getIntent().getStringExtra("sid");
		new getjson().execute();
		
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Shop_pdt_detail.class);
				
				HashMap<String, String>hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				i.putExtra("pid", hmap.get("pid"));
				i.putExtra("pname", hmap.get("pname"));
				i.putExtra("ptype", hmap.get("ptype"));
				i.putExtra("pimage", hmap.get("pimage"));
				i.putExtra("pqty", hmap.get("pqty"));
				i.putExtra("price", hmap.get("price"));
				i.putExtra("sid", hmap.get("sid"));
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_pdt_list, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"product/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							
							
		                    String pid = c.getString("pid");	
		                    String pname = c.getString("pname");
		                    String ptype = c.getString("ptype");
		                    String pimage = c.getString("pimage");
		                    String pqty = c.getString("pqty");
		                    String price = c.getString("price");
		                    String sid = c.getString("sid");
		                   
		                    if(shopid.equals(sid))
		                    {
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("pid", pid);
		                    contact.put("pname", pname);
		                    contact.put("ptype", ptype);
		                    contact.put("pimage", pimage);
		                    contact.put("pqty", pqty);
		                    contact.put("price", price);
		                    contact.put("sid", sid);
		                    
		                    al.add(contact);
		                    }
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No Products Found yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No Products Found yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         Toast.makeText(getApplicationContext(), Login.uid, 3).show();
			         
			         ListAdapter adapter = new SimpleAdapter(Shop_pdt_list.this, al,
			            R.layout.shop_pdt_list, new String[]{ "pname","ptype"}, 
			               new int[]{R.id.textView1, R.id.textView2,});
			         l1.setAdapter(adapter);
			      }
			}
}
