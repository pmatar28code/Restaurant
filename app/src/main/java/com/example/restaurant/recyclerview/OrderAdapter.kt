package com.example.restaurant.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.Networking.MenuServer
import com.example.restaurant.R
import com.example.restaurant.databinding.ItemOrderBinding
import com.squareup.picasso.Picasso

class OrderAdapter(
        private val items:List<MenuServer.Item>
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(
        layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
            holder: OrderViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(items[position])

        var icon = item.imageUrl
        icon =  icon.replace("localhost","192.168.1.191")
        Log.d("ADDAAAPPTEERRR",icon)
        Picasso.get().load(icon).into(holder.itemView
                .findViewById<ImageView>(R.id.order_image))
    }

    class OrderViewHolder(
            private val binding: ItemOrderBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(current: MenuServer.Item){
            binding.apply {
                orderNameText.text = "Item: ${current.name}"
                orderPriceText.text = "Price: $${current.price}"
            }
        }
    }
}