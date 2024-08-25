package com.jibin.retrofitandroid.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jibin.retrofitandroid.models.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    companion object {
        private val TAG = UserViewModel::class.java.simpleName
    }

    private val _postsUser: MutableLiveData<List<User>> = MutableLiveData()
    val postUser: LiveData<List<User>> get() = _postsUser

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun fetchUserData() {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                val fetchDetails = RetrofitInstance.retrofit.getUserPost()
                Log.i(TAG, "fetchUserData: $fetchDetails")
                _postsUser.value = fetchDetails
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching user data", e)
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}
