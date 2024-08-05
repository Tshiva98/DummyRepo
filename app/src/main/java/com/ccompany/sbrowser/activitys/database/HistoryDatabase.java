package com.ccompany.sbrowser.activitys.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = HistoryEntity.class,version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();
}
