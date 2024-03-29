package com.gustu.logbook.sharePreferences

import android.content.Context
import android.content.SharedPreferences

import com.gustu.logbook.BuildConfig


object SharedPrefUtil {

    fun pref(): SharedPreferences {
        return App.context!!.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    fun edit(): SharedPreferences.Editor {
        return pref().edit()
    }

    fun saveString(key: String, value: String?) {
        edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return pref().getString(key, null)
    }

    fun saveInt(key: String, value: Int) {
        edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return pref().getInt(key, 0)
    }

    fun saveBoolean(key: String, value: Boolean) {
        pref().edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref().getBoolean(key, false)
    }


    fun saveToken(key: String, value: String) {
        edit().putString(key, value).apply()
    }

    fun getToken(key: String): String? {
        return pref().getString(key, null)
    }

    fun saveExpired(key: String, value: String) {
        edit().putString(key, value).apply()
    }

    fun getExpired(key: String): String? {
        return pref().getString(key, null)
    }


    //    public static void isLogin(String key, boolean value){
    //        edit().putBoolean(key, value).apply();
    //    }
    //
    //    public static boolean getLogin(String key){
    //        return pref().getBoolean(key, false);
    //    }

}
