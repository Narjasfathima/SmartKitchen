package com.example.smartkitchen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Mc_section extends Activity {
Button b1,b2,b3;
public static String secname,perm="ok";
public String m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mc_section);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				m="mcbreak";
				Toast.makeText(getApplicationContext(), "Section selected "+m , 3).show();
	        	 Intent ii=new Intent(getApplicationContext(),Mc_list.class);
	        	 ii.putExtra("m", m);
					startActivity(ii);
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				new savejson1().execute();
				m="mclunch";
				Toast.makeText(getApplicationContext(), "Section selected "+m, 3).show();
	        	 Intent ii=new Intent(getApplicationContext(),Mc_list.class);
	        	 ii.putExtra("m", m);
					startActivity(ii);
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				new savejson2().execute();
				m="mcdinner";
				Toast.makeText(getApplicationContext(), "Section selected " +m, 3).show();
	        	 Intent ii=new Intent(getApplicationContext(),Mc_list.class);
	        	 ii.putExtra("m", m);
					startActivity(ii);
			}
		});
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mc_section, menu);
		return true;
	}

}
