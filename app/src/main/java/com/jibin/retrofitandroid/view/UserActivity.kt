package com.jibin.retrofitandroid.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jibin.retrofitandroid.databinding.ActivityUserBinding
import com.jibin.retrofitandroid.adapters.UserAdapter
import com.jibin.retrofitandroid.models.User
import com.jibin.retrofitandroid.view_models.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userAdapter: UserAdapter
    private var userNew = mutableListOf<User>()
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(this@UserActivity, userNew)
        binding.userRecView.adapter = userAdapter
        binding.userRecView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.fetchUserData()

        viewModel.postUser.observe(this) { user ->
            userAdapter.updateUserData(user)
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}