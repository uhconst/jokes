package com.uhc.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.uhc.domain.repository.JokeRepository
import com.uhc.presentation.joke.JokesListViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Constancio on 2019-11-20.
 */
class JokesListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val jockRepositoryMock = mock<JokeRepository>()

    lateinit var viewModel: JokesListViewModel

    @Before
    fun `Setup test`() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        viewModel = JokesListViewModel(
            jockRepositoryMock
        )
    }

    @Test
    fun `Test generateRandomNumber() when success`() {
        assertTrue(viewModel.generateRandomNumber() in viewModel.jokesRange)
    }

    @Test
    fun `Test generateRandomNumber() when fail`() {
        assertFalse(viewModel.generateRandomNumber() !in viewModel.jokesRange)
    }
}