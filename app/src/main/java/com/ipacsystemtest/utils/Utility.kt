package com.ipacsystemtest.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


object Utility {
    fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null && activity.window.decorView != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }


    /**
     * Shows toast message
     *
     * @param context Context of the class
     * @param message What message you have to show
     */
    fun showToastMessage(context: Context?, message: String?) {
        try {
            if (!isValueNullOrEmpty(message) && context != null) {
                val toast = Toast.makeText(
                    context.applicationContext, message,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Check the value is null or empty
     *
     * @param value Value of that string
     * @return Boolean returns the value true or false
     */
    fun isValueNullOrEmpty(value: String?): Boolean {
        var isValue = false
        if (value == null || value == "" || value == "0.0" || value == "null" || value.trim { it <= ' ' }.length == 0) {
            isValue = true
        }
        return isValue
    }

}