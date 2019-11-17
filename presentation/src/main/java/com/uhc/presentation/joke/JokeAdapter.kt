package com.uhc.presentation.joke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uhc.domain.model.Joke
import com.uhc.presentation.R
import com.uhc.presentation.databinding.JokesListItemBinding

/**
 * Created by Constancio on 2019-11-17.
 */
class JokeAdapter : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    private val jokes = mutableListOf<Joke>()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.jokes_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = jokes.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    fun notifyChanged(jokes: List<Joke>) {
        this.jokes.clear()
        this.jokes.addAll(jokes)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: JokesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke) {
            binding.joke = joke
            binding.executePendingBindings()
        }
    }
}