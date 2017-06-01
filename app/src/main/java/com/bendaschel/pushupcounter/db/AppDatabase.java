package com.bendaschel.pushupcounter.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Counter.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CounterDao counterDao();
}
