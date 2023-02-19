package com.ipacsystemtest.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipacsystemtest.MainActivity
import com.ipacsystemtest.R
import com.ipacsystemtest.activities.DetailViewActivity
import com.ipacsystemtest.rest.api.data.UsersList
import com.squareup.picasso.Picasso

class UsersListAdapter(
    private var results: ArrayList<UsersList.User>,
    mainActivity: MainActivity
) :
    RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    var activity: MainActivity? = null

    init {
        activity = mainActivity
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(usersData: ArrayList<UsersList.User>) {
        results.addAll(usersData)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_user_name: TextView
        val txt_gender: TextView
        val txt_location: TextView
        val img_user: ImageView
        val ll_item: LinearLayout

        init {
            txt_user_name = view.findViewById<TextView>(R.id.txt_user_name)
            txt_gender = view.findViewById<TextView>(R.id.txt_gender)
            txt_location = view.findViewById<TextView>(R.id.txt_location)
            img_user = view.findViewById<ImageView>(R.id.img_user)
            ll_item = view.findViewById<LinearLayout>(R.id.ll_item)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.users_item, viewGroup, false)

        return ViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.txt_user_name.text = results[position].name.first + " " + results[position].name.last
        viewHolder.txt_gender.text = results[position].gender
        viewHolder.txt_location.text = results[position].location?.city + " " +  results[position].location?.state
        Picasso.get()
            .load(results[position].picture.large)
            .into(viewHolder.img_user)
        viewHolder.ll_item.setOnClickListener {
            val intent = Intent(activity, DetailViewActivity::class.java)
            intent.putExtra("user", results[position])
            activity?.startActivity(intent)
        }
    }

    override fun getItemCount() = results.size

}