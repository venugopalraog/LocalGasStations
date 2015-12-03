package sample.com.localgasstations.network;

/**
 * Created by Vgubbala on 12/1/15.
 */

import android.content.Context;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.net.HttpURLConnection;

import sample.com.localgasstations.R;


public class Yelp {


	OAuthService service;
	Token accessToken;


	public static Yelp getYelp(Context context) {
		return new Yelp(context.getString(R.string.consumer_key), context.getString(R.string.consumer_secret),
				context.getString(R.string.token), context.getString(R.string.token_secret));
	}


	/**
	 * Setup the Yelp API OAuth credentials.
	 *
	 * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
	 *
	 * @param consumerKey Consumer key
	 * @param consumerSecret Consumer secret
	 * @param token Token
	 * @param tokenSecret Token secret
	 */
	public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
	}


	/**
	 * Search with term and location.
	 *
	 * @param term Search term
	 * @param latitude Latitude
	 * @param longitude Longitude
	 * @return JSON string response
	 */
	public String search(String term, double latitude, double longitude) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("ll", latitude + "," + longitude);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}


	/**
	 * Search with term string location.
	 *
	 * @param term Search term
	 * @param latitude Latitude
	 * @param longitude Longitude
	 * @return JSON string response
	 */
	public String search(String term, String location) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}

	public String searchGasStations(String term, String location) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.yelp.com/v2/search/?category_filter=servicestations");
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		if (response.getCode() != HttpURLConnection.HTTP_OK) {
			return null;
		}
		return response.getBody();
	}
}