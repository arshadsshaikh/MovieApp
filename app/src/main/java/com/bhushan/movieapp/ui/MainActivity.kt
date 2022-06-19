package com.bhushan.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhushan.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MovieApp)
        setContentView(R.layout.activity_main)
    }
}