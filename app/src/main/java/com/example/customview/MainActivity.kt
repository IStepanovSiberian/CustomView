package com.example.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMock()
    }

    private fun initMock() {
        with(binding.message) {
            username = resources.getString(R.string.message_sample_username)
            message = resources.getString(R.string.message_sample_text)
            timestamp = resources.getString(R.string.message_sample_timestamp)
            avatar = R.drawable.paul
        }
    }
}
