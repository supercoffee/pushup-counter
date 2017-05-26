package com.bendaschel.pushupcounter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var  btnCount: Button

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCount = findViewById(R.id.btn_count) as Button
        btnCount.setOnClickListener { v ->
            count++
            if (v is Button) {
                v.text = count.toString()
            }
        }
        btnCount.setOnLongClickListener {
            count = 0
            Toast.makeText(this, "Counter reset", Toast.LENGTH_SHORT).show();
            true
        }
    }
}
