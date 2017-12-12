package com.bendaschel.pushupcounter

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import java.util.*

class MainActivity : LifecycleActivity() {

    private val countButton by lazy {
        findViewById(R.id.btn_count) as Button
    }

    private val toolbar by lazy {
        findViewById(R.id.toolbar) as Toolbar
    }

    private val countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

    // Bundle is nullable because the superclass is written in Java
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel.getCount().observe(this, android.arch.lifecycle.Observer { value ->
            // Since Android LiveData is written in Java, types are inferred to be nullable
            countButton.text = value?.toString()
            updateColors()
        })

        setActionBar(toolbar)

        countButton.setOnClickListener {
            countViewModel.add()
        }

        // Example function reference
        countButton.setOnLongClickListener(this::onClick)

    }

    fun onClick(view: View?): Boolean {
        countViewModel.reset()
        Toast.makeText(this, R.string.toast_counter_reset, Toast.LENGTH_SHORT).show()
        return true
    }

    fun updateColors() {
        val red = Random().nextInt(255)
        val green = Random().nextInt(255)
        val blue = Random().nextInt(255)
        val bgColor = Color.rgb(red, green, blue)
        val textColor = complementaryColor(bgColor)
        countButton.setTextColor(textColor)
        countButton.setBackgroundColor(bgColor)
        toolbar.background = ColorDrawable(bgColor)
        toolbar.setTitleTextColor(textColor)
        window.setBackgroundDrawable(ColorDrawable(bgColor))
        window.statusBarColor = ColorUtils.blendARGB(bgColor, Color.BLACK, 0.2f)
    }

}
