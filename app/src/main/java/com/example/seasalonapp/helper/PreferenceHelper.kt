package com.example.seasalonapp.helper

import android.content.Context
import com.example.seasalonapp.data.model.response.User
import com.google.gson.Gson

object PreferenceHelper {
    private const val PREF_NAME = "login_pref"
    private const val ACCESS_TOKEN = "access_token"
    private const val USER_DATA = "user_data"

    fun saveAccessToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(ACCESS_TOKEN, token).apply()
    }

    fun getAccessToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ACCESS_TOKEN, null)
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