package com.example.restaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding
import com.example.restaurant.databinding.ItemsMenuBinding
import com.example.restaurant.models.CategoriesModel
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class MenuAdapter(
        private val items:List<MenuServer.Item>,
        private val onClick:(MenuServer.Item,pos:Int)-> Unit
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            :MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemsMenuBinding.inflate(
                layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
            holder: MenuViewHolder,position: Int) {
        val item = items[position]
        holder.onBind(items[position])

        var icon = item.imageUrl
       icon =  icon.replace("localhost","192.168.1.191")
        Log.d("ADDAAAPPTEERRR",icon)
        Picasso.get().load(icon).into(holder.itemView
            .findViewById<ImageView>(R.id.menu_image))


        holder.itemView.setOnClickListener {
            onClick(item,position)
        }

    }

    class MenuViewHolder(
            private val binding: ItemsMenuBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(current:MenuServer.Item){
            binding.apply {
                menuText.text = current.name

            }

        }
    }
}