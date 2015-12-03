package sample.com.localgasstations.data;

/**
 * Created by Vgubbala on 12/2/15.
 */
public interface RequestListener {

	void onNetworkDown(String error);
	void onResponseErrorReceived(String error_content, String error_id);
	void onExceptionOccurred(Exception error);
	void onSuccess(SearchResultData data);

}
