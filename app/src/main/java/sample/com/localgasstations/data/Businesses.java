package sample.com.localgasstations.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class Businesses {

	@SerializedName("rating")
	public float rating;

    @SerializedName("rating_img_url")
    public String ratingImageUrl;

	@SerializedName("name")
	public String name;

	@SerializedName("phone")
	public String phone;

	@SerializedName("image_url")
	public String imageUrl;

	@SerializedName("snippet_text")
	public String snippet_text;

	@SerializedName("location")
	public Location location;

	@SerializedName("display_phone")
	public String display_phone;

}
