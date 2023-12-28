package com.example.myfirebaseexample.api

import com.example.myfirebaseexample.api.response.PostResponse
import com.example.myfirebaseexample.api.response.VisitaResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FirebaseApi {
    @GET("Visitas.json")
    fun getVisitas(): Call<MutableMap<String, VisitaResponse>>

    @GET("visitas/{rut}.json")
    fun getVisitas(
        @Path("rut") rut: String
    ): Call<VisitaResponse>

    @POST("Visitas.json")
    fun setVisita(
        @Body() body: VisitaResponse
    ): Call<PostResponse>
}