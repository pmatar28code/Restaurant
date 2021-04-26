package com.example.restaurant.repositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.allyants.notifyme.NotifyMe
import com.example.restaurant.Networking.CategoriesServer
import com.example.restaurant.Keys
import com.example.restaurant.MainActivity
import com.example.restaurant.Networking.MenuServer
import com.example.restaurant.Networking.CategoriesClient
import com.example.restaurant.Networking.MenuClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.roundToInt

object RestaurantRepository {
    val ipAddress = "192.168.1.191"
    val urlForClients = "http://$ipAddress:8090"
    var menuList = mutableListOf<MenuServer.Item>()
    var categoriesList = mutableListOf<String>()
    var string = ""
    var MenuObject: MenuServer.Item?=null
    var orderList = mutableListOf<MenuServer.Item>()
    var totalPrepTime: Int = 0
    var totalCheckAmount: Double = 0.0
    var menuCategory = ""
    //var orderListQuantity = 0

    fun callGetCategories(context:Context,onComplete : (List<String>) -> Unit) {
        CategoriesClient.categoriesService.getCategories().
            enqueue(object : Callback<CategoriesServer> {
                override fun onFailure(call: Call<CategoriesServer>, t: Throwable) {
                    //this error message takes like 5 seconds to show
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("error","error")
                    startActivity(context,intent, Bundle.EMPTY)
                }
                override fun onResponse(call: Call<CategoriesServer>,
                                        response: Response<CategoriesServer>) {
                    if (response.isSuccessful) {
                       // Log.d("Error","${response.body()?.categories}")
                        if(response.body()?.categories?.isEmpty()!!){
                        }
                        val listServer = response.body()?.categories
                        if (listServer != null) {
                            for(item in listServer){
                                categoriesList.add(item)
                            }
                        }
                    }
                    onComplete(categoriesList)
                }
            })
    }

    fun callGetMenu(menu :String,context: Context,onComplete: (List<MenuServer.Item>) -> Unit){
        MenuClient.MenuService.getMenu(menu).
        enqueue(object : Callback<MenuServer> {
            override fun onFailure(call: Call<MenuServer>, t: Throwable) {
               // Log.d("Pedrrrooo no jalaa","error$ $t")
            }
            override fun onResponse(call: Call<MenuServer>, response: Response<MenuServer>) {
                if (response.isSuccessful) {
                    //Log.d("Error","${response.body()?.items}")
                    if(response.body()?.items?.isEmpty()!!){

                    }
                    val newMenuList = response.body()?.items

                    if (newMenuList != null) {
                        for(item in newMenuList){
                           menuList.add(item)
                        }
                    }
                    onComplete(menuList)
                }
            }
        })
    }
    //This was for Post, but still cant get it to work
    fun getOrderIdsList():List<Keys>{
        val list = mutableListOf<Keys>()

        for(item in orderList){
            val keys = Keys(item.id.toString())
            list.add(keys)
        }
        return list.toList()
        //var newStringList = mutableListOf<String>()
        //for(item in mutableList){
          //  var id =  item.id.toString()
            //newStringList.add( id)
       // }
       // return    newStringList.toList()

    }
    var ids : List<String> = emptyList()
    fun post(context: Context){
        MenuClient.MenuService.sendItems(getOrderIdsList()).enqueue(object: Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getCheckTotal(){
        var oderList = orderList
        for(item in orderList){
            totalCheckAmount += item.price
        }
    }

    fun getTotalPrepTime(){
       val orderList = orderList
        for(item in orderList){
            totalPrepTime += item.estimatedPrepTime
       }
    }

     private fun MenuServer.Item.toToServer(): MenuServer.Item {
        return MenuServer.Item(
                name = name,
                imageUrl = imageUrl,
                category = category,
                description = description,
                id = id,
                price = price,
                estimatedPrepTime = estimatedPrepTime
        )
    }

    fun createNotifyMe(context: Context,minutes:Int){
        var hour =Calendar.HOUR
        val minute = Calendar.MINUTE

        hour *= 60
        hour +=minute

        val prepTimeMinusTen = minutes - 10
        val timeNotificationMinutes = hour + prepTimeMinusTen

        var hourToMinutes = timeNotificationMinutes % 60
        var hourToHours = timeNotificationMinutes / 60
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR,2021)
        calendar.set(Calendar.MONTH,4)
        calendar.set(Calendar.DATE,10)
        calendar.set(Calendar.HOUR_OF_DAY,4)
        calendar.set(Calendar.MINUTE,42)
        calendar.set(Calendar.SECOND,2)

        // could not get this library to work.
        val notifyMe = NotifyMe.Builder(context)
        //Then set the fields you want.
        notifyMe.title("Remaining Prep Time")
        notifyMe.content("test")
        notifyMe.key("test")
        //notifyMe.color(Int red,Int green,Int blue,Int alpha)//Color of notification header
        //notifyMe.led_color(Int red,Int green,Int blue,Int alpha)//Color of LED when notification pops up
        notifyMe.time(calendar)//The time to popup notification
        notifyMe.delay(0)//Delay in ms
       // notifyMe.large_icon(Int resource)//Icon resource by ID
        notifyMe.rrule("FREQ=MINUTELY;INTERVAL=5;COUNT=2")//RRULE for frequency of notification
        //notifyMe.addAction(Intent intent,String text);//The action will call the intent when pressed
            .build()
    }

    fun getTimeForNotification(minutes:Int): Int{
        val toSeconds = minutes * 60
        val percentage = .50
        val finalSeconds = percentage * toSeconds
        return finalSeconds.roundToInt()
    }

    fun notificationPrepTimeRemaining(seconds: Int):Double{
        val minutes = seconds/60
            return minutes.toDouble()
    }
}

