package sample.com.localgasstations.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

	public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView mBusinessAddress;
        TextView mBusinessName;
        ImageView mBusinessPhoto;
        ImageView mBusinessRatings;

		TextView mSnippetText;
		TextView mDisplayPhone;

		public static LinearLayout mPreviousLayout = null;

		public DataObjectHolder(View itemView) {
			super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            mBusinessName = (TextView) itemView.findViewById(R.id.business_name);
            mBusinessAddress = (TextView) itemView.findViewById(R.id.business_address);
            mBusinessPhoto = (ImageView) itemView.findViewById(R.id.business_photo);
            mBusinessRatings = (ImageView) itemView.findViewById(R.id.business_score);

			mSnippetText = (TextView) itemView.findViewById(R.id.snippet_text);
			mDisplayPhone = (TextView) itemView.findViewById(R.id.display_phone);

			cv.setOnClickListener(this);
        }

		@Override
		public void onClick(View v) {
			LinearLayout layout = (LinearLayout) v.findViewById(R.id.extended_item);

			if (layout.getVisibility() == View.GONE) {
				if (mPreviousLayout != null)
					mPreviousLayout.setVisibility(View.GONE);
				layout.setVisibility(View.VISIBLE);
			} else {
				layout.setVisibility(View.GONE);
			}

			mPreviousLayout = layout;
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

        holder.mBusinessAddress.setText(mSearchResultData.businesses.get(position).location.displayAddress.get(0) + mSearchResultData.businesses.get(position).location.displayAddress.get(1));

        holder.mBusinessName.setText(mSearchResultData.businesses.get(position).name);
		holder.mDisplayPhone.setText("Phone Number: " + mSearchResultData.businesses.get(position).display_phone);
		holder.mSnippetText.setText("Description: " + mSearchResultData.businesses.get(position).snippet_text);
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