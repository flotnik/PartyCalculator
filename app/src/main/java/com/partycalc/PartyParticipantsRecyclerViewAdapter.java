package com.partycalc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.partycalc.database.Participant;
import com.partycalc.database.Party;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Party} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class PartyParticipantsRecyclerViewAdapter extends RecyclerView.Adapter<PartyParticipantsRecyclerViewAdapter.ViewHolder> {

    private List<Participant> mValues;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener longClickListener;

    public PartyParticipantsRecyclerViewAdapter(List<Participant> items, View.OnClickListener onClickListener, View.OnLongClickListener longClickListener) {
        this.mValues = items;
        this.onClickListener = onClickListener;
        this.longClickListener = longClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.party_participants_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Participant borrowModel = mValues.get(position);
        holder.mItem = mValues.get(position);
        String item_text = mValues.get(position).getName();
        if (mValues.get(position).getSize() > 1) {
            item_text += " (" + mValues.get(position).getSize() + ")";
        }
        holder.nameView.setText(item_text);
        holder.itemView.setTag(borrowModel);
        holder.itemView.setOnClickListener(onClickListener);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItems(List<Participant> items) {
        this.mValues = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameView;
        public Participant mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            nameView = view.findViewById(R.id.participant_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameView.getText() + "'";
        }
    }
}
