package com.example.newsapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.model.News
import com.example.newsapp.model.toNews
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel : ViewModel() {

    private var _newsListLiveData = MutableLiveData<List<News>?>()
    var newsListLiveData: LiveData<List<News>?> = _newsListLiveData

    private var _errorLiveData = MutableLiveData<String>()
    var errorLiveData: LiveData<String> = _errorLiveData

    init {
        viewModelScope.launch {
            try {
                val newsList = RetrofitInstance.api.getBreakingNews().body()?.toNews()
                _newsListLiveData.value = newsList
            } catch (e: HttpException) {
                _errorLiveData.value = e.message()
            }
        }
    }
}