package com.whjung.movieproject.data

import android.content.Context
import com.google.gson.Gson

class PreferenceProvider(context: Context, val gson: Gson) : PreferenceManager(context) {

    companion object {
        const val KEY_EXAMPLE = "example"
    }

    fun putPrefString(key: String, value: String?) { encryptedSharedPreferences?.edit()?.putString(key, value)?.apply() }
    fun getPrefString(key: String, default: String = "") = encryptedSharedPreferences?.getString(key, default) ?: ""
    fun clearPrefString(key: String) = putPrefString(key, "")

    fun putPrefInt(key: String, value: Int) { encryptedSharedPreferences?.edit()?.putInt(key, value)?.apply() }
    fun getPrefInt(key: String) = encryptedSharedPreferences?.getInt(key, -1) ?: -1
    fun clearPrefInt(key: String) = putPrefInt(key, -1)

    fun putPrefBool(key: String, value: Boolean) { encryptedSharedPreferences?.edit()?.putBoolean(key, value)?.apply() }
    fun getPrefBool(key: String) = encryptedSharedPreferences?.getBoolean(key, false) ?: false
    fun clearPrefBool(key: String) = putPrefBool(key, false)

    fun putGsonPrefString(key: String, value: Any) { putPrefString(key, gson.toJson(value)) }
    fun<T> getGsonPrefString(key: String, classOfT: Class<T>) = gson.fromJson(getPrefString(key), classOfT)

    fun clearData() { encryptedSharedPreferences?.edit()?.clear()?.apply() }
}