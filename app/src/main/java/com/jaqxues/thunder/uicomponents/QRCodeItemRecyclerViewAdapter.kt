package com.jaqxues.thunder.uicomponents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.MedItemContent.MedItem
import kotlinx.android.synthetic.main.fragment_qrcode_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MedItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class QRCodeItemRecyclerViewAdapter(
    private val mValues: List<MedItem>
) : RecyclerView.Adapter<QRCodeItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_qrcode_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitle.text = item.title
        holder.mDesc.text = item.description
        Glide
            .with(holder.mQRImg)
            .load(getDrawable(position))
            .thumbnail(0.1f)
            .into(holder.mQRImg)
    }

    @RawRes
    @DrawableRes
    fun getDrawable(position: Int): Int {
        return when (position % 3) {
            0 -> R.drawable.qr_1
            1 -> R.drawable.qr_2
            2 -> R.drawable.qr_3
            else -> R.drawable.qr_1
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.med_item_title
        val mDesc: TextView = mView.med_item_desc
        val mQRImg: ImageView = mView.med_item_img

        override fun toString(): String {
            return super.toString() + " '" + mDesc.text + "'"
        }
    }
}
