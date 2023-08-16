package com.example.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.Utils.GetWordFromItem
import com.example.roomwordsample.data.Word
import com.example.roomwordsample.databinding.ActivityMainBinding
import com.example.roomwordsample.domain.WordViewModel
import com.example.roomwordsample.ui.adapters.WordListAdapter
import com.google.android.material.snackbar.Snackbar
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
        createAdapter()
    }

    private fun createAdapter() {
        val adapter = WordListAdapter(this)

        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false;
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                wordViewModel.delete((viewHolder as GetWordFromItem).getWord())
            }

        }

        with(binding){
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            ItemTouchHelper(callback).attachToRecyclerView(recyclerview)
            fab.setOnClickListener {
                launchWordActivity()
            }
        }

        wordViewModel.allWords.observe(this) { words ->
            words?.let {list ->
                adapter.submitList(list)
                if(list.isEmpty()){
                    binding.emptyListTextView.visibility = View.VISIBLE
                } else {
                    binding.emptyListTextView.visibility = View.GONE
                }

            }
        }
    }

    private fun launchWordActivity() {
        val intent = Intent(this, NewWordActivity::class.java)
        resultLauncher.launch(intent)
    }

}