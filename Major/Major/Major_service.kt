package com.example.yujie.Major


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//const val ORIGINAL_URL = "http://10.0.2.2:8080/aubweekend2/read.php?api_key=kosal"
const val PRODUCT_BASE_URL = "http://10.0.2.2"

interface MajorService {

    @GET("Major/read.php")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = "MengLy"
    ): Major

    companion object {
        private var service: MajorService? = null
        fun getInstance(): MajorService {
            if (service == null) {
                service = Retrofit.Builder()
                    .baseUrl(PRODUCT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MajorService::class.java)
            }
            return service!!
        }
    }
}

