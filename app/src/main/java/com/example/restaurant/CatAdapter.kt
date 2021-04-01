package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding
import com.example.restaurant.models.CategoriesModel

class CatAdapter(
        private val items:List<String>,
        private val onClick:(String)-> Unit
): RecyclerView.Adapter<CatAdapter.CurrentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            :CurrentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoriesBinding.inflate(
                layoutInflater, parent, false)
        return CurrentViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
            holder: CurrentViewHolder,position: Int) {
        val item = items[position]
        holder.onBind(items[position])
        holder.itemView.setOnClickListener {
            var new = CategoriesModel(item)
            onClick(item)
        }

    }

    class CurrentViewHolder(
            private val binding: ItemCategoriesBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(current:String){
            binding.apply {
                categorie.text = current
            }

        }
    }
}
