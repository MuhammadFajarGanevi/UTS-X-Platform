package com.example.restopadang

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restopadang.adapter.MenuAdapter
import com.example.restopadang.data.CartItem
import com.example.restopadang.data.MenuItem

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMenu)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val menus = listOf(
            MenuItem(R.drawable.nasi_padang, "Nasi Padang", 25000),
            MenuItem(R.drawable.rendang, "Rendang", 30000),
            MenuItem(R.drawable.sate_padang, "Sate Padang", 20000),
            MenuItem(R.drawable.gulai_ayam, "Gulai Ayam", 28000)
        )

        val adapter = MenuAdapter(menus)
        recyclerView.adapter = adapter

        val buttonLanjutkan = findViewById<Button>(R.id.Lanjutkan)
        buttonLanjutkan.setOnClickListener {
            // Buat list keranjang berdasarkan kuantitas yang diambil dari adapter
            val cartItems = mutableListOf<CartItem>()

            for ((name, quantity) in adapter.itemQuantities) {
                val menu = menus.find { it.name == name }
                if (menu != null) {
                    cartItems.add(CartItem(menu.name, menu.price, quantity))
                }
            }

            // Kirim item ke RecheckActivity
            val intent = Intent(this, RecheckActivity::class.java)
            intent.putParcelableArrayListExtra("CART_ITEMS", ArrayList(cartItems))
            startActivity(intent)
        }
    }
}
