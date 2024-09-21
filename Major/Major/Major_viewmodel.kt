package com.example.yujie.Major

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yujie.Legend.LegendService
import com.example.yujie.mo.MajorItem
import com.example.yujie.model.CinemaItem


import kotlinx.coroutines.launch

class MajorViewModel : ViewModel() {
    private val _majorList = mutableStateListOf<Records>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val majorList: List<Records>
        get() = _majorList


    fun getMajorResultList() {
        viewModelScope.launch {
            isLoading = true
            val service = MajorService.getInstance()
            try{
                _majorList.clear()
                _majorList.addAll(service.getMovie().records)
            } catch (e: Exception){
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
    private val _favoriteItems = mutableStateListOf<MajorItem>()
    val favoriteItems: List<MajorItem> get() = _favoriteItems

    fun toggleFavorite(item: MajorItem) {
        if (_favoriteItems.contains(item)) {
            _favoriteItems.remove(item) // Remove if already a favorite
        } else {
            _favoriteItems.add(item) // Add if not a favorite
        }
    }
    var isGridMode: MutableState<Boolean> = mutableStateOf(true)
    fun toggleMode() {
        isGridMode.value = !isGridMode.value
    }
}