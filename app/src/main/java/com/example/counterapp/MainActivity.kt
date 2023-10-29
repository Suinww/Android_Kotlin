package com.example.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberTextView = findViewById<TextView>(R.id.numberTextView)
        val resetbtn = findViewById<Button>(R.id.resetbtn)
        val countbtn = findViewById<Button>(R.id.countbtn)

        var number = 0

        resetbtn.setOnClickListener {
            number = 0
            numberTextView.text = number.toString()
            Log.d("onClick","리셋 :  $number")
        }
        countbtn.setOnClickListener {
            number += 1
            numberTextView.text = number.toString()
            Log.d("onClick", "플러스 : $number")
        }
    }
}