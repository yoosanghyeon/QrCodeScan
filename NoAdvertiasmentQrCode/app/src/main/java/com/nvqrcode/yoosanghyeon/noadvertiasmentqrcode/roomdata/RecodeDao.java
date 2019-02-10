package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecodeDao {
    @Query("SELECT * FROM Recodes ORDER BY timestamp DESC")
    public List<Recode> getAll();

    @Insert
    public void insertAll(Recode... recode);

    @Insert
    public void insertRecode(Recode recode);

    @Delete
    public void delete(Recode recode);
}