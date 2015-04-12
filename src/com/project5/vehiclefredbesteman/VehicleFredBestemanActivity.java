package com.project5.vehiclefredbesteman;

import com.example.vehiclefredbesteman.AboutActivity;
import com.example.vehiclefredbesteman.DetailActivity;
import com.example.vehiclefredbesteman.MileageDB;
import com.example.vehiclefredbesteman.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class VehicleFredBestemanActivity extends Activity {

	private static final String TAG = "*** FB ***";
	ListView listMileage;
	MileageDB db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_fred_besteman);
		
		db = new MileageDB(this);
		
		
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
			startActivity(new Intent(getApplicationContext(), DetailActivity.class));
			return true;
		case R.id.about:
			Log.d(TAG, "About menu");
			startActivity(new Intent(getApplicationContext(), AboutActivity.class));
			return true;
		case R.id.reset:
			Log.d(TAG, "Reset menu");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}	
	
	
	
}

