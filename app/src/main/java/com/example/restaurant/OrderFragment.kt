package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ResourceCursorTreeAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.FragmentCategoriesBinding
import com.example.restaurant.databinding.FragmentMenuBinding
import com.example.restaurant.databinding.FragmentOrderBinding

class OrderFragment:Fragment(R.layout.fragment_order) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentMenuBinding.inflate(inflater)
        val recycler = view.findViewById<RecyclerView>(R.id.order_recycler)

        binding.apply {
            recycler.apply {
                adapter = OrderAdapter(RestaurantRepository.orderList)

                val itemTouchHelper = ItemTouchHelper(
                        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT
                        ) {
                            override fun onMove(recyclerView: RecyclerView,
                                                viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                                val fromPos = viewHolder.adapterPosition
                                val toPos = target.adapterPosition
                                // move item in `fromPos` to `toPos` in adapter.
                                return true // true if moved, false otherwise
                            }

                            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int ) {
                                // remove from adapter

                                var position: Int = viewHolder.adapterPosition
                                var orderListFromRepo = RestaurantRepository.orderList
                                // booksListFromRepo.removeAt(position)
                                RestaurantRepository.orderList.removeAt(position)
                                adapter?.notifyDataSetChanged()
                                var intent = Intent(context,MainActivity::class.java)
                                intent.putExtra("change","change")
                                startActivity(intent)

                                // a way to refresh mainActivity, to get recycler view show changes
                            }

                        })

                itemTouchHelper.attachToRecyclerView(this)

                layoutManager = LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL,
                        false)
                setHasFixedSize(true)
                adapter?.notifyDataSetChanged()

            }
                //adapter =  OrderAdapter(RestaurantRepository.orderList)
                //layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

            }
        }
    }
//}