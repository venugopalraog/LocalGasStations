package sample.com.localgasstations.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import sample.com.localgasstations.data.SearchResultData;

/**
 * Created by Vgubbala on 12/2/15.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.DataObjectHolder>  {

	private ItemClickListener mItemClickListener = null;

	private Context mContext;
	private SearchResultData mSearchResultData;


	public interface ItemClickListener {
		public void onItemClick(int position, View v);
	}

	SearchResultAdapter (Context context, SearchResultData data) {
		mContext = context;
		mSearchResultData = data;
	}

	public void setOnItemClickListener(ItemClickListener listener) {
		mItemClickListener = listener;
	}


	public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public DataObjectHolder(View itemView) {
			super(itemView);
		}

		@Override
		public void onClick(View v) {

		}
	}

	@Override
	public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(DataObjectHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return mSearchResultData.businesses.size();
	}
}
