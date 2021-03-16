package com.hacybeyker.robolectric

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hacybeyker.robolectric.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonClickMe.setOnClickListener { binding.textMessage.text = "Hello World!" }
        binding.buttonHide.setOnClickListener { it.visibility = View.GONE }
        binding.buttonNewIntent.setOnClickListener { MainActivity2.newInstance(this) }
    }
}