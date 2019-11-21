package com.uhc.presentation.joke

import android.util.Log
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
    private val jokeRepo: JokeRepository
) : BaseViewModel() {

    private val TAG = JokesListViewModel::class.java.name

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes

    private val _events = EventLiveData<JokeListEvents>()
    val events: LiveData<JokeListEvents> get() = _events

    init {
        generateRandomNumber().let { randomNumber ->
            subscribeCompletable(
                observable = jokeRepo.loadRemoteRandomJokes(randomNumber)
                    .doOnSubscribe { startProgress() }
                    .doFinally { stopProgress() },
                complete = { Log.d(TAG, "Fetch success") },
                error = { _events.postValue(JokeListEvents.JOKES_ERROR) }
            )

            observeJokes(randomNumber)
        }
    }

    private fun observeJokes(randomNumber: Int) {
        subscribeObservable(
            observable = jokeRepo.observeJokes(randomNumber)
                .doOnSubscribe { startProgress() }
                .doFinally { stopProgress() },
            success = { _jokes.postValue(it) },
            error = { _events.postValue(JokeListEvents.JOKES_ERROR) }
        )
    }

    private fun generateRandomNumber(): Int {
        (8..21).random().let { randomNumber ->
            Log.d(TAG, "Random number generated: $randomNumber")
            return randomNumber
        }
    }
}

enum class JokeListEvents {
    JOKES_ERROR
}