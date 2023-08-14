package com.example.roomwordsample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.RecyclerviewItemBinding

class WordListAdapter: ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        );
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

     inner class WordViewHolder(private val binding: RecyclerviewItemBinding)
         : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String?) {
            binding.textView.text = text
        }

    }

    class WordsComparator: DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }
}