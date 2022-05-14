package com.example.flickr.data.remotedatasource

import com.example.flickr.model.GetPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ImageService{
    @GET("services/rest")
    fun getphoto(@QueryMap text:HashMap<String,Int> = hashMapOf(),
                 @Query("api_key") api_key:String="1c04e05bce6e626247758d120b372a73",
                 @Query("method") method:String="flickr.photos.getPopular",
                 @Query("user_id") user_id:String="34427466731@N01",
                 @Query("extras") extras:String="url_s",
                 @Query("format") format:String="json",
                 @Query("nojsoncallback") nojsoncallback:String="1",
//             @Query("per_page") per_page:String="10"
    ): Call<GetPhoto>
//:LiveData<Resource<GetPhoto>>

    @GET("services/rest")
    fun search(
        @QueryMap text:HashMap<String,String> = hashMapOf(),
        @Query("api_key") api_key: String = "1c04e05bce6e626247758d120b372a73",
        @Query("method") method: String = "flickr.photos.search",
        @Query("extras") extras: String = "url_s",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ) : Call<GetPhoto>



}