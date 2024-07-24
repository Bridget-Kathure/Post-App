package com.akirachix.postapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.postapp.databinding.PostsListItemBinding
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle.parent

class PostAdapter (var postsList: List<Post>, val context: Context): RecyclerView.Adapter<PostsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postsList[position]
        holder.binding.apply {
            tvBody.text = post.body
            tvTittle.text = post.title
            clPost.setOnClickListener{
                val intent = Intent(context , CommentsActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }
}

class  PostsViewHolder(val binding: PostsListItemBinding):
    RecyclerView.ViewHolder(binding.root)