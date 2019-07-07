package com.jaqxues.thunder.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.LabEntryContent
import com.jaqxues.thunder.uicomponents.LabResultsItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_labresults_item_list.view.*

class LabResultsItemFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_labresults_item_list, container, false)

        with(view.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = LabResultsItemRecyclerViewAdapter(LabEntryContent.ITEMS)
        }

        view.title_lab_results.text = "Lab Results from ${LabEntryContent.ITEMS[0].date}"
        return view
    }
}
