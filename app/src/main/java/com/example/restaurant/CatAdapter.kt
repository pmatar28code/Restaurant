package com.example.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ItemCategoriesBinding
import com.example.restaurant.models.CategoriesModel
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
        var appetizersImage = "http://192.168.1.191:8090/images/appetizers.png"
        var saladsImage = "http://192.168.1.191:8090/images/salads.png"
        var soupsImage = "http://192.168.1.191:8090/images/soups.png"
        var entreesImage = "http://192.168.1.191:8090/images/entrees.png"
        var dessertsImage = "http://192.168.1.191:8090/images/desserts.png"
        var sandwichesImage = "http://192.168.1.191:8090/images/sandwiches.png"

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
