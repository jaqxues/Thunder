package com.jaqxues.thunder.uicomponents

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.jaqxues.thunder.R
import java.util.*
import kotlin.random.Random

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object TransactionContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<TransActionItem> = ArrayList()

    init {
        fun randomParticipant(): Participant {
            return when (Random.nextInt() % 4) {
                0 -> Participant.DOCTOR
                1 -> Participant.PHARMACY
                else -> Participant.LAB
            }
        }

        fun randomName(participant: Participant): String {
            return when (participant) {
                Participant.DOCTOR -> "Dr. Phil"
                Participant.PHARMACY -> "Pharmacy \"Am Wenkel\""
                Participant.LAB -> "Hospital Sankt Markus"
            }
        }
        var day = 1
        var month = 1
        var year = 2019

        for (i in 1..25) {
            day += Random.nextInt(3, 15)
            while (day > 28) {
                day -= 18
                month += 1
            }
            if (month > 12) {
                month -= 12
                year += 1
            }
            val participant = randomParticipant()
            ITEMS.add(
                TransActionItem(
                    participant,
                    randomName(participant),
                    "${Random.nextInt(15, 50)},${Random.nextInt(0, 100).buffer0()}€",
                    "$day.${month.buffer0()}.$year"
                )
            )
        }
    }

    fun Int.buffer0(): String {
        val string = toString()
        if (string.length == 1)
            return "0$string"
        return string
    }

    data class TransActionItem(val participant: Participant, val name: String, val amount: String, val date: String)

    enum class Participant(@RawRes @DrawableRes val id: Int) {
        DOCTOR(R.drawable.ic_trans_doctor), PHARMACY(R.drawable.ic_trans_pharmacy), LAB(R.drawable.ic_trans_lab)
    }
}
