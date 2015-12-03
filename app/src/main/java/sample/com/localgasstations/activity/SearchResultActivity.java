package sample.com.localgasstations.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import sample.com.localgasstations.R;
import sample.com.localgasstations.adapters.SearchResultAdapter;
import sample.com.localgasstations.data.RequestListener;
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


        //Start AsyncTask to fetch the Gas Station from Yelp Server
		new SearchRequest(SearchResultActivity.this, "Tustin, CA").execute();
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
//				mRecyclerView.setVisibility(View.VISIBLE);
				mSearchResultAdapter.setSearchResultData(mResultData);
				mSearchResultAdapter.notifyDataSetChanged();
			}
		});

	}
}
