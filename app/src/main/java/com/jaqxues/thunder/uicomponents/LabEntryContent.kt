package com.jaqxues.thunder.uicomponents

import com.jaqxues.thunder.fragments.UserInformationViewModel
import com.jaqxues.thunder.packets.labresgen.Entries
import com.jaqxues.thunder.packets.labresgen.High
import com.jaqxues.thunder.packets.labresgen.Low
import com.jaqxues.thunder.packets.labresgen.Value_PQ
import java.util.*

object LabEntryContent {

    val ITEMS: MutableList<Entry> = ArrayList()

    var currentIndex = 0
    val hasNext
        get() = UserInformationViewModel.labResults.size - 1 > currentIndex
    val hasPrevious
        get() = currentIndex > 0

    init {
        // Add some sample items.
        fromEntries(UserInformationViewModel.labResults[currentIndex].entries)
    }

    private fun fromEntries(entries: List<Entries>) {
        ITEMS.clear()
        for (i in entries) {
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

    fun previous() {
        if (!hasPrevious)
            return
        fromEntries(UserInformationViewModel.labResults[--currentIndex].entries)
    }

    fun next() {
        if (!hasNext)
            return
        fromEntries(UserInformationViewModel.labResults[++currentIndex].entries)
    }

    private fun Int.formatyyyymmdd(): String {
        val string = toString()
        return "${string.subSequence(6, 8)}.${string.subSequence(4, 6)}.${string.substring(0, 4)}"
    }

    data class Entry(val name: String, val valuePq: Value_PQ, val low: Low?, val high: High?, val date: String)
}
