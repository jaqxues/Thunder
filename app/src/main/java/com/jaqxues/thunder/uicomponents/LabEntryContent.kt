package com.jaqxues.thunder.uicomponents

import com.jaqxues.thunder.fragments.UserInformationViewModel
import com.jaqxues.thunder.packets.labresgen.High
import com.jaqxues.thunder.packets.labresgen.Low
import com.jaqxues.thunder.packets.labresgen.Value_PQ
import java.util.ArrayList

object LabEntryContent {

    val ITEMS: MutableList<Entry> = ArrayList()

    init {
        // Add some sample items.
        for (i in UserInformationViewModel.labResults[0].entries) {
            ITEMS.add(
                Entry(
                    i.code.displayName,
                    i.value_PQ,
                    i.value_IVL_PQ.low,
                    i.value_IVL_PQ.high,
                    i.effectiveTime.value.formatyyyymmdd()
                )
            )
        }
    }

    private fun Int.formatyyyymmdd(): String {
        val string = toString()
        return "${string.subSequence(6, 8)}.${string.subSequence(4, 6)}.${string.substring(0, 4)}"
    }

    data class Entry(val name: String, val valuePq: Value_PQ, val low: Low?, val high: High?, val date: String)
}
