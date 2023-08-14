package com.example.roomwordsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.ui.adapters.WordListAdapter
import com.example.roomwordsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = WordListAdapter()
        with(binding) {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}