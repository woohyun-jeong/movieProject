package com.whjung.movieproject.security

import android.annotation.SuppressLint
import android.os.Build
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


object RoutingCheck {
    fun checkSuperUser(): Boolean {
        return checkRooedFiles() || checkSuperUserCommandFirst() || checkSuperUserCommandSecond() || checkTags()
    }

    private fun checkRooedFiles(): Boolean {
        @SuppressLint("SdCardPath") val files = arrayOf(
            "/sbin/su",
            "/system/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/system/xbin/mu",
            "/system/bin/.ext/.su",
            "/system/usr/su-backup",
            "/data/data/com.noshufou.android.su",
            "/system/app/Superuser.apk",
            "/system/app/su.apk",
            "/system/bin/.ext",
            "/system/xbin/.ext",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (i in files.indices) {
            val file = File(files[i])
            if (file.exists()) {
                return true
            }
        }
        return false
    }

    private fun checkTags(): Boolean {
        val buidTags = Build.TAGS
        return buidTags != null && buidTags.contains("test-keys")
    }

    private fun checkSuperUserCommandFirst(): Boolean {
        try {
            Runtime.getRuntime().exec("su")
            return true
        } catch (e: Error) {
        } catch (e: Exception) {
        }
        return false
    }

    private fun checkSuperUserCommandSecond(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime()
                .exec(arrayOf("/system/xbin/which", "su"))
            val `in` =
                BufferedReader(InputStreamReader(process.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }
}