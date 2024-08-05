package com.ccompany.sbrowser.activitys.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert ( HistoryEntity historyEntity);


    @Query("select * from HistoryEntity")
    List<HistoryEntity> getAllFavText();


    @Query("Delete from HistoryEntity where id=:id")
    void delete(int id);

}
