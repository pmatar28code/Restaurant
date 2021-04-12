package com.example.restaurant

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.restaurant.databinding.ActivityMainBinding
import com.example.restaurant.repositories.RestaurantRepository
import com.notificationman.library.NotificationMan

class MainActivity : AppCompatActivity() {
    var CHANNEL_ID = "channel01"
    var notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        creatNotificationChannel()

        var getIntent = intent.getStringExtra("change")
        var getIntentDetails = intent.getStringExtra("details")
        var getIntentConfirmation = intent.getStringExtra("confirmation")
        var getIntentError = intent.getStringExtra("error")

        Handler().postDelayed(
            {
            if(getIntent!=null){
            binding.menu.selectedItemId=R.id.order
            RestaurantRepository.menuList.clear()
            getIntent = ""
            }else if(getIntentDetails == "details"){
                swapFragments(DetailsFragment())
                getIntentDetails =""
            }else if (getIntentConfirmation == "confirmation"){
                swapFragments((ConfirmationFragment()))
                getIntentConfirmation = ""
                sendNotification()
                // var test = applicationContext//activity?.applicationContext
                // RestaurantRepository.createNotifyMe(test,RestaurantRepository.totalPrepTime)
                //i used a library
                //to show the notification on x seconds based on 50% of the total prep time
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
                //This error message takes like 5 seconds to show
            }else if(getIntentError == "error"){
                swapFragments(ErrorFragment())
                getIntentError = ""
            }else{
                binding.menu.selectedItemId=R.id.categories
                //RestaurantRepository.categoriesList.clear()
                RestaurantRepository.menuList.clear()
                updateBadge(binding)
                    }
                },
                600 // value in milliseconds
        )

        binding.menu.setOnNavigationItemSelectedListener {
            handeBottonNavigation(it.itemId,binding)
        }
        val orderBadgeNumber = RestaurantRepository.orderList.size
        binding.menu.refreshDrawableState()
        binding.menu.getOrCreateBadge(R.id.order).number=orderBadgeNumber
    }

    private fun swapFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
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
}