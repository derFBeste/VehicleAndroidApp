package com.project5.vehiclefredbesteman;

import java.util.ArrayList;

import com.example.vehiclefredbesteman.AboutActivity;
import com.example.vehiclefredbesteman.DetailActivity;
import com.example.vehiclefredbesteman.Mileage;
import com.example.vehiclefredbesteman.MileageDB;
import com.example.vehiclefredbesteman.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class VehicleFredBestemanActivity extends Activity {

	private static final String TAG = "*** FB ***";
	ListView listMileage;
	MileageDB db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_fred_besteman);
		
		db = new MileageDB(this);
		
		//An ArrayList of movies
		ArrayList<Mileage> allRecords = db.getMileageRecords();
		
		for(Mileage m : allRecords)
			Log.d(TAG, "Yo!");
		
		listMileage = (ListView) findViewById(R.id.listMileage);
		Cursor cursor = db.getMileageAsCursor();
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
				R.layout.list_entry4,
				cursor,
				new String[] {"date", "price", "gallons", "miles"},
				new int[]{R.id.date_entry, R.id.price_entry, R.id.gallon_entry, R.id.mile_entry});
		
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

