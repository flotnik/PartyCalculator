package com.partycalc

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.partycalc.database.Participant

/**
 * [RecyclerView.Adapter] that can display a [Party] and makes a call to the
 * specified [].
 * TODO: Replace the implementation with code for your data type.
 */
class PartyParticipantsRecyclerViewAdapter(private var mValues: List<Participant>, private val onClickListener: View.OnClickListener, private val longClickListener: OnLongClickListener) : androidx.recyclerview.widget.RecyclerView.Adapter<PartyParticipantsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.party_participants_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val borrowModel = mValues[position]
        holder.mItem = mValues[position]
        var item_text = mValues[position].name
        if (mValues[position].size > 1) {
            item_text += " (" + mValues[position].size + ")"
        }
        holder.nameView.text = item_text
        holder.itemView.tag = borrowModel
        holder.itemView.setOnClickListener(onClickListener)
        holder.itemView.setOnLongClickListener(longClickListener)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    fun addItems(items: List<Participant>) {
        mValues = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        val nameView: TextView
        var mItem: Participant? = null
        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }

        init {
            nameView = mView.findViewById(R.id.participant_name)
        }
    }

}