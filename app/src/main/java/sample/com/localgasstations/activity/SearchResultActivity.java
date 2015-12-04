package sample.com.localgasstations.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import sample.com.localgasstations.R;
import sample.com.localgasstations.adapters.SearchResultAdapter;
import sample.com.localgasstations.network.RequestListener;
import sample.com.localgasstations.data.SearchResultData;
import sample.com.localgasstations.network.SearchRequest;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchResultActivity extends Activity implements RequestListener{

	private RecyclerView mRecyclerView;
	private SearchResultAdapter mSearchResultAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private SearchResultData mResultData = null;

	private ProgressBar mProgressBar = null;

	private String mCityName = null;
	private String mStationName = null;

	private Thread mRequestThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);

		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mProgressBar.setVisibility(View.VISIBLE);

		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		mRecyclerView.setHasFixedSize(false);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);
		RecyclerView.ItemDecoration itemDecoration =
				new DividerItemDecoration(SearchResultActivity.this, LinearLayoutManager.HORIZONTAL);
		mRecyclerView.addItemDecoration(itemDecoration);

		//Create Adapter Instance and pass the Result data to it
        mSearchResultAdapter = new SearchResultAdapter(SearchResultActivity.this, mResultData);
		mRecyclerView.setAdapter(mSearchResultAdapter);

		//Get the CityName and Station Name
		Intent intent = getIntent();
		mCityName = intent.getStringExtra("CityName");
		mStationName = 	intent.getStringExtra("StationName");

		SearchResultRequest request = new SearchResultRequest();
		mRequestThread = new Thread(request);
		mRequestThread.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onNetworkDown(String error) {

	}

	@Override
	public void onResponseErrorReceived(String error_content, String error_id) {

	}

	@Override
	public void onExceptionOccurred(Exception error) {

	}

	@Override
	public void onSuccess(SearchResultData data) {
		mResultData = data;

		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				mProgressBar.setVisibility(View.GONE);
				mSearchResultAdapter.setSearchResultData(mResultData);
				mSearchResultAdapter.notifyDataSetChanged();
			}
		});

	}

	public class SearchResultRequest implements  Runnable {

		@Override
		public void run() {
			SearchRequest searchRequest= new SearchRequest(SearchResultActivity.this);
			searchRequest.request(mStationName, mCityName);
		}
	}
}
