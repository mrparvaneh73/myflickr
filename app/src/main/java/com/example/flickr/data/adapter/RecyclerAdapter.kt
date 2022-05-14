package com.example.flickr.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.R
import com.example.flickr.databinding.ListItemBinding
import com.example.flickr.model.SearchItems

class RecyclerAdapter() : ListAdapter<SearchItems, RecyclerAdapter.MyViewHolder>(DiffCallback()) {

    class MyViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun fill(photo: SearchItems) {
            binding.image = photo

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fill(getItem(position))

    }

}

class DiffCallback : DiffUtil.ItemCallback<SearchItems>() {
    override fun areItemsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: SearchItems, newItem: SearchItems): Boolean {
        return oldItem == newItem
    }
}