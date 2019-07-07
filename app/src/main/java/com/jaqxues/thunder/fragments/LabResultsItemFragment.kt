package com.jaqxues.thunder.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaqxues.thunder.R
import com.jaqxues.thunder.uicomponents.LabEntryContent
import com.jaqxues.thunder.uicomponents.LabResultsItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_labresults_item_list.view.*

class LabResultsItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_labresults_item_list, container, false)

        with(view.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = LabResultsItemRecyclerViewAdapter(LabEntryContent.ITEMS)
        }

        view.arrow_left.setOnClickListener {
            modDataSet(view) {
                LabEntryContent.previous()
            }
        }

        view.arrow_right.setOnClickListener {
            modDataSet(view) {
                LabEntryContent.next()
            }
        }

        view.arrow_left.imageAlpha = 70
        setTitle(view.title_lab_results)
        return view
    }

    @SuppressLint("SetTextI18n")
    fun setTitle(title: TextView) {
        title.text = "Lab Results from ${LabEntryContent.ITEMS[0].date}"
    }

    private inline fun modDataSet(view: View, modFun: () -> Unit) {
        modFun()
        view.list.adapter!!.notifyDataSetChanged()

        view.arrow_left.imageAlpha =
            if (LabEntryContent.hasPrevious) 255 else 70

        view.arrow_right.imageAlpha =
            if (LabEntryContent.hasNext) 255 else 70


        setTitle(view.title_lab_results)
    }
}
