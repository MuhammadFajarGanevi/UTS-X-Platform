package com.example.restopadang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restopadang.R
import com.example.restopadang.data.MenuItem

class MenuAdapter(
    private val menuList: List<MenuItem>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    val itemQuantities = mutableMapOf<String, Int>() // Map untuk menyimpan kuantitas setiap item

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewMenu: ImageView = view.findViewById(R.id.imageViewMenu)
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)
        val buttonAddToCart: Button = view.findViewById(R.id.buttonAddToCart)
        val layoutCounter: LinearLayout = view.findViewById(R.id.layoutCounter)
        val textViewQuantity: TextView = view.findViewById(R.id.textViewQuantity)
        val buttonPlus: ImageButton = view.findViewById(R.id.buttonPlus)
        val buttonMinus: ImageButton = view.findViewById(R.id.buttonMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.imageViewMenu.setImageResource(menu.imageResId)
        holder.textViewName.text = menu.name
        holder.textViewPrice.text = "Rp ${menu.price}"

        // Default: hide counter, show "Add to Cart" button
        holder.layoutCounter.visibility = View.GONE
        holder.buttonAddToCart.visibility = View.VISIBLE

        var quantity = 0

        holder.buttonAddToCart.setOnClickListener {
            holder.buttonAddToCart.visibility = View.GONE
            holder.layoutCounter.visibility = View.VISIBLE
            quantity = 1
            holder.textViewQuantity.text = quantity.toString()

            // Simpan kuantitas ke map
            itemQuantities[menu.name] = quantity
        }

        holder.buttonPlus.setOnClickListener {
            quantity++
            holder.textViewQuantity.text = quantity.toString()
            itemQuantities[menu.name] = quantity
        }

        holder.buttonMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                holder.textViewQuantity.text = quantity.toString()
                itemQuantities[menu.name] = quantity
            } else {
                holder.layoutCounter.visibility = View.GONE
                holder.buttonAddToCart.visibility = View.VISIBLE
                itemQuantities.remove(menu.name) // Hapus dari map jika kuantitasnya 0
            }
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}
