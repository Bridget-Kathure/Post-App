package com.akirachix.postapp

import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.postapp.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()

        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }
    fun fetchPosts(){
        val apiClient =
            ApiClient.buildApiInterface((PostAPIinterface::class.java))
        val request = apiClient.fetchPosts()
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(p0: retrofit2.Call<List<Post>>, p1: Response<List<Post>>) {
                if (p1.isSuccessful){
                    val posts = p1.body()

                    if (posts != null){
                        displayPosts(posts)
                    }
                    Toast.makeText(baseContext, "Fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(baseContext, p1.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: retrofit2.Call<List<Post>>, p1: Throwable) {
                Toast.makeText(baseContext, p1.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun displayPosts(posts: List<Post>){
        val postAdapter = PostAdapter(posts)
       binding.rvPosts.adapter =postAdapter
    }


}