package com.uhc.presentation.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * An implementation of `LiveData`, dispatching one-time events.
 */
class EventLiveData<T> : LiveData<T>() {

    private val pendingEvents = MutableLiveData<List<T>>()

    /** Always returns null. */
    override fun getValue(): T? = null

    /** Posts a new event. */
    public override fun postValue(value: T) {
        synchronized(pendingEvents) {
            pendingEvents.postValue(listOfNotNull(pendingEvents.value, listOf(value)).flatten())
        }
    }

    /**
     * Adds the given observer to the observers list within the lifespan of the given owner.
     * The events are dispatched on the main thread.
     * If `LiveData` already contains events, they will be delivered to the observer.
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        removeObservers(owner)
        pendingEvents.observe(owner, Observer { handleEvents(it, observer) })
    }

    private fun handleEvents(
        events: List<T>?,
        observer: Observer<in T>
    ) {
        if (events?.isNotEmpty() == true) {
            synchronized(pendingEvents) {
                for (event in events) {
                    observer.onChanged(event)
                }
                pendingEvents.postValue(emptyList())
            }
        }
    }

    /**
     * Adds the given observer to the observers list.
     * This call is similar to [observe] with a `LifecycleOwner`, which is always active.
     */
    override fun observeForever(observer: Observer<in T>) {
        pendingEvents.observeForever { handleEvents(it, observer) }
    }

    /** Removes all observers that are tied to the given lifecycle [owner]. */
    override fun removeObservers(owner: LifecycleOwner) {
        pendingEvents.removeObservers(owner)
    }
}
