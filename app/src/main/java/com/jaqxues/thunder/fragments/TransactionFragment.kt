package com.jaqxues.thunder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.TransactionContent
import com.jaqxues.thunder.uicomponents.TransactionItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_qrcode_item_list.view.*

class TransactionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_item_list, container, false)

        // Set the adapter
        with(view.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = TransactionItemRecyclerViewAdapter(TransactionContent.ITEMS)
        }
        return view
    }
}
