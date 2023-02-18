package com.ipacsystemtest.custom

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import com.ipacsystemtest.R
import com.ipacsystemtest.utils.Utility

class CustomProgressDialog(context: Context) {
    private var mDialog: Dialog
    private var mTxtMessage: TextView? = null
    private var context: Context

    init {
        this.context = context
        mDialog = Dialog(context, R.style.style_new_dialog)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val mInflater = LayoutInflater.from(context)
        mDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val layout: View = mInflater.inflate(R.layout.custom_progress_dialog, null)
        val mProgress = layout.findViewById<ProgressBar>(R.id.progress)
        mProgress.indeterminateDrawable.setColorFilter(
            context.resources.getColor(R.color.purple_200),
            PorterDuff.Mode.MULTIPLY
        )
        mDialog.setContentView(layout)
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.setCancelable(false)
        mTxtMessage = mDialog.findViewById(R.id.tvProgressMessage)
    }

    fun showProgress(message: String) {
        if (Utility.isValueNullOrEmpty(message)) {
            mTxtMessage!!.visibility = View.GONE
        } else {
            mTxtMessage!!.text = "" + message
        }
        if (mDialog != null) {
            mDialog.show()
        }
    }

    fun dismissProgress() {
        if (mDialog != null || mDialog.isShowing) {
            mDialog.dismiss()
        }
    }
}