package sample.com.localgasstations.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchResultData {

	@SerializedName("total")
	public int total;

	@SerializedName("businesses")
	public List<Businesses> businesses;
}
