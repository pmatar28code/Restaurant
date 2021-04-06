package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding
import com.example.restaurant.models.CategoriesModel
// I didnt use this List Adapter because i was having a hard time to set the onclicks on activity
// i used the CatAdapter instead
class CategoriesAdapter(
        private var onClick: (CategoriesModel) -> Unit
) : ListAdapter<String, CategoriesAdapter.RestaurantViewHolder>(diff) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(inflater, parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.onBind(getItem(position))
        var item = getItem(position)
        holder.itemView.setOnClickListener {
        var Cname = CategoriesModel(item)
            onClick (Cname)
        }
    }

    class RestaurantViewHolder(
        private val binding: ItemCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(title: String) {
            binding.categorie.text =title
        }
    }
}