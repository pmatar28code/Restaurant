package com.example.restaurant.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.Networking.MenuServer
import com.example.restaurant.R
import com.example.restaurant.databinding.ItemsMenuBinding
import com.example.restaurant.repositories.RestaurantRepository
import com.squareup.picasso.Picasso

class MenuAdapter(
        private val items:List<MenuServer.Item>,
        private val onClick:(MenuServer.Item, pos:Int)-> Unit,
        private val addToOnClick:(MenuServer.Item)->Unit
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemsMenuBinding.inflate(
                layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
            holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(items[position])

        var icon = item.imageUrl
       icon =  icon.replace("localhost", RestaurantRepository.ipAddress)
        Picasso.get().load(icon).into(holder.itemView
            .findViewById<ImageView>(R.id.menu_image))

        holder.itemView.setOnClickListener {
            onClick(item,position)
        }
        holder.itemView.findViewById<Button>(R.id.menu_add_to_cart_button).setOnClickListener {
            addToOnClick(item)
        }
    }

    class MenuViewHolder(
            private val binding: ItemsMenuBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(current: MenuServer.Item){
            binding.apply {
                menuText.text = current.name
                menuPriceText.text = "Price:$${current.price}"

            }
        }
    }
}