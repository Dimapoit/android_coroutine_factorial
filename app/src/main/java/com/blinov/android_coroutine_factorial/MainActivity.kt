package com.blinov.android_coroutine_factorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.blinov.android_coroutine_factorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private  val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.editTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {

        viewModel.progress.observe(this) {

            if (it) {
                binding.progressBar.progress = View.VISIBLE
                binding.buttonCalculate.isEnabled = false
            } else {
                binding.progressBar.progress = View.GONE
                binding.buttonCalculate.isEnabled = true
            }
        }
        viewModel.error.observe(this) {
            if (it) {

                Toast.makeText(
                    this,
                    "You did not entered value",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.factorial.observe(this) {
            Log.d("MainActivity", "text ${it.toString()}")
            binding.tvFactorial.text = it.toString()
        }
    }
}