package com.bendaschel.pushupcounter

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import java.util.*

class MainActivity : LifecycleActivity() {

    private lateinit var  btnCount: Button
    private lateinit var toolbar: Toolbar

    fun bindViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        btnCount = findViewById(R.id.btn_count) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        val countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        countViewModel.getCount().observe(this, android.arch.lifecycle.Observer {
            btnCount.text = it?.toString()
            updateColors()
        })

        setActionBar(toolbar)

        btnCount.setOnClickListener {
            countViewModel.inc()
        }
        btnCount.setOnLongClickListener {
            countViewModel.reset()
            Toast.makeText(this, R.string.toast_counter_reset, Toast.LENGTH_SHORT).show()
            true
        }
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
