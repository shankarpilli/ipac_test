package com.ipacsystemtest.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        val user = intent.getSerializableExtra("user") as? UsersList.User
        binding?.txtName?.text = user?.name?.first + " " + user?.name?.last
        binding?.txtGender?.text = user?.gender
        binding?.txtLocation?.text = user?.location?.city + " " + user?.location?.state
        Picasso.get()
            .load(user?.picture?.large)
            .into(binding?.imgUserImage)
    }


}