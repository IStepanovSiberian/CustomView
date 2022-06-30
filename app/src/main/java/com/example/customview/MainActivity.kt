package com.example.customview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.customview.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onCreate")
        initMock()
    }

    private fun initMock() {
        with(binding.message) {
            username = resources.getString(R.string.message_sample_username)
            message = resources.getString(R.string.message_sample_text)
            timestamp = resources.getString(R.string.message_sample_timestamp)
            avatar = R.drawable.paul
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Timber.tag("Lifecycle: ").e("НОВЫЕ ДАННЫЕ ПРИШЛИ")
            with(binding.message) {
                username = resources.getString(R.string.message_sample_username2)
                message = resources.getString(R.string.message_sample_text2)
                timestamp = resources.getString(R.string.message_sample_timestamp2)
                avatar = null
            }
        }, 3000)
    }

    override fun getLayoutInflater(): LayoutInflater {
        Timber.tag("Lifecycle: MainActivity").d("Activity - getLayoutInflater")
        return super.getLayoutInflater()
    }

    override fun setContentView(view: View?) {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - setContentView")
        super.setContentView(view)
    }

    override fun onStart() {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onStop")
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Timber.tag("Lifecycle: MainActivity").d("AppCompatActivity - onCreate")
        super.onCreate(savedInstanceState, persistentState)
    }
}
