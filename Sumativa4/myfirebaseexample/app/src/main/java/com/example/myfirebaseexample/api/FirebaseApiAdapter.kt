package com.example.myfirebaseexample.api

import com.example.myfirebaseexample.api.response.PostResponse
import com.example.myfirebaseexample.api.response.VisitaResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirebaseApiAdapter {
    private var URL_BASE = "https://visitaclientes-b54a7-default-rtdb.firebaseio.com/"
    private val firebaseApi = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getVisitas(rut: String): VisitaResponse? {
        val call = firebaseApi.create(FirebaseApi::class.java).getVisitas(rut).execute()
        val visitas = call.body()
        visitas?.rut = rut
        return visitas
    }

    fun setVisita(visita: VisitaResponse): PostResponse? {
        val call = firebaseApi.create(FirebaseApi::class.java).setVisita(visita).execute()
        val results = call.body()
        return results
    }
}