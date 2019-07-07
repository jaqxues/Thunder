package com.jaqxues.thunder.uicomponents


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaqxues.thunder.R
import kotlinx.android.synthetic.main.fragment_labresults_item.view.*

class LabResultsItemRecyclerViewAdapter(
    private val mValues: List<LabEntryContent.Entry>
) : RecyclerView.Adapter<LabResultsItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_labresults_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.name.text = item.name
        holder.value.text = "${item.valuePq.value}${item.valuePq.unit}"
        holder.min.text = if (item.low != null) "Min: ${item.low.value}${item.low.unit}" else "No Min Value"
        holder.max.text = if (item.high != null) "Max: ${item.high.value}${item.high.unit}" else "No Max Value"

        var warn = false
        if (item.low != null && item.valuePq.value < item.low.value)
            warn = true
        if (item.high != null && item.valuePq.value > item.high.value)
            warn = true

        if (warn) {
            holder.mView.card_item.setCardBackgroundColor(Color.parseColor("#ffb8b8"))
        }

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
