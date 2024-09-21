package com.example.yujie.Major




import com.google.gson.annotations.SerializedName
import java.util.Date


data class Major (

    @SerializedName("success") var success : Boolean,
    @SerializedName("records") var records : List<Records>

)

data class Records (
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title : String,
    @SerializedName("release") var release : String,
    @SerializedName("time") var time : String,
    @SerializedName("type") var type : String,
    @SerializedName("image") var image : String

)