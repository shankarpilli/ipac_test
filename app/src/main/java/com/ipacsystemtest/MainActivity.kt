package com.ipacsystemtest


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ipacsystemtest.adapters.UsersListAdapter
import com.ipacsystemtest.custom.CustomProgressDialog
import com.ipacsystemtest.databinding.ActivityMainBinding
import com.ipacsystemtest.livedata.models.UserModel
import com.ipacsystemtest.rest.api.data.UsersList
import com.ipacsystemtest.rest.features.UsersListViewModel
import com.ipacsystemtest.utils.Utility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var customProgressDialog: CustomProgressDialog
    private lateinit var layoutManager: LinearLayoutManager
    var page: Int = 1
    private lateinit var adapter: UsersListAdapter
    private var users: ArrayList<UserModel> = ArrayList()
    private val viewModel: UsersListViewModel by viewModels()
    var isLoading: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.hide()
        initUi()
    }

    private fun initUi() {
        page = 1
        customProgressDialog = CustomProgressDialog(this)
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding?.rlUsersList?.layoutManager = layoutManager
        adapter = UsersListAdapter(ArrayList(), this@MainActivity)
        binding?.rlUsersList?.adapter = adapter
        viewModel.usersList.observe(this@MainActivity) {
            customProgressDialog.dismissProgress()
            if (it != null) {
                displayList(it)
            }
        }
        binding?.rlUsersList?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        page++
                        getUserList(page * 20)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        getUserList(page * 20)
    }

    /**
     * This function is used to display movies list
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun displayList(usersList: UsersList) {
        Log.d("usersList", "" + usersList.results.size)
        if (usersList.results.isNotEmpty()) {
            adapter.setData(usersList.results)
        } else {
            Utility.showToastMessage(this@MainActivity, "No Users found")
        }
        val userModel  = UserModel()
        usersList.results.forEachIndexed { _, user ->
            userModel.user_name = user.name.first + " "+user.name.first
            userModel.user_gender = user.gender.toString()
            userModel.user_location = user.location?.city+ " "+user.location?.state
            userModel.user_picture = user.picture.large
            users.add(userModel)
        }
        //viewModel.addAllItem(users)
    }


    /**
     * This function is used to get movies list
     */
    private fun getUserList(int: Int) {
        isLoading = false
        customProgressDialog.showProgress("")
        viewModel.getUsersListRequest(
            int
        )
        viewModel.getUsersListData()
    }
}
