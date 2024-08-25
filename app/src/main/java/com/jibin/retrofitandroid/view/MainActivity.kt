package com.jibin.retrofitandroid.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jibin.retrofitandroid.adapters.PostAdapter
import com.jibin.retrofitandroid.view_models.MainViewModel
import com.jibin.retrofitandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //To Initialize and Configure recyclerView Adapter
        adapter = PostAdapter(this, listOf())
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.fetchAndLogPosts()


        viewModel.posts.observe(this) { posts ->
            Log.i(TAG, "Number of posts: ${posts.size}")
            adapter.updatePosts(posts)
        }

        binding.user.setOnClickListener {
            val intent = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(intent)
        }
    }
}