package com.example.home.Recommendation.ui.Tourist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.function.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TouristRecommendationVm : ViewModel() {
    var newresponse: List<PostResponse>? = emptyList()
    fun getrec(cityName:String, cityLocation:String, cityRange:String): List<PostResponse>?
    {  print("hehe getrec")
        viewModelScope.launch((Dispatchers.IO)){
             val service=PostService.create()
            if (cityLocation.isNotEmpty() && cityName.isNotEmpty() && cityRange.isNotEmpty()) {
                try {

                        withContext(Dispatchers.IO){
                            val postResponse = service.createPost(cityName=cityName, postRequest = cityLocation, cityRange=cityRange)
                            withContext(Dispatchers.Main){
                               newresponse=postResponse
                            }
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                // Handle the case where any of the variables are empty
                // Maybe show an error message or log a warning
            }
        }
        return newresponse
    }
}

