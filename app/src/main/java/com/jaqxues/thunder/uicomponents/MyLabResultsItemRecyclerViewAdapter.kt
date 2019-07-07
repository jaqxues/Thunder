package com.jaqxues.thunder.uicomponents


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaqxues.thunder.R
import kotlinx.android.synthetic.main.fragment_labresults_item.view.*

class MyLabResultsItemRecyclerViewAdapter(
    private val mValues: List<LabEntryContent.Entry>
) : RecyclerView.Adapter<MyLabResultsItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_labresults_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.name.text = item.name
        holder.value.text = item.value
        holder.min.text = item.min
        holder.max.text = item.max

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val name: TextView = mView.entry_name
        val value: TextView = mView.entry_value
        val min: TextView = mView.min_value
        val max: TextView = mView.max_value
    }
}
