package com.example.flickr.ui.home

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.R
import com.example.flickr.data.adapter.RecyclerAdapter
import com.example.flickr.databinding.FragmentFirstBinding

class FirstFragment : Fragment(R.layout.fragment_first) {
    private var isScrolling = false
    private val viewmodel by viewModels<FirstFragmentViewmodel>()
    lateinit var binding: FragmentFirstBinding
    lateinit var manager: LinearLayoutManager
    var adapter = RecyclerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFirstBinding.bind(view)
        binding.recyclerview.adapter = adapter
        manager = LinearLayoutManager(view.context)
        binding.recyclerview.layoutManager = manager
        viewmodel.getPhoto()
        viewmodel.getphoto.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentItem = manager.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val scrollOutItem = manager.findFirstCompletelyVisibleItemPosition()
                if (isScrolling && ((currentItem + scrollOutItem) >= totalItemCount!!)) {
                    isScrolling = false
                    viewmodel.getPhoto()

                }
            }
        })
    }

}