package com.jaqxues.thunder.api

import com.jaqxues.thunder.packets.PatientSummary
import com.jaqxues.thunder.packets.LabResults
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/patientsummary")
    suspend fun patientSummary(): Response<PatientSummary>

    @GET("/labresults")
    suspend fun labResults(): Response<LabResults>
}