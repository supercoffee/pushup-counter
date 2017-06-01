package com.bendaschel.pushupcounter.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "counters")
public class Counter {

    public Counter(int count) {
        this.count = count;
        timestamp = System.currentTimeMillis();
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int count;

    public long timestamp;
}
