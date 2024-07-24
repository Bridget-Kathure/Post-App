package com.akirachix.postapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.postapp.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val extra = intent.extras
        if(extra!= null){
            postId = extra.getInt("POST_ID", )
            Toast.makeText(this, "$postId", Toast.LENGTH_LONG).show()
        }

    }


    fun fetchPost(postId:Int){
        val apiClient = ApiClient. buildApiInterface(PostAPIinterface::class.java)
        val request = apiClient.fetchPostsById(postId)


        request.enqueue(object : Callback<Post>{
            override fun onResponse(call: Call<Post>, response:Response<Post>){
                if(response.isSuccessful){
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                }
                else{
                    Toast.makeText(this@CommentsActivity, response.errorBody()?)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}