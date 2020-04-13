package com.stay.moviedb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailNetworkData(
    private val apiService: MovieInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedMovieDetailResponse = MutableLiveData<MovieModel>()
    val downloadedMovieResponse: LiveData<MovieModel>
        get() = _downloadedMovieDetailResponse

    fun fetchMovieDetail(movieId: Int) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getMovieDetail(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieDetailResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailNetworkData", it.toString())
                        }
                    )
            )
        } catch (e: Exception) {
            Log.e("MovieDetailNetworkData", e.toString())
        }
    }
}