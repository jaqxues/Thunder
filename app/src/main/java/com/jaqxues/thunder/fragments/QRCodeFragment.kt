package com.jaqxues.thunder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.MedItemContent
import com.jaqxues.thunder.uicomponents.MyQRCodeItemRecyclerViewAdapter

class QRCodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qrcode_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyQRCodeItemRecyclerViewAdapter(MedItemContent.ITEMS)
            }
        }
        return view
    }


}
