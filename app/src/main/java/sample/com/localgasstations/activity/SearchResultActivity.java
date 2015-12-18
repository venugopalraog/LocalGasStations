package sample.com.localgasstations.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import sample.com.localgasstations.R;
import sample.com.localgasstations.adapters.SearchResultAdapter;
import sample.com.localgasstations.data.StationSearchFilter;
import sample.com.localgasstations.loaders.StationLoaders;
import sample.com.localgasstations.data.SearchResultData;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchResultActivity extends Activity implements View.OnClickListener{

	private RecyclerView mRecyclerView;
	private SearchResultAdapter mSearchResultAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private SearchResultData mResultData = null;

	private ProgressBar mProgressBar = null;

	private StationSearchFilter mStationSearchFilter = new StationSearchFilter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);

		//Get the CityName and Station Name
		Intent intent = getIntent();
		mStationSearchFilter.setLocation(intent.getStringExtra("CityName"));
		mStationSearchFilter.setTerm(intent.getStringExtra("StationName"));

		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

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

		getLoaderManager().initLoader(R.id.loader_id, null, loaderCallbacks);
	}

	//LoaderCallbacks to listen to status from StationLoader class
	private  LoaderManager.LoaderCallbacks<SearchResultData> loaderCallbacks = new LoaderManager.LoaderCallbacks<SearchResultData>() {

		@Override
		public android.content.Loader<SearchResultData> onCreateLoader(int id, Bundle args) {
			mProgressBar.setVisibility(View.VISIBLE);
			return new StationLoaders(getApplicationContext(), mStationSearchFilter);
		}

		@Override
		public void onLoadFinished(android.content.Loader<SearchResultData> loader, SearchResultData data) {
			if (data != null) {
				mSearchResultAdapter.setSearchResultData(data);
				mSearchResultAdapter.notifyDataSetChanged();
				mProgressBar.setVisibility(View.GONE);
			} else {
				mProgressBar.setVisibility(View.GONE);
			}
		}

		@Override
		public void onLoaderReset(android.content.Loader<SearchResultData> loader) {
			mProgressBar.setVisibility(View.GONE);
		}
	};

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(StationLoaders.ACTION);
		LocalBroadcastManager.getInstance(SearchResultActivity.this).sendBroadcast(intent);

	}
}
