package ru.fedosov.insta.ui.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.fedosov.insta.R;
import ru.fedosov.insta.model.InstaData;

/**
 * Created by fedosov on 9/7/16.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<InstaData> mDataset;


    public void setData(List<InstaData> data) {
        this.mDataset = data;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
            TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView =(TextView) v.findViewById(R.id.item_recycler_view_text_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<InstaData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getLogin());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}