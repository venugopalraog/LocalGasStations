package sample.com.localgasstations.data;

/**
 * Created by Vgubbala on 12/17/15.
 */
public class StationSearchFilter {

	private String mTerm = null;
	private String mLocation = null;
	private Integer mNumOfResults = 20;
	private Integer mRadius = 1000;
	private Integer mStartingResult = 0;

	public String getTerm() {
		return mTerm;
	}

	public void setTerm(String term) {
		this.mTerm = term;
	}

	public String getLocation() {
		return mLocation;
	}

	public void setLocation(String location) {
		this.mLocation = location;
	}

	public Integer getNumOfResults() {
		return mNumOfResults;
	}

	public void setNumOfResults(Integer numOfResults) {
		this.mNumOfResults = numOfResults;
	}

	public Integer getRadius() {
		return mRadius;
	}

	public void setRadius(Integer radius) {
		this.mRadius = radius;
	}

	public Integer getStartingResult() {
		return mStartingResult;
	}

	public void setStartingResult(Integer startingResult) {
		this.mStartingResult = startingResult;
	}
}
