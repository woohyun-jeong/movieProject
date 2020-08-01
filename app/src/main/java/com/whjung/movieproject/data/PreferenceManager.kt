package com.whjung.movieproject.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


@RequiresApi(api = Build.VERSION_CODES.M)
open class PreferenceManager(val context: Context) {
    protected val encryptedSharedPreferences: SharedPreferences? by lazy {
        val spec = KeyGenParameterSpec.Builder(
            "_androidx_security_master_key_",
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(256)
            .build()


        val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()

        return@lazy EncryptedSharedPreferences.create(
            context,
            "test_secret_shared_prefs",
            masterKey, // masterKey created above
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        return@lazy null
    }
}