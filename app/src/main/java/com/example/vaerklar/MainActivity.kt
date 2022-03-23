package com.example.vaerklar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.vaerklar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchWeatherData()
        viewModel.fetchLocationData()

        viewModel.getWeatherData().observe(this) {
            println(it)
        }

        viewModel.getLocationData().observe(this) {
            println(it)
        }
    }
}