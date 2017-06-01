package com.bendaschel.pushupcounter

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import com.bendaschel.pushupcounter.db.AppDatabase
import com.bendaschel.pushupcounter.db.Counter
import java.util.*

class MainActivity : LifecycleActivity() {

    private lateinit var  btnCount: Button
    private lateinit var toolbar: Toolbar
    private lateinit var appDb: AppDatabase

    fun bindViews() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        btnCount = findViewById(R.id.btn_count) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()

        appDb = Room.databaseBuilder(this.applicationContext, AppDatabase::class.java, "app-db")
                .allowMainThreadQueries()
                .addMigrations()
                .build()

        val countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        countViewModel.getCount().observe(this, Observer {
            btnCount.text = it?.toString()
            updateColors()
        })

        setActionBar(toolbar)

        btnCount.setOnClickListener {
            countViewModel.inc()
        }
        btnCount.setOnLongClickListener {
            saveCount(countViewModel.getCount().value)
            countViewModel.reset()
            Toast.makeText(this, R.string.toast_counter_reset, Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun saveCount(value: Int?) {
        if (value != null) {
            val counter = Counter(value)
            appDb.counterDao().insertCounter(counter)
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
