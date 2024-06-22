package com.example.seasalonapp.helper

import android.content.Context
import com.example.seasalonapp.data.model.response.User
import com.example.seasalonapp.data.model.response.mainservice.Services
import com.google.gson.Gson

object PreferenceHelper {
    private const val PREF_NAME = "login_pref"
    private const val ACCESS_TOKEN = "access_token"
    private const val USER_DATA = "user_data"
    private const val DATA_SERVICE = "data_service"

    fun saveAccessToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply()
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun saveDataService(context: Context, service: List<Services>) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val serviceJson = Gson().toJson(service)
        sharedPreferences.edit().putString(DATA_SERVICE, serviceJson).apply()
    }

    fun getServices(context: Context): List<Services>? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        val serviceJson = sharedPreferences.getString(DATA_SERVICE, null)
        return if (serviceJson != null) {
            Gson().fromJson(serviceJson, listOf<Services>()::class.java)
        } else {
            null
        }
    }

    fun saveUser(context: Context, user: User) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userJson = Gson().toJson(user)
        sharedPreferences.edit().putString(USER_DATA, userJson).apply()
    }

    fun getUser(context: Context): User? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userJson = sharedPreferences.getString(USER_DATA, null)
        return if (userJson != null) {
            Gson().fromJson(userJson, User::class.java)
        } else {
            null
        }
    }
}