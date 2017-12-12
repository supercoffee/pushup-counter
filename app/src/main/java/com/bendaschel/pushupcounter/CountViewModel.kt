package com.bendaschel.pushupcounter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CountViewModel: ViewModel() {

    val count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun getCount(): LiveData<Int> {
        return count
    }

    fun add() {
        // Again, nullable type here due to inference from Java code
        count.value = count.value?.inc()
    }

    fun reset() {
        count.value = 0
    }
}