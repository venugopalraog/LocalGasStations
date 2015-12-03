package sample.com.localgasstations.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sample.com.localgasstations.R;
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

    public void setSearchResultData (SearchResultData searchResultData) {
        mSearchResultData = searchResultData;
    }
	public SearchResultAdapter(Context context, SearchResultData data) {
		mContext = context;
		mSearchResultData = data;
	}

	public void setOnItemClickListener(ItemClickListener listener) {
		mItemClickListener = listener;
	}

	public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView mBusinessAddress;
        TextView mBusinessName;
        ImageView mBusinessPhoto;
        ImageView mBusinessRatings;
		public DataObjectHolder(View itemView) {
			super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            mBusinessName = (TextView) itemView.findViewById(R.id.business_name);
            mBusinessAddress = (TextView) itemView.findViewById(R.id.business_address);
            mBusinessPhoto = (ImageView) itemView.findViewById(R.id.business_photo);
            mBusinessRatings = (ImageView) itemView.findViewById(R.id.business_score);
        }

		@Override
		public void onClick(View v) {

		}
	}

	@Override
	public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false);
        DataObjectHolder viewHolder = new DataObjectHolder(v);
        return viewHolder;
	}

	@Override
	public void onBindViewHolder(DataObjectHolder holder, int position) {
        //Load Rating Image to List Item
        Picasso.with(mContext)
                .load(mSearchResultData.businesses.get(position).ratingImageUrl)
                .into(holder.mBusinessRatings);
        Picasso.with(mContext)
                .load(mSearchResultData.businesses.get(position).imageUrl)
                .into(holder.mBusinessPhoto);

        holder.mBusinessAddress.setText(mSearchResultData.businesses.get(position).snippet_text);

        holder.mBusinessName.setText(mSearchResultData.businesses.get(position).name);

	}

	@Override
	public int getItemCount() {
		if (mSearchResultData == null) {
			return 0;
		}
		return mSearchResultData.businesses.size();
	}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}