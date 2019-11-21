package com.uhc.presentation.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Version of `LiveData.observe` which allows using lambda expression
 * to define [onChange] event action.
 *
 * Example usage:
 * ```
 * viewModel.someProperty.observe(this) { value ->
 *     doSomething()
 * }
 * ```
 *
 * @return The created `Observer`.
 */
inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChange: (T?) -> Unit
): Observer<T> {
    val observer = Observer<T> { onChange(it) }
    observe(owner, observer)
    return observer
}