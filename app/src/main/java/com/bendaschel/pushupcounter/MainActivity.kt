package com.bendaschel.pushupcounter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var  btnCount: Button
    private var toolBar: ActionBar? = null

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar = supportActionBar
        btnCount = findViewById(R.id.btn_count) as Button
        btnCount.setOnClickListener { v ->
            count++
            if (v is Button) {
                v.text = count.toString()
                val red = Random().nextInt(count)
                val green = Random().nextInt(count)
                val blue = Random().nextInt(count)
                val bgColor = Color.rgb(square(red), square(green), square(blue))
                v.setBackgroundColor(bgColor)
                toolBar?.setBackgroundDrawable(ColorDrawable(bgColor))
            }
        }
        btnCount.setOnLongClickListener { v ->
            count = 0
            Toast.makeText(this, "Counter reset", Toast.LENGTH_SHORT).show()
            if (v is Button) {
                v.text = count.toString()
            }
            true
        }
    }
}
