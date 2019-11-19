package com.uhc.presentation.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uhc.domain.model.Joke
import com.uhc.domain.repository.JokeRepository
import com.uhc.presentation.ui.base.BaseViewModel
import com.uhc.presentation.utils.EventLiveData

/**
 * Created by Constancio on 2019-11-17.
 */
class JokesListViewModel(
    jokeRepo: JokeRepository
) : BaseViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val _events = EventLiveData<JokeListEvents>()
    val events: LiveData<JokeListEvents> get() = _events

    init {
        subscribeCompletable(
            observable = jokeRepo.loadRandomJokes(5) // TODO: Random value
                .doOnSubscribe { startProgress() }
                .doFinally { stopProgress() },
            error = { _events.postValue(JokeListEvents.JOKES_ERROR) }
        )

        subscribeObservable(
            observable = jokeRepo.observeJokes(5)// TODO: Random value
                .doOnSubscribe { startProgress() }
                .doFinally { stopProgress() },
            success = { _jokes.postValue(it) },
            error = { _events.postValue(JokeListEvents.JOKES_ERROR) }
        )
    }
}

enum class JokeListEvents {
    JOKES_ERROR
}