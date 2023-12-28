package com.example.myfirebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.myfirebaseexample.api.FirebaseApiAdapter
import com.example.myfirebaseexample.api.response.VisitaResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    // Referenciar campos de las interfaz
    private lateinit var rutField: EditText
    private lateinit var razonSocialField: EditText
    private lateinit var direccionField: EditText
    private lateinit var fechaVisitaField: EditText
    private lateinit var compraField: EditText
    private lateinit var comentariosField: EditText
    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button

    // Referenciar la API
    private var firebaseApi = FirebaseApiAdapter()

    // Mantener los nombres e IDs de las armas
    private var visitaList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rutField = findViewById(R.id.rutField)
        razonSocialField = findViewById(R.id.razonSocialField)
        direccionField = findViewById(R.id.direccionField)
        fechaVisitaField = findViewById(R.id.fechaVisitaField)
        compraField = findViewById(R.id.compraField)
        comentariosField = findViewById(R.id.comentariosField)

        buttonLoad = findViewById(R.id.buttonLoad)
        buttonLoad.setOnClickListener {
            Toast.makeText(this, "Cargando información", Toast.LENGTH_SHORT).show()
            runBlocking {
                getVisitaFromApi()
            }
        }

        buttonSave = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            Toast.makeText(this, "Guardando información", Toast.LENGTH_SHORT).show()
            runBlocking {
                sendVisitaToApi()
            }
        }

        runBlocking {
            //populateRutField()
        }
    }

    /*private suspend fun populateIdSpinner() {
        val response = GlobalScope.async(Dispatchers.IO) {
            firebaseApi.getVisitas()
        }
        val visitas = response.await()
        visitas?.forEach { entry ->
            visitaList.add("${entry.key}: ${entry.value.name}")
        }
        val visitaAdapter = ArrayAdapter(this, android.R.layout.tex, visitaList)
        with(idSpinner) {
            adapter = weaponAdapter
            setSelection(0, false)
            gravity = Gravity.CENTER
        }
    }*/

    private suspend fun getVisitaFromApi() {
        val selectedItem = rutField.text.toString()
        val visitaId = selectedItem.subSequence(0, selectedItem.indexOf(":")).toString()
        println("Loading ${visitaId}... ")
        val visitaResponse = GlobalScope.async(Dispatchers.IO) {
            firebaseApi.getVisitas(visitaId)
        }
        val visita = visitaResponse.await()
        rutField.setText(visita?.rut)
        razonSocialField.setText(visita?.razonSocial)
        direccionField.setText(visita?.direccion)
        fechaVisitaField.setText(visita?.fechaVisita)
        compraField.setText(visita?.compra)
        comentariosField.setText(visita?.comentarios)
    }

    private suspend fun sendVisitaToApi() {
        val rut = rutField.text.toString()
        val razonSocial = razonSocialField.text.toString()
        val direccion = direccionField.text.toString()
        val fechaVisita = fechaVisitaField.text.toString()
        val compra = compraField.text.toString()
        val comentarios = comentariosField.text.toString()
        val visita = VisitaResponse(rut, razonSocial, direccion,fechaVisita,compra, comentarios)
        val visitaResponse = GlobalScope.async(Dispatchers.IO) {
            firebaseApi.setVisita(visita)
        }
        val response = visitaResponse.await()
        rutField.setText(visita?.rut)
        razonSocialField.setText(visita?.razonSocial)
        direccionField.setText(visita?.direccion)
        fechaVisitaField.setText(visita?.fechaVisita)
        compraField.setText(visita?.compra)
        comentariosField.setText(visita?.comentarios)
    }
}