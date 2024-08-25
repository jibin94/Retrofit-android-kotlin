package com.jibin.retrofitandroid.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jibin.retrofitandroid.models.Post
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> get() = _posts

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchAndLogPosts(page: Int = 1, limit: Int = 10) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                val fetchedPosts = RetrofitInstance.retrofit.getPosts(page, limit)
                Log.i(TAG, "Number of posts fetched: ${fetchedPosts.size}")
                _posts.value = fetchedPosts
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching posts", e)
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}
