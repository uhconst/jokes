package com.uhc.presentation.joke

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uhc.presentation.R
import com.uhc.presentation.databinding.JokesListFragmentBinding
import com.uhc.presentation.ui.base.BaseFragment
import com.uhc.presentation.ui.extensions.observeNotNull
import com.uhc.presentation.utils.observe
import kotlinx.android.synthetic.main.jokes_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Constancio on 2019-11-16.
 */
class JokesListFragment : BaseFragment<JokesListFragmentBinding>() {
    val viewModel: JokesListViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.jokes_list_fragment

    private val jokeAdapter by inject<JokeAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        viewModel.fetchRemoteJokes()

        setupJokesList()
        setupToobar(toolbar)

        observeData()
    }

    private fun setupJokesList() {
        binding.rvJokes.layoutManager = LinearLayoutManager(
            this.context,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvJokes.adapter = jokeAdapter
    }

    private fun observeData() {
        viewModel.jokes.observeNotNull(this) {
            jokeAdapter.notifyChanged(it)
        }

        viewModel.events.observe(this) { event ->
            when (event) {
                JokeListEvents.JOKES_ERROR -> {
                    Toast.makeText(
                        this.context,
                        R.string.message_jokes_error,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}