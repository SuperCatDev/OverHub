package com.sc.overhub.view.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.sc.overhub.R
import com.sc.overhub.view.activity.LaunchActivity

class BattleTagDialogFragment : DialogFragment() {
    lateinit var hostActivity: LaunchActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is LaunchActivity) {
            hostActivity = context
        } else {
            throw ClassCastException("WTF")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView =
            hostActivity.layoutInflater.inflate(R.layout.battle_tag_dialog_layout, null)

        val battleTag: EditText = dialogView.findViewById(R.id.battleTag_et)

        val dialog = android.app.AlertDialog.Builder(hostActivity).apply {
            setView(dialogView)
            setPositiveButton("Apply") { dialog, which ->
                val hostActivity: LaunchActivity = hostActivity

                if (hostActivity.repo.setBattleTag(battleTag.text.toString())) {
                    dialog.cancel()
                    hostActivity.startApp()
                    Log.e("LaunchActivity", "IN")
                }
                Log.e("LaunchActivity", "Out")
            }
            setCancelable(false)
        }

        return dialog.create()
    }
}