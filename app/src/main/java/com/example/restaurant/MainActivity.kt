package com.example.restaurant

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.repositories.RestaurantRepository
import com.example.restaurant.viewmodels.MainViewModel
import com.notificationman.library.NotificationMan

class MainActivity : AppCompatActivity(),ListenersInterface {
    var CHANNEL_ID = "channel01"
    var notificationId = 101
    val mainViewModel:MainViewModel ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        creatNotificationChannel()
        val mainViewModel : MainViewModel by viewModels()

        var liveBadge = mainViewModel.liveBadge
        mainViewModel.getLiveBadge(this)


        if(PrefConfing().readListFromPref(this).isEmpty()){
            swapFragments(CategoriesFragment())
        }else{
            swapFragments(OrderFragment())
        }

        liveBadge.observe(this, Observer<Int> {
            binding.testText.text = it.toString()
            binding.menu.getOrCreateBadge(R.id.order).number = it
            binding.menu.refreshDrawableState()
            //RestaurantRepository.orderListQuantity = it
        })


        var getIntentError = intent.getStringExtra("error")
        if(getIntentError == "error"){
            swapFragments(ErrorFragment())
            getIntentError = ""
        }



        binding.menu.setOnNavigationItemSelectedListener {
            handeBottonNavigation(it.itemId,binding)
        }

    }

    private fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .addToBackStack("back")
                .replace(R.id.categories_container, fragment)
                .commit()
    }

    private fun handeBottonNavigation(menuItemId:Int, binding:ActivityMainBinding):Boolean {
        return when(menuItemId){
            R.id.categories -> {
                RestaurantRepository.categoriesList.clear()
                swapFragments(CategoriesFragment())
                true
            }
            R.id.order -> {
                swapFragments(OrderFragment())
                true
            }
            else -> false
        }
    }

    private fun updateBadge(binding: ActivityMainBinding){

        val prefList = PrefConfing().readListFromPref(this)
        val orderBadgeNumber = prefList.size
         binding.menu.refreshDrawableState()
        // menu.getOrCreateBadge(R.id.order).number=orderBadgeNumber

        //var count = RestaurantRepository.orderList.size
        binding.menu.getOrCreateBadge(R.id.order).number = orderBadgeNumber
    }

    private fun creatNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "notifications title"
            val descriptionText = "Description Text"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID,name,importance)
            .apply {
                description = descriptionText
            }
            val notificationManager:NotificationManager=getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    //I was using this fun , to show a regular notification, instead i used a library
    //to show the notification on x seconds based on 50% of the total prep time
    private fun sendNotification(){
        val builder = NotificationCompat.Builder(
        this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Thank you for your order.")
            .setContentText("You will recieve a notification in " +
                    "${RestaurantRepository.getTimeForNotification
                    (RestaurantRepository.totalPrepTime)/60} minutes.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }
    }

    override fun goToFragment(fragment : Fragment) {
        swapFragments(fragment)
    }


    override fun liveBadgeUpdate(context: Context) {
        val mainv : MainViewModel by viewModels()
        mainv.getLiveBadge(context)
    }

    override fun forScheduleNotification() {
        val timeInSeconds = RestaurantRepository.
        getTimeForNotification(RestaurantRepository.totalPrepTime)

        fun fireNotificationMan(context : Context) = NotificationMan
                .Builder(context, "com.notification.man.MainActivity") // make sure class path match with your project architecture
                .setTitle("Your Restaurant order") // optional
                .setDescription("Be prepared,order is gonna be ready in" +
                        " ${RestaurantRepository.notificationPrepTimeRemaining(timeInSeconds)} Minutes") // optional
                .setThumbnailImageUrl("https://www.presidenteicguadalajara.com/english/" +
                        "resourcefiles/diningsmallimages/bistro-la-bastille-in-presidencial-presidente-" +
                        "intercontinental-guadalajara-mexico-01.jpg?version=3192021103745") // optional
                .setTimeInterval(timeInSeconds.toLong()) // needs secs - default is 5 secs
                .setNotificationType(NotificationMan.NOTIFICATION_TYPE_IMAGE) // optional - default type is TEXT
                .fire()
        fireNotificationMan(applicationContext)

        PrefConfing().deletePref(this)
        val tempList = RestaurantRepository.orderList
        PrefConfing().writeListInPref(this,tempList)

        sendNotification()

    }


}