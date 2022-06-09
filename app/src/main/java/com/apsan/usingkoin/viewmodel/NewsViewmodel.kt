package com.apsan.usingkoin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apsan.usingkoin.models.Articles
import com.apsan.usingkoin.repo.Repository
import com.apsan.usingkoin.utils.LoadingState
import kotlinx.coroutines.launch

class NewsViewmodel(
    val repository: Repository
) : ViewModel() {

    val TAG = "tag"
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData(ArrayList<Articles>())
    val data: LiveData<ArrayList<Articles>>
        get() = _data

    init {
        viewModelScope.launch {
            getTopNewsFromRepo()
        }
    }

    suspend fun getTopNewsFromRepo(q : String? = null) {

        Log.d(TAG, "getting news: ")

        _loadingState.value = LoadingState.LOADING

        val response = if (q.isNullOrEmpty()) {
            repository.getTopNews()
        } else {
            repository.getTopNewsSearch(q)
        }
        if (response.isNullOrEmpty()){
            Log.d(TAG, "getTopNewsFromRepo: response is null")
            _loadingState.value = LoadingState.error("some error...")
        }else{
            _data.value = response!!
            _loadingState.value = LoadingState.LOADED
        }
    }

}