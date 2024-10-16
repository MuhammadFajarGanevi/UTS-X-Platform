package com.example.restopadang

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        // Set listener for "Pesan Sekarang" button
        val buttonPesan = findViewById<Button>(R.id.buttonPesan)
        buttonPesan.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}
