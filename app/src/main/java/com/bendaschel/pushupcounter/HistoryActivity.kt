package com.bendaschel.pushupcounter

import android.arch.lifecycle.LifecycleActivity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bendaschel.pushupcounter.db.AppDatabase
import com.bendaschel.pushupcounter.db.Counter
import java.text.DateFormat
import java.util.*

class HistoryActivity: LifecycleActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        recycler = findViewById(R.id.recycler) as RecyclerView
        recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        appDb = Room.databaseBuilder(this.applicationContext, AppDatabase::class.java, "app-db")
                .allowMainThreadQueries()
                .build()

        val counters = appDb.counterDao().allCounters
        val adapter = CounterAdapter(counters?: emptyList())
        recycler.adapter = adapter
    }
}

class CounterAdapter(val counters: List<Counter>) : RecyclerView.Adapter<CounterViewHolder>() {

    override fun onBindViewHolder(holder: CounterViewHolder?, position: Int) {
        val counter = counters[position]
        holder?.bind(counter)
    }

    override fun getItemCount(): Int {
        return counters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CounterViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_history, parent, false)
        return CounterViewHolder(view)
    }
}

class CounterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val countTextView: TextView = view.findViewById(R.id.tv_count) as TextView
    val timeTextView: TextView = view.findViewById(R.id.tv_time) as TextView

    fun bind(counter: Counter) {
        countTextView.text = counter.count.toString()
        timeTextView.text = DateFormat.getDateTimeInstance().format(Date(counter.timestamp))
    }

}
