package com.jaqxues.thunder.fragments

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jaqxues.thunder.packets.LabResults
import com.jaqxues.thunder.packets.PatientSummary
import java.io.IOException
import java.io.InputStream


class UserInformationViewModel : ViewModel() {

    val patientSummary = MutableLiveData<PatientSummary>()

    fun inputStreamToString(inputStream: InputStream): String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            null
        }

    }

    fun refreshItems(activity: Activity) {
        val patientSummaryTmp =
            Gson().fromJson(
                inputStreamToString(activity.assets.open("patientsummary.json")),
                PatientSummary::class.java
            )
        patientSummary.value = patientSummaryTmp
        Companion.patientSummary = patientSummaryTmp

        labResults =
            Gson().fromJson(
                inputStreamToString(activity.assets.open("labresults.json")),
                object : TypeToken<List<LabResults>>() {}.type
            )
    }

    companion object {
        lateinit var patientSummary: PatientSummary
        lateinit var labResults: List<LabResults>
    }
}
