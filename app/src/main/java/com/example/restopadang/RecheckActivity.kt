package com.example.restopadang

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restopadang.adapter.OrderAdapter
import com.example.restopadang.data.CartItem

@Suppress("DEPRECATION")
class RecheckActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var textViewTotalPrice: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recheck)

        recyclerView = findViewById(R.id.recyclerViewOrder)
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice)

        // Ambil data cartItems dari intent
        val cartItems = intent.getParcelableArrayListExtra<CartItem>("CART_ITEMS") ?: arrayListOf()

        // Gabungkan item yang sama berdasarkan nama, lalu hitung jumlah total dan total harga per item
        val groupedItems = cartItems.groupBy { it.name }.map { (name, items) ->
            val totalQuantity = items.sumOf { it.quantity }
            val price = items.first().price // Harga satuan
            CartItem(name, price, totalQuantity)
        }

        // Atur adapter untuk menampilkan item yang sudah digabung
        orderAdapter = OrderAdapter(groupedItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = orderAdapter

        // Hitung total harga seluruh keranjang
        val totalPrice = groupedItems.sumOf { it.price * it.quantity }
        textViewTotalPrice.text = "Total Harga: Rp $totalPrice"

        // Tombol untuk konfirmasi pesanan
        findViewById<Button>(R.id.buttonConfirmOrder).setOnClickListener {
            // Logika untuk konfirmasi pesanan bisa ditambahkan di sini
        }
    }
}
