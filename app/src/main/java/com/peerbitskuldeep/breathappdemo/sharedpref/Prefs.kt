package com.peerbitskuldeep.breathappdemo.sharedpref

import android.app.Activity
import android.content.SharedPreferences
import com.peerbitskuldeep.breathappdemo.MainActivity
import java.util.*

class Prefs(activity: Activity) {

    private var sharedP: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    fun setDate(milliseconds: Long) {
        sharedP.edit().putLong("seconds", milliseconds).apply()
    }

    fun getDate(): String
    {
        val milliDate = sharedP.getLong("seconds",0)
        val amOrPm: String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)

        if (a == Calendar.AM)
        {
            amOrPm = "AM"
        }
        else
        {
            amOrPm = "PM"
        }

        val time = "Last ${calendar.get(Calendar.HOUR_OF_DAY)} : "+
                "${calendar.get(Calendar.MINUTE)} "+ "$amOrPm"

        return time
    }

    //getter setter way
    var sessions: Int
    get() = sharedP.getInt("sessions",0)
    set(session) = sharedP.edit().putInt("sessions",session).apply()

    var breaths: Int
    get() = sharedP.getInt("breath",0)
    set(breath) = sharedP.edit().putInt("breath",breath).apply()

}