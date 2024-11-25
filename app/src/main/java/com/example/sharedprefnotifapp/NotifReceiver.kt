package com.example.sharedprefnotifapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action

        if (action == "ACTION_SHOW_MESSAGE") {
            val msg = intent.getStringExtra("MESSAGE")
            if (msg != null) {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            }
        } else if (action == "ACTION_LOGOUT") {
            val prefManager = PrefManager.getInstance(context!!)
            prefManager.clear() // Hapus data username dari SharedPreferences
            val msgLogout = intent.getStringExtra("MESSAGE_LOGOUT")
            if (msgLogout != null) {
                Toast.makeText(context, msgLogout, Toast.LENGTH_LONG).show()
            }

            // Intent untuk memperbarui UI
            val mainIntent = Intent(context, MainActivity::class.java)
            mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(mainIntent)
        }
    }
}
