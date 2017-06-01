package com.bendaschel.pushupcounter.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CounterDao {

    @Insert
    void insertCounter(Counter ... counters);

    @Query("select * from counters")
    LiveData<List<Counter>> getAllCounters();


}
