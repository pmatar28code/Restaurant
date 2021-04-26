package com.example.restaurant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurant.repositories.RestaurantRepository

class OrderViewModel: ViewModel() {

    var liveOrderTotal = MutableLiveData<Double>()

    init{
        liveOrderTotal.postValue(0.00)
    }

    fun setLiveOrderTotal(){
        RestaurantRepository.getCheckTotal()
        liveOrderTotal.postValue(RestaurantRepository.totalCheckAmount)
    }
}