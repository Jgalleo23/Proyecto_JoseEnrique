package com.joseenrique.proyecto_joseenrique

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreditsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_credits)

        val tvAppName = findViewById<TextView>(R.id.tvAppName)

        val nombre = intent.getStringExtra("NAME")
        tvAppName.apply {
            text =getString(R.string.app_description, nombre)
        }

        val btContact = findViewById<Button>(R.id.btContact)
        btContact!!.setOnClickListener {
            val email = "jgalleo293@g.educaand.es"
            val appName = "PetHome"
            val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(email))

            intentMail.type = "plain/text"
            intentMail.putExtra(Intent.EXTRA_SUBJECT, "Consulta de la app $appName")
            startActivity(Intent.createChooser(intentMail, "Elije un cliente de correo"))
        }
    }
}