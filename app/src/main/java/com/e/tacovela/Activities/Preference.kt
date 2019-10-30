package com.e.tacovela.Activities

import android.content.Context

class Preference(context: Context){
    val PREFERENCE_NAME = "Preference_Tacovela"
    val PREFERENCE_TOKEN = "TOKEN"

    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getToken():String?{
        return preference.getString(PREFERENCE_TOKEN, null)
    }
    fun setToken(token:String){
        val editor = preference.edit()
        editor.putString(PREFERENCE_TOKEN, token)
        editor.apply()
    }
}