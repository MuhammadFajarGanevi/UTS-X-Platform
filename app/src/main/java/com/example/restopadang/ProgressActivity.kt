package com.example.restopadang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        // Get payment method from Intent
        val method = intent.getStringExtra("method")
        val textViewStatus = findViewById<TextView>(R.id.textViewStatus)

        textViewStatus.text = when (method) {
            "kasir" -> "Menunggu pembayaran di kasir"
            "qr" -> "Menunggu pembayaran melalui QR"
            else -> "Status tidak diketahui"
        }
    }
}
