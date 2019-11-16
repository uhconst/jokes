package com.uhc.presentation.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.uhc.domain.exception.DefaultException
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Constancio on 26/01/19.
 */
abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val isProgress = ObservableBoolean()

    protected fun startProgress() {
        isProgress.set(true)
    }

    protected fun stopProgress() {
        isProgress.set(false)
    }

    protected fun <T> subscribeSingle(
        observable: Single<T>,
        success: ((T) -> Unit)? = null,
        error: ((DefaultException) -> Unit)? = null
    ): Disposable {
        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                success,
                {
                    when (it) {
                        is DefaultException -> error?.invoke(it)
                        else -> error?.invoke(DefaultException())
                    }
                }
            )

        compositeDisposable.add(disposable)

        return disposable
    }

    protected fun subscribeCompletable(
        observable: Completable,
        complete: (() -> Unit)? = null,
        error: ((DefaultException) -> Unit)? = null
    ): Disposable {
        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                complete,
                {
                    when (it) {
                        is DefaultException -> error?.invoke(it)
                        else -> error?.invoke(DefaultException())
                    }
                }
            )

        compositeDisposable.add(disposable)

        return disposable
    }

    protected fun dispose(disposable: Disposable?) {
        disposable?.let { compositeDisposable.remove(it) }
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}