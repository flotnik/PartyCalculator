package com.partycalc;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.partycalc.database.Party;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Party} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class PartyRecyclerViewAdapter extends RecyclerView.Adapter<PartyRecyclerViewAdapter.ViewHolder> {

    private List<Party> mValues;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener longClickListener;

    public PartyRecyclerViewAdapter(List<Party> items, View.OnClickListener onClickListener, View.OnLongClickListener longClickListener) {
        this.mValues = items;
        this.onClickListener = onClickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_party, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Party borrowModel = mValues.get(position);
        holder.mItem = mValues.get(position);
        holder.nameView.setText(mValues.get(position).getName());
        holder.dateView.setText(new SimpleDateFormat("yyyy.MM.dd").format(mValues.get(position).getDate()));
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnClickListener(onClickListener);
        holder.itemView.setOnLongClickListener(longClickListener);

/*        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickListener) {
                    // Notify the active callbacks interface (the activity, if the fragment is attached to one) that an item has been selected.
                    onClickListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItems(List<Party> items){
        this.mValues = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView dateView;
        public final TextView nameView;
        public Party mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            dateView = view.findViewById(R.id.party_date);
            nameView = view.findViewById(R.id.party_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }
}
