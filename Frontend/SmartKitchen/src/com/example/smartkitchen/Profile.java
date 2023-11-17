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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Profile extends Activity {
	EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
	RadioButton r1,r2,r3;
	RadioGroup ra;
	ImageButton b1;
	public String perm="OK",pwd;
	String uid;	
    String uname;
    String ugender ;
    String uplace;
    String homename;
    String upin ;
    String upost;
    String uemail ;
    String uphno;
    String username;
    String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		e5=(EditText)findViewById(R.id.editText5);
		e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
		e8=(EditText)findViewById(R.id.editText8);
		e9=(EditText)findViewById(R.id.editText9);
		
		r1=(RadioButton)findViewById(R.id.radio0);
		r2=(RadioButton)findViewById(R.id.radio1);
		ra=(RadioGroup)findViewById(R.id.radioGroup1);
		r3=(RadioButton)findViewById(R.id.radio2);
		
		b1=(ImageButton)findViewById(R.id.imageButton1);
		new getjson().execute();
		
		
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String uname = e1.getText().toString();
				if (uname.length() > 25) {
					e1.setText("");
					e1.setError("Length too long...");
				}
				
				String hn = e2.getText().toString();
				if (hn.length() > 50) {
					e2.setText("");
					e2.setError("Length too long...");
				}
				
				String pc = e3.getText().toString();
				if (pc.length() > 50) {
					e3.setText("");
					e3.setError("Length too long...");
				}
				
				String po = e4.getText().toString();
				String poPattern = "[a-zA-Z ]";
				if (!po.matches(poPattern)) {
					e4.setText("");
					e4.setError("Invalid format...");
				}
				
				String pn = e5.getText().toString();
				if (pn.length() != 6) {
					e5.setText("");
					e5.setError("Invalid format...");
				}
				
				String em = e7.getText().toString();
				String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+com+";
				if (!em.matches(emailPattern)) {
					e7.setText("");
					e7.setError("Not in e-mail format");
				}

				
				String mo = e6.getText().toString();
				if (mo.length() != 10) {
					e6.setText("");
					e6.setError("Invalid number...");
				}
				
				String usname = e8.getText().toString();
				if (uname.length() < 6) {
					e8.setText("");
					e8.setError("Please enter minimum 6 characters... ");
				}
				
				String pwdd = e9.getText().toString();
				if (pwdd.length() < 6) {
					e9.setText("");
					e9.setError("Password must be atleast six characters... ");
				}
				
				if(!e1.getText().toString().equals("")&&!e2.getText().toString().equals("")&&!e3.getText().toString().equals("")&&!e4.getText().toString().equals("")&&!e5.getText().toString().equals("")&&!e6.getText().toString().equals("")&&!e7.getText().toString().equals("")&&!e8.getText().toString().equals("")&&!e9.getText().toString().equals(""))
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

			String url = Login.url+"user/android1/";
		
			JSONObject jobj= new JSONObject();
	        try {
	        	
	        	String gender;
	        	if(r1.isChecked())
	        	{
	        		gender="Male";
	        	}
	        	else if (r2.isChecked()) {
					gender="Female";
				}
	        	else
	        		gender="Other";
	        	
	        	pwd=e9.getText().toString();
	        	if(pwd.equals(password))
	        	{
	        	jobj.put("uname",e1.getText().toString());
	        	jobj.put("ugender",gender);
	        	jobj.put("uplace",e3.getText().toString());
	        	jobj.put("homename",e2.getText().toString());
	        	jobj.put("upost",e4.getText().toString());
	        	jobj.put("upin",e5.getText().toString());
	        	jobj.put("uemail",e7.getText().toString());
	        	jobj.put("uphno",e6.getText().toString());
	        	jobj.put("username",e8.getText().toString());
	        	jobj.put("lid", Login.uid);
	        	
	        	String s=JsonHandler.Postjson(url, jobj);
	        	}
				
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
	        	 
	        	 Toast.makeText(getApplicationContext(), "Profile updation Successfull...", 3).show();
	        	 Intent ii=new Intent(getApplicationContext(),Profile.class);
					startActivity(ii);
	        	 
	        	 
	         }
	         else
	         {
	        	  
	        	 Toast.makeText(getApplicationContext(), "Updtion failed...!", 3).show();
	         }
	      }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	private class getjson extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"user/android1/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							String lid=c.getString("lid");
							if(lid.equals(Login.uid))
							{
								
								
		                     uid = c.getString("uid");	
		                     uname = c.getString("uname");
		                     ugender = c.getString("ugender");
		                     uplace = c.getString("uplace");
		                     homename = c.getString("homename");
		                     upin = c.getString("upin");
		                     upost = c.getString("upost");
		                     uemail = c.getString("uemail");
		                     uphno = c.getString("uphno");
		                     username = c.getString("username");
		                     password=c.getString("password");
		                     
//		                     if(ugender.equals("Male"))
//		                     {
//		                    	 r1.isSelected();
//		                     }
//		                     else 
////		                    	 r2.isS
//		                    
//		              
							}
		                  
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Toast.makeText(getApplicationContext(), "No User Found yet...", 3).show();
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No user Found yet...", 3).show();
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         
//			         if(ugender.equals("Male"))
//			        	 r1.setSelected(true);
//			         else if (ugender.equals("Female")) 				
//			        	 r2.setSelected(true);
//			         else if (ugender.equals("Other"))
//			        	 r3.setSelected(true);
			         
			         

			                if(ugender.equals("Male")){
			                    r1.setChecked(true);
			                    r2.setChecked(false);
			                    r3.setChecked(false);

			                }
			                else if(ugender.equals("Female")){
			                    r1.setChecked(false);
			                    r2.setChecked(true);
			                    r3.setChecked(false);

			                }
			                else{
			                    r2.setChecked(false);
			                    r1.setChecked(false);
			                    r3.setChecked(true);
			                }
			         
			         	e1.setText(uname);
	                   e3.setText(uplace);
	                   e2.setText(homename);
	                   e5.setText(upin);
	                   e4.setText(upost);
	                   e7.setText(uemail);
	                   e6.setText(uphno);
	                   e8.setText(username);
			      }
			}

}

