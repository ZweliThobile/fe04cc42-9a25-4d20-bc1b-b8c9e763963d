package com.glucode.about_you.service

import android.content.Context
import android.content.SharedPreferences
import com.glucode.about_you.engineers.models.Engineer
import com.google.gson.Gson

class LocalRoom(context: Context?) {

    private val sharedPreferences: SharedPreferences =
        context!!.getSharedPreferences("MyRoom", Context.MODE_PRIVATE)

    fun saveImage(engineer: Engineer, uri: String?) {
        var engineerJson: String = Gson().toJson(engineer)
        val myKey = sharedPreferences.edit()
        myKey.putString(engineerJson, uri)
        myKey.apply()
    }

    fun getImage(engineer: Engineer): String? {
        var engineerJson: String = Gson().toJson(engineer)
        return sharedPreferences.getString(engineerJson, " ")
    }

}