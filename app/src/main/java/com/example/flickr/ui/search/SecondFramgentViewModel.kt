package com.example.flickr.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.R
import com.example.flickr.data.remotedatasource.NetworkManager
import com.example.flickr.databinding.FragmentSecondBinding
import com.example.flickr.model.GetPhoto
import com.example.flickr.model.SearchItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondFragmentViewModel: ViewModel() {
    private val _listPhoto = MutableLiveData<List<SearchItems>>()
    val listPhoto: LiveData<List<SearchItems>> = _listPhoto
    fun searchphoto(text:HashMap<String,String>){
        NetworkManager.service.search(text)
            .enqueue(object : Callback<GetPhoto?> {
                override fun onResponse(call: Call<GetPhoto?>, response: Response<GetPhoto?>) {
                    _listPhoto.postValue(response.body()?.photos?.photo?.map {
                        SearchItems(it.title,it.url_s)
                    })
                }

                override fun onFailure(call: Call<GetPhoto?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    fun getUserFromFirstName(text:String) {
        if (text.isNotBlank()){
            searchphoto( hashMapOf("text" to text))
        }

    }
}
