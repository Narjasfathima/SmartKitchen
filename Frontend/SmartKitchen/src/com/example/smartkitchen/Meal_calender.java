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

public class Meal_calender extends Activity {
EditText e1;
Button b1;
public String perm="ok";
public static String mcdate="";
public static String d;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meal_calender);
		
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				String dt = e1.getText().toString();
//				String dtPattern = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ";
//				if (!dt.matches(dtPattern)) {
//					e1.setText("");
//					e1.setError("Invalid date format...");
//				}
//				if (dt.length() !=10) {
//					e1.setText("");
//					e1.setError("Please follow the hint...");
//				}
				
				if(!e1.getText().toString().equals(""))
				{
					d = e1.getText().toString();
	//				
					Intent ii=new Intent(getApplicationContext(),Mc_section.class);
					startActivity(ii);
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.meal_calender, menu);
		return true;
	}

}
