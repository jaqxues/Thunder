package com.jaqxues.thunder.uicomponents

import com.jaqxues.thunder.fragments.UserInformationViewModel
import java.util.ArrayList

object LabEntryContent {

    val ITEMS: MutableList<Entry> = ArrayList()

    init {
        // Add some sample items.
        for (i in UserInformationViewModel.labResults[0].entries) {
            ITEMS.add(
                Entry(
                    i.code.displayName,
                    "${i.value_PQ.value}${i.value_PQ.unit}",
                    if (i.value_IVL_PQ.low != null) "${i.value_IVL_PQ.low.value}${i.value_IVL_PQ.low.unit}" else "No Min Value",
                    if (i.value_IVL_PQ.high != null) "${i.value_IVL_PQ.high.value}${i.value_IVL_PQ.high.unit}" else "No Max Value"
                )
            )
        }
    }

    data class Entry(val name: String, val value: String, val min: String, val max: String)
}
