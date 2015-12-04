package sample.com.localgasstations.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import sample.com.localgasstations.R;

public class SearchActivity extends AppCompatActivity {

	private static final String TAG = "SearchActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.button:
				Log.d(TAG, "onClick -- button");
				Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
				EditText cityET = (EditText) findViewById(R.id.city_name);
				EditText stationET = (EditText) findViewById(R.id.station_name);

				intent.putExtra("CityName", cityET.getText().toString());
				intent.putExtra("StationName", stationET.getText().toString());
				startActivity(intent);
				break;
		}
	}
}
