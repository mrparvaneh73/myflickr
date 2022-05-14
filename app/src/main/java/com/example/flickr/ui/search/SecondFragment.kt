package com.example.flickr.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickr.R
import com.example.flickr.data.adapter.RecyclerAdapter
import com.example.flickr.databinding.FragmentSecondBinding

class SecondFragment: Fragment(R.layout.fragment_second) {
    private val viewModel by viewModels<SecondFragmentViewModel>()
    lateinit var binding: FragmentSecondBinding
    val adapter= RecyclerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentSecondBinding.bind(view)

        binding.recyclerview.layoutManager= GridLayoutManager(view.context,3)
        binding.recyclerview.adapter=adapter

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.getUserFromFirstName(query!!)
                    viewModel.listPhoto.observe(viewLifecycleOwner, Observer {
                       adapter.submitList(it)

                    })

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }
            }
        )



    }




}