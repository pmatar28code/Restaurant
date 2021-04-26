package com.example.restaurant

import android.content.Context
import androidx.fragment.app.Fragment

interface ListenersInterface {
    fun goToFragment(fragment: Fragment)
    fun liveBadgeUpdate(context: Context)
    fun forScheduleNotification()

}