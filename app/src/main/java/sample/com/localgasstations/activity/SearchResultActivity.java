package sample.com.localgasstations.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import sample.com.localgasstations.R;
import sample.com.localgasstations.data.RequestListener;
import sample.com.localgasstations.data.SearchResultData;
import sample.com.localgasstations.network.SearchRequest;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchResultActivity extends Activity implements RequestListener{

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private SearchResultData mResultData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);

		mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(mLayoutManager);

		new SearchRequest(SearchResultActivity.this, "Tustin, CA").execute();
	}

	@Override
	protected void onResume() {
		super.onResume();
/*		mAdapter = new SearchResultAdapter(this, mResultData);
		mRecyclerView.setAdapter(mAdapter);
		RecyclerView.ItemDecoration itemDecoration =
				new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL);
		mRecyclerView.addItemDecoration(itemDecoration);*/
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
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mAdapter = new SearchResultAdapter(SearchResultActivity.this, mResultData);
				mRecyclerView.setAdapter(mAdapter);
				RecyclerView.ItemDecoration itemDecoration =
						new DividerItemDecoration(SearchResultActivity.this, LinearLayoutManager.HORIZONTAL);
				mRecyclerView.addItemDecoration(itemDecoration);

			}
		});
	}
}
