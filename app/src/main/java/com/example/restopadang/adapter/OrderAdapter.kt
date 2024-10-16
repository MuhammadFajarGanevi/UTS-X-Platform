package com.example.restopadang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restopadang.R
import com.example.restopadang.data.CartItem

class OrderAdapter(private val cartItems: List<CartItem>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val textViewQuantity: TextView = view.findViewById(R.id.textViewQuantity)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = cartItems[position]
        holder.textViewName.text = item.name
        holder.textViewQuantity.text = "Jumlah: ${item.quantity}"
        holder.textViewPrice.text = "Harga Satuan: Rp ${item.price}"
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}
