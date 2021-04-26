package com.example.restaurant.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurant.Networking.MenuServer
import com.example.restaurant.PrefConfing
import com.example.restaurant.repositories.RestaurantRepository
import java.util.concurrent.CopyOnWriteArrayList

class MainViewModel : ViewModel(){
    var liveBadge = MutableLiveData<Int>()

    init
    {
        liveBadge.postValue(0)
    }

    fun getLiveBadge(context:Context){
        val prefList = PrefConfing().readListFromPref(context)
        val orderBadgeNumber = prefList.size
        liveBadge.postValue(orderBadgeNumber)
        }

    }
