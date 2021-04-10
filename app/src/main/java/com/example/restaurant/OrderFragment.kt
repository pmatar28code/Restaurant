package com.example.restaurant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.databinding.FragmentOrderBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrderFragment:Fragment(R.layout.fragment_order) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var inflater = LayoutInflater.from(context)
        val binding = FragmentOrderBinding.inflate(inflater)
        val recycler = view.findViewById<RecyclerView>(R.id.order_recycler)

        binding.apply {
            recycler.apply {
                var prefList= if(
                PrefConfing().readListFromPref(requireContext()).isNotEmpty()){
                    PrefConfing().readListFromPref(requireContext()).toMutableList()
                }else{
                    mutableListOf<MenuServer.Item>()
                }
                if(RestaurantRepository.orderList.isEmpty() && prefList.isNotEmpty() ){
                    for(item in prefList){
                        RestaurantRepository.orderList.add(item)
                    }
                    adapter = OrderAdapter(RestaurantRepository.orderList)
                }else{
                    adapter = OrderAdapter(RestaurantRepository.orderList)
                }



                val itemTouchHelper = ItemTouchHelper(
                        object : ItemTouchHelper.SimpleCallback(
                                0, ItemTouchHelper.RIGHT
                        ) {
                            override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                            ): Boolean {
                                val fromPos = viewHolder.adapterPosition
                                val toPos = target.adapterPosition
                                // move item in `fromPos` to `toPos` in adapter.
                                return true // true if moved, false otherwise
                            }

                            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                            direction: Int) {
                                // remove from adapter
                                var position: Int = viewHolder.adapterPosition
                                var orderListFromRepo =
                                RestaurantRepository.orderList
                                RestaurantRepository.orderList.removeAt(position)
                                adapter?.notifyDataSetChanged()
                                prefList.removeAt(position)
                                PrefConfing().deletePref(requireContext())
                                PrefConfing().writeListInPref(requireContext(),prefList.toList())
                                var intent = Intent(context, MainActivity::class.java)
                                intent.putExtra("change", "change")
                                startActivity(intent)
                            }
                        })

                itemTouchHelper.attachToRecyclerView(this)
                layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter?.notifyDataSetChanged()
            }

            RestaurantRepository.totalCheckAmount = 0.0
            RestaurantRepository.getCheckTotal()
            var orderText = view.findViewById<TextView>(R.id.order_title_text)
            orderText.text = "Your Order Total: ${RestaurantRepository.totalCheckAmount}"
            var orderButton = view.findViewById<Button>(R.id.order_submit_button)

            if (RestaurantRepository.orderList.isNotEmpty()) {
                orderButton.setOnClickListener {
                    var dialog = SubmitDialog()
                    dialog.show(childFragmentManager, "start")
                }
            }
        }

    }
}
