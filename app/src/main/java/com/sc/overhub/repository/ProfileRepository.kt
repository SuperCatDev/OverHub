package com.sc.overhub.repository

import android.content.Context

class ProfileRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun getBattleTag(): String? = sharedPreferences.getString(battleTagKey, "")

    fun setBattleTag(battleTag: String): Boolean {
        if (!validateTag(battleTag)) return false

        sharedPreferences.edit().putString(battleTagKey, battleTag).apply()

        return true
    }

    companion object {
        const val prefName = "ProfileSetup"
        const val battleTagKey = "BattleTag"

        fun validateTag(battleTag: String): Boolean =
            battleTag.matches(Regex(".+#\\d+"))
    }
}