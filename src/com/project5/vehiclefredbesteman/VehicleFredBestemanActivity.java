package com.project5.vehiclefredbesteman;

import com.example.vehiclefredbesteman.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class VehicleFredBestemanActivity extends Activity {

	private static final String TAG = "**********  FB **********";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_fred_besteman);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.optiions_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			Log.d(TAG, "Add menu");
			return true;
		case R.id.about:
			Log.d(TAG, "About menu");
			return true;
		case R.id.reset:
			Log.d(TAG, "Reset menu");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}	
	
	
	
}

