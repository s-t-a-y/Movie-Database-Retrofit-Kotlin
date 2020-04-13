package com.stay.moviedb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieDetailViewModel(private val movieRepository: MovieDetailRepository, movieId: Int) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDetail: LiveData<MovieModel> by lazy {
        movieRepository.fetchMovieDetail(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}