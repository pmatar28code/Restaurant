package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding
import com.example.restaurant.models.CategoriesModel
import com.example.restaurant.repositories.RestaurantRepository
import com.squareup.picasso.Picasso

//This is the one i used

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
        val appetizersImage = RestaurantRepository.urlForClients+"/images/appetizers.png"
        val saladsImage = RestaurantRepository.urlForClients+"/images/salads.png"
        val soupsImage = RestaurantRepository.urlForClients+"/images/soups.png"
        val entreesImage = RestaurantRepository.urlForClients+"/images/entrees.png"
        val dessertsImage = RestaurantRepository.urlForClients+"/images/desserts.png"
        val sandwichesImage = RestaurantRepository.urlForClients+"/images/sandwiches.png"

        // i added images to the server, thats why i asked to please use my server attached
        when (item) {
            "appetizers" -> {
                Picasso.get().load(appetizersImage).into(holder.itemView
                        .findViewById<ImageView>(R.id.category_image))
            }
            "salads" -> {
                Picasso.get().load(saladsImage).into(holder.itemView
                        .findViewById<ImageView>(R.id.category_image))
            }
            "soups" -> {
                Picasso.get().load(soupsImage).into(holder.itemView
                        .findViewById<ImageView>(R.id.category_image))
            }
            "entrees" ->{ Picasso.get().load(entreesImage).into(holder.itemView
                    .findViewById<ImageView>(R.id.category_image))
            }
            "desserts" -> { Picasso.get().load(dessertsImage).into(holder.itemView
                    .findViewById<ImageView>(R.id.category_image))
            }
            "sandwiches" -> { Picasso.get().load(sandwichesImage).into(holder.itemView
                    .findViewById<ImageView>(R.id.category_image))
            }
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
