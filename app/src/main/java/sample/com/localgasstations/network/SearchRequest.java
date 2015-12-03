package sample.com.localgasstations.network;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import sample.com.localgasstations.data.RequestListener;
import sample.com.localgasstations.data.SearchResultData;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchRequest extends AsyncTask<Void, Void, SearchResultData> {

	private Context mContext;
	private RequestListener mRequestListener;
	private String mCityName;

	public SearchRequest (Context context, String cityName) {
		mContext = context;
		mRequestListener = (RequestListener) mContext;
		mCityName = cityName;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected SearchResultData doInBackground(Void... params) {

		Yelp yelp = Yelp.getYelp(mContext);
		String response = yelp.searchGasStations("Gas Stations", mCityName);
		Gson gson = new Gson();

		SearchResultData result = gson.fromJson(response, SearchResultData.class);

		return result;
	}


	@Override
	protected void onPostExecute(SearchResultData result) {
		mRequestListener.onSuccess(result);
	}
}
