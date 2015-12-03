package sample.com.localgasstations.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vgubbala on 12/3/15.
 */
public class Location {

	@SerializedName("city")
	public String city;

	@SerializedName("display_address")
	public ArrayList<String> displayAddress;
}
