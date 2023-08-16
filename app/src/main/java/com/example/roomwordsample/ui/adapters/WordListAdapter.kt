package com.example.roomwordsample.ui.adapters

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.Utils.GetWordFromItem
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.RecyclerviewItemBinding
import com.example.roomwordsample.ui.MainActivity
import com.google.android.material.snackbar.Snackbar

class WordListAdapter(private val parent: MainActivity): ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

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
         : RecyclerView.ViewHolder(binding.root), GetWordFromItem {

         fun bind(word: String) {
             binding.textView.text = word
         }

         override fun getWord(): String {
             return binding.textView.text.toString();
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