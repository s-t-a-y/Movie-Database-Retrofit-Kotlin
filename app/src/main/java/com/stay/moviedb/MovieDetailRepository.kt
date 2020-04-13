package com.stay.moviedb

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable

class MovieDetailRepository(private val apiService: MovieInterface) {
    private lateinit var movieDetailNetworkData: MovieDetailNetworkData

    fun fetchMovieDetail(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieModel> {
        movieDetailNetworkData = MovieDetailNetworkData(apiService, compositeDisposable)
        movieDetailNetworkData.fetchMovieDetail(movieId)
        return movieDetailNetworkData.downloadedMovieResponse
    }

    fun getMovieDetailNetworkState(): LiveData<NetworkState> {
        return movieDetailNetworkData.networkState
    }
}