package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.BottomFragment
import com.example.myapplication.ui.TopFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView1.id, TopFragment())
            commit()
        }
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainerView2.id, BottomFragment())
            commit()
        }
    }
}