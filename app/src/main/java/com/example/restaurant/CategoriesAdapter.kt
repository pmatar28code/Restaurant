package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding


class CategoriesAdapter : ListAdapter<String, CategoriesAdapter.StoreItemViewHolder>(diff) {

    companion object {
        private val diff = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return areContentsTheSame(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem:String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return StoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.onBind(getItem(position))

        //var item = getItem(position)
        //var icon = item.artworkUrl100
       // Picasso.get().load(icon).into(holder.itemView
        //    .findViewById<ImageView>(R.id.image))
    }

    class StoreItemViewHolder(
        private val binding: ItemCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(title: String) {
            binding.categorie.text =title

        }
    }
}