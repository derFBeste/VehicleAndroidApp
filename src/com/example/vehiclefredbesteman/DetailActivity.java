package com.example.vehiclefredbesteman;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends Activity{
	
	private static final String TAG = "***FB***";


	Button buttonDate;
	EditText editPrice;
	EditText editGallons;
	EditText editMiles;
	String timeStamp;
	
	Mileage mileage;
	MileageDB db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		buttonDate = (Button) findViewById(R.id.buttonDate);
		editPrice = (EditText) findViewById(R.id.editPrice);
		editGallons = (EditText) findViewById(R.id.editGallons);
		editMiles = (EditText) findViewById(R.id.editMiles);
		
		db = new MileageDB(this);
		
		mileage = new Mileage(0, "", 0.0, 0.0, 0.0);
		timeStamp = new SimpleDateFormat("MMM d, yyyy h:mm a").format(Calendar.getInstance().getTime());
		Log.d(TAG, "time: " + timeStamp);
		mileage.setDate(timeStamp);

		buttonDate.setText(timeStamp);
		
		editPrice.addTextChangedListener(new MyTextWatcher());
		editGallons.addTextChangedListener(new MyTextWatcher());
		editMiles.addTextChangedListener(new MyTextWatcher());
		
		DateButtonListener buttonListener = new DateButtonListener(); 
		buttonDate.setOnClickListener(buttonListener);
		
		
		
	}// end OnCreate

	class MyTextWatcher implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			Log.d(TAG, "onTextChanged");
			//Double p = Double.parseDouble(editPrice.getText().toString());
			
			if(editPrice.hasFocus()){
				mileage.setPrice(Double.parseDouble(editPrice.getText().toString()));
			}
			if(editGallons.hasFocus()){
				mileage.setGallons(Double.parseDouble(editGallons.getText().toString()));				
			}
			if(editMiles.hasFocus()){
				mileage.setMiles(Double.parseDouble(editMiles.getText().toString()));				
			}

			Log.d(TAG, "price" + mileage.getPrice().toString());
			Log.d(TAG, "gallons" + mileage.getGallons().toString());
			Log.d(TAG, "miles" + mileage.getMiles().toString());


		}			
	}//end MyTextWatcher
	
	class DateButtonListener implements OnClickListener{
		@Override
		public void onClick(View v){
//			long id = mileage.generateId();
//			mileage.setId(id);
			Log.d(TAG, mileage.toString());
			//db.insertMileageAutoId(mileage);
			long id = db.insertMileageAutoId(mileage);
		}
		
	}
	
}//end DetailActivity
