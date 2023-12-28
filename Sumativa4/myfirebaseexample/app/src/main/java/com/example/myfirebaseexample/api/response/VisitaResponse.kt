package com.example.myfirebaseexample.api.response

import com.google.gson.annotations.SerializedName

data class VisitaResponse(
    @SerializedName("rut") var rut: String,
    @SerializedName("razonSocial") var razonSocial: String,
    @SerializedName("direccion") var direccion: String,
    @SerializedName("fechaVisita") var fechaVisita: String,
    @SerializedName("compra") var compra: String,
    @SerializedName("comentarios") var comentarios: String,

)
