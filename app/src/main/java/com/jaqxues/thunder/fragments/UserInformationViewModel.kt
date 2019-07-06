package com.jaqxues.thunder.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaqxues.thunder.api.ApiService
import com.jaqxues.thunder.packets.PatientSummary
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserInformationViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val apiService: ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://lts-cns-data.herokuapp.com")
        .build()
        .create(ApiService::class.java)

    val patientSummary = MutableLiveData<PatientSummary>()


    fun refreshItems() {
        ioScope.launch {
            apiService.patientSummary().body()?.let {
                withContext(Dispatchers.Main) {
                    patientSummary.value = it
                    Companion.patientSummary = it
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        lateinit var patientSummary: PatientSummary
    }
}
