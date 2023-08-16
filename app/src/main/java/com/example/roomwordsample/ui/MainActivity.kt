package com.example.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwordsample.R
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.ActivityMainBinding
import com.example.roomwordsample.domain.WordViewModel
import com.example.roomwordsample.ui.adapters.WordListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val wordViewModel: WordViewModel by viewModels()

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                with(binding) {
                    val word = Word(reply)
                    wordViewModel.insert(word)
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter()
        with(binding){
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            fab.setOnClickListener {
                launchWordActivity()
            }
        }

        wordViewModel.allWords.observe(this) { words ->
            words.let { adapter.submitList(it) }
        }

    }

    private fun launchWordActivity() {
        val intent = Intent(this, NewWordActivity::class.java)
        resultLauncher.launch(intent)
    }

}