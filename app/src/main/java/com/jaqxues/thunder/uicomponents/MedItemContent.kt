package com.jaqxues.thunder.uicomponents

import com.jaqxues.thunder.fragments.UserInformationViewModel
import java.util.ArrayList

object MedItemContent {

    val ITEMS: MutableList<MedItem> = ArrayList()

    init {
        // Add some sample items.
        UserInformationViewModel.patientSummary.substanceAdministration.let {
            for (i in it.indices) {
                ITEMS.add(MedItem("${it[i].doseQuantity}* ${it[i].consumable.name}", "Possible side effects: Headache"))
            }
        }
    }

    data class MedItem(val title: String, val description: String) {
        override fun toString(): String = description
    }
}
