package com.example.flickr.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickr.data.remotedatasource.NetworkManager
import com.example.flickr.model.GetPhoto
import com.example.flickr.model.SearchItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstFragmentViewmodel(): ViewModel() {
    private var per_page=10
    private val _getphoto = MutableLiveData<List<SearchItems>>()
    val getphoto: LiveData<List<SearchItems>> = _getphoto
    fun getPhotoFromServer(page:HashMap<String,Int>){
        NetworkManager.service.getphoto(page).enqueue(object : Callback<GetPhoto?> {
            override fun onResponse(call: Call<GetPhoto?>, response: Response<GetPhoto?>) {
                if (response.isSuccessful){
                    _getphoto.postValue(response.body()?.photos?.photo?.map {
                        SearchItems(it.title,it.url_s)
                    })
                }
            }
            override fun onFailure(call: Call<GetPhoto?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getPhoto() {
        per_page+=10
        getPhotoFromServer( hashMapOf("per_page" to per_page ))
    }



}