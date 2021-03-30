package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding

class CatAdapter(
        private val items:List<String>
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
