package com.ipacsystemtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ipacsystemtest.R
import com.ipacsystemtest.databinding.ActivityDetailViewBinding
import com.ipacsystemtest.rest.api.data.UsersList
import com.squareup.picasso.Picasso

class DetailViewActivity : AppCompatActivity() {

    private var binding: ActivityDetailViewBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.hide()
        initUi()
    }

    private fun initUi() {
        intent.getStringExtra("user_name")?.let { binding?.txtName?.text = it }
        intent.getStringExtra("user_gender")?.let { binding?.txtGender?.text = it }
        intent.getStringExtra("user_location")?.let { binding?.txtLocation?.text = it }


        intent.getStringExtra("user_picture")?.let {  Picasso.get()
            .load(it)
            .into(binding?.imgUserImage)}
    }


}