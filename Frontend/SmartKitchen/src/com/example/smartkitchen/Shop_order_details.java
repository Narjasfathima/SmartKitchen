package com.example.smartkitchen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Shop_order_details extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_order_details);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shop_order_details, menu);
		return true;
	}

}
