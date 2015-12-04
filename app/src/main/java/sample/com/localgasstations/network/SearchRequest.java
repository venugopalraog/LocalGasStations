package sample.com.localgasstations.network;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;

import sample.com.localgasstations.data.SearchResultData;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchRequest {

	private Context mContext;
	private RequestListener mRequestListener;


	public SearchRequest (Context context) {
		mContext = context;
		mRequestListener = (RequestListener) mContext;
	}

	public void request(String term, String location) {
		Yelp yelp = Yelp.getYelp(mContext);
		String response = yelp.searchGasStations(term, location);

		/*Parse the Json String into Class Objects*/
		Gson gson = new Gson();
		SearchResultData result = gson.fromJson(response, SearchResultData.class);

		mRequestListener.onSuccess(result);
	}

	public void requestLL(String term, String location) {
		Yelp yelp = Yelp.getYelp(mContext);
		String response = yelp.searchGasStations(term, location);

		/*Parse the Json String into Class Objects*/
		Gson gson = new Gson();
		SearchResultData result = gson.fromJson(response, SearchResultData.class);

		mRequestListener.onSuccess(result);

	}
}
