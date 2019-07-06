package com.jaqxues.thunder.uicomponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.TransactionContent.TransActionItem
import kotlinx.android.synthetic.main.fragment_transaction_item.view.*

class MyTransactionItemRecyclerViewAdapter(
    private val mValues: List<TransActionItem>
) : RecyclerView.Adapter<MyTransactionItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.transName.text = item.id
        holder.transDate.text = item.content

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val transName: TextView = mView.transaction_name
        val transDate: TextView = mView.transaction_date
        val transAmount: TextView = mView.transaction_amount
        val transIcon: ImageView = mView.transaction_icon

        override fun toString(): String {
            return super.toString() + " '" + transDate.text + "'"
        }
    }
}
