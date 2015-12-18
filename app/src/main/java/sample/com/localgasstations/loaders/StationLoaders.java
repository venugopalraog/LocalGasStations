package sample.com.localgasstations.loaders;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import sample.com.localgasstations.data.SearchResultData;
import sample.com.localgasstations.data.StationSearchFilter;
import sample.com.localgasstations.network.SearchRequest;

/**
 * Created by Vgubbala on 12/17/15.
 */
public class StationLoaders extends AsyncTaskLoader<SearchResultData> {

	public static final String ACTION = "com.stationloaders.NewList";

	StationSearchFilter mStationSearchFilter = null;
	private SearchResultData mSearchResultData = null;

	public StationLoaders(Context context, StationSearchFilter stationSearchFilter) {
		super(context);
		mStationSearchFilter = stationSearchFilter;
	}

	@Override
	protected void onStartLoading() {

		LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getContext());
		IntentFilter filter = new IntentFilter(ACTION);
		manager.registerReceiver(broadcastReceiver, filter);

		if (mSearchResultData == null) {
			forceLoad();
		} else {
			super.deliverResult(mSearchResultData);
		}
	}

	@Override
	public SearchResultData loadInBackground() {
		SearchRequest searchRequest= new SearchRequest(getContext());
		return searchRequest.request(mStationSearchFilter);
	}

	@Override
	public void deliverResult(SearchResultData data) {
		mSearchResultData = data;
		super.deliverResult(data);

	}

	@Override
	protected void onReset() {
		super.onReset();
		LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			forceLoad();
		}
	};


}
