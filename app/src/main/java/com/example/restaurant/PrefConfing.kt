package com.example.restaurant

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.restaurant.Networking.MenuServer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefConfing {
    var LIST_KEY = "list_key"
    fun writeListInPref(context: Context,list:  List<MenuServer.Item>){
        val gson = Gson()
        val jsonString = gson.toJson(list)
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
     val editor:SharedPreferences.Editor= pref.edit()
        editor.putString(LIST_KEY,jsonString)
        editor.apply()
    }
    fun readListFromPref(context:Context):List<MenuServer.Item>{
        val pref:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString: String? = pref.getString(LIST_KEY,"")
        val gson = Gson()
        val type = object: TypeToken<List<MenuServer.Item>>(){}.type
        val list:List<MenuServer.Item> ?= gson.fromJson(jsonString,type)?: emptyList()
        return list!!
    }

    fun deletePref(context: Context){
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor:SharedPreferences.Editor= pref.edit()
        //editor.putString(LIST_KEY,jsonString)
       // editor.apply()
        editor.clear()
        editor.apply()
    }
}