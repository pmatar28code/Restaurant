package com.example.restaurant

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrefConfing {
    var LIST_KEY = "list_key"
    fun writeListInPref(context: Context,list:  List<MenuServer.Item>){
        var gson = Gson()
        var jsonString = gson.toJson(list)
    var pref = PreferenceManager.getDefaultSharedPreferences(context)
     var editor:SharedPreferences.Editor= pref.edit()
        editor.putString(LIST_KEY,jsonString)
        editor.apply()
    }
    fun readListFromPref(context:Context):List<MenuServer.Item>{
        var pref:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var jsonString: String? = pref.getString(LIST_KEY,"")
        var gson = Gson()
        var type = object: TypeToken<List<MenuServer.Item>>(){}.type
        var list:List<MenuServer.Item> = gson.fromJson(jsonString,type)
        return list
    }

    fun deletePref(context: Context){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var editor:SharedPreferences.Editor= pref.edit()
        //editor.putString(LIST_KEY,jsonString)
       // editor.apply()
        editor.clear()
        editor.apply()
    }
}