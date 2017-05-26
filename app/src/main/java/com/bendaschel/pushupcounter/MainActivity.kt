package com.bendaschel.pushupcounter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var  btnCount: Button
    private lateinit var toolbar: Toolbar

    private var count = 0

    fun bindViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        btnCount = findViewById(R.id.btn_count) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        setSupportActionBar(toolbar)

        btnCount.setOnClickListener { v ->
            count++
            if (v is Button) {
                v.text = count.toString()
                updateColors()
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

        updateColors()
    }

    fun updateColors() {
        val red = Random().nextInt(255)
        val green = Random().nextInt(255)
        val blue = Random().nextInt(255)
        val bgColor = Color.rgb(red, green, blue)
        val textColor = complementaryColor(bgColor)
        btnCount.setTextColor(textColor)
        btnCount.setBackgroundColor(bgColor)
        toolbar.background = ColorDrawable(bgColor)
        toolbar.setTitleTextColor(textColor)
        window.setBackgroundDrawable(ColorDrawable(bgColor))
        window.statusBarColor = ColorUtils.blendARGB(bgColor, Color.BLACK, 0.2f)
    }

}
