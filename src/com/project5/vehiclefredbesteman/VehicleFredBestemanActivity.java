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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class VehicleFredBestemanActivity extends Activity {

	private static final String TAG = "*** FB ***";
	ListView listMileage;
	MileageDB db;
	ArrayAdapter<Mileage> adapterContextMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vehicle_fred_besteman);
				
		db = new MileageDB(this);
		
		
		//An ArrayList of movies
		ArrayList<Mileage> allRecords = db.getMileageRecords();
		
		for(Mileage m : allRecords)
			Log.d(TAG, String.format("%s, %f, %f, %f", m.getDate(), m.getPrice(), m.getGallons(), m.getMiles()));
		
		listMileage = (ListView) findViewById(R.id.listMileage);
		
		//registers context menu to listMileage
		registerForContextMenu(listMileage);
		
		adapterContextMenu = new ArrayAdapter<Mileage>(this, android.R.layout.simple_list_item_1);
		listMileage.setAdapter(adapterContextMenu);
		
		Cursor cursor = db.getMileageAsCursor();
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
				R.layout.list_entry4,
				cursor,
				new String[] {"date", "price", "gallons", "miles"},
				new int[]{R.id.date_entry, R.id.price_entry, R.id.gallon_entry, R.id.mile_entry});
		
		listMileage.setAdapter(adapter);
		
		adapter.setViewBinder(
				new ViewBinder(){
					@Override
					public boolean setViewValue(View view, Cursor cursor, int column){
						if(column == MileageDB.DATE_COL){
							String date = cursor.getString(MileageDB.DATE_COL);
							TextView text = (TextView) view;
							text.setText(date);
							return true;
						}
						if(column == MileageDB.GALLONS_COL){
							Double gallons = cursor.getDouble(MileageDB.GALLONS_COL);
							TextView text = (TextView) view;
							text.setText(String.format("%.2f gallons", gallons));
							return true;
						}
						if(column == MileageDB.PRICE_COL){
							Double price = cursor.getDouble(MileageDB.PRICE_COL);
							TextView text = (TextView) view;
							text.setText(String.format("$%.2f", price));
							return true;
						}
						if(column == MileageDB.MILES_COL){
							Double miles = cursor.getDouble(MileageDB.MILES_COL);
							TextView text = (TextView) view;
							text.setText(String.format("%.2f miles", miles));
							return true;
						}					
						return false;
					}			
		});
		
	}
	
//	public void onPause(){
//		
//	}
//	
	public void onResume(){
		super.onResume();
		adapterContextMenu.notifyDataSetChanged();
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		getMenuInflater().inflate(R.menu.list_context, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		//Mileage selectedItem = adapterContextMenu.getItem(position);
		
		switch(item.getItemId()){
		case R.id.delete:
			Log.d(TAG, "delete item");
			//adapterContextMenu.notifyDataSetChanged();
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	
}

