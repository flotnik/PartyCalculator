package com.partycalc

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.partycalc.database.Party
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [Party] and makes a call to the
 * specified [].
 * TODO: Replace the implementation with code for your data type.
 */
class PartyRecyclerViewAdapter(private var mValues: List<Party>, private val onClickListener: View.OnClickListener, private val longClickListener: OnLongClickListener) : androidx.recyclerview.widget.RecyclerView.Adapter<PartyRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.party_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val borrowModel = mValues[position]
        holder.mItem = mValues[position]
        holder.nameView.text = mValues[position].name
        holder.dateView.text = SimpleDateFormat("yyyy.MM.dd").format(mValues[position].date)
        holder.itemView.tag = borrowModel
        holder.itemView.setOnClickListener(onClickListener)
        holder.itemView.setOnLongClickListener(longClickListener)

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

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun addItems(items: List<Party>) {
        mValues = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        val dateView: TextView = mView.findViewById(R.id.party_date)
        val nameView: TextView = mView.findViewById(R.id.party_name)
        var mItem: Party? = null
        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}