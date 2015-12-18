package sample.com.localgasstations.network;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;

import sample.com.localgasstations.data.SearchResultData;
import sample.com.localgasstations.data.StationSearchFilter;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchRequest {

	private Context mContext;


	public SearchRequest (Context context) {
		mContext = context;
	}

	public SearchResultData request(StationSearchFilter stationSearchFilter) {
		Yelp yelp = Yelp.getYelp(mContext);
		String response = yelp.searchGasStations(stationSearchFilter);

		/*Parse the Json String into Class Objects*/
		Gson gson = new Gson();
		return gson.fromJson(response, SearchResultData.class);

	}

	public void requestLL(StationSearchFilter stationSearchFilter) {
		Yelp yelp = Yelp.getYelp(mContext);
		String response = yelp.searchGasStations(stationSearchFilter);

		/*Parse the Json String into Class Objects*/
		Gson gson = new Gson();
		SearchResultData result = gson.fromJson(response, SearchResultData.class);
	}
}
