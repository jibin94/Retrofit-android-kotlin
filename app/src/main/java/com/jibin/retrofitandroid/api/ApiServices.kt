package com.jibin.retrofitandroid.api

import com.jibin.retrofitandroid.models.Post
import com.jibin.retrofitandroid.models.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int = 1,
        @Query("_limit") limit: Int = 10
    ): List<Post>

    @GET("users")
    suspend fun getUserPost(): List<User>
}