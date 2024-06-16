package com.glucode.about_you.service

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import com.glucode.about_you.engineers.models.Engineer
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.io.InputStream
import android.util.Base64

class LocalRoom(context: Context?) {

    private val sharedPreferences : SharedPreferences = context!!.getSharedPreferences("MyRoom",Context.MODE_PRIVATE)
private val context : Context? = context
    fun saveImage(engineer: Engineer,uri: Uri?){
        var engineerJson : String = Gson().toJson(engineer)
        val myKey = sharedPreferences.edit()
        myKey.putString(engineerJson,uriToBase64(context,uri))
        myKey.apply()
    }

    fun uriToBase64(context: Context?, uri: Uri?): String? {
        var inputStream: InputStream? = null
        try {
            inputStream = uri?.let { context?.contentResolver?.openInputStream(it) }
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var len: Int
            while (inputStream?.read(buffer).also { len = it!! } != -1) {
                byteArrayOutputStream.write(buffer, 0, len)
            }
            val data = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(data, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun getImage(engineer: Engineer): String? {
        var engineerJson : String = Gson().toJson(engineer)
        return sharedPreferences.getString(engineerJson," ")
    }

}