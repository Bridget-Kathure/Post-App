package com.akirachix.postapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostAPIinterface {
    @GET("/posts")
    fun fetchPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun  fetchPostsById(@Path("postId") postId: Int): Call<Post>
}