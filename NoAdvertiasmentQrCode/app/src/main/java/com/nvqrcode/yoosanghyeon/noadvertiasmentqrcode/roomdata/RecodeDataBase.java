package com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static com.nvqrcode.yoosanghyeon.noadvertiasmentqrcode.roomdata.RecodeDataBase.DATABASE_VERSION;


@Database(entities = Recode.class , version =  DATABASE_VERSION)
public abstract class RecodeDataBase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "qrcode_recode";

    public abstract RecodeDao recodeDao();

    private static RecodeDataBase mInstance;



    public static RecodeDataBase getInstance(Context context) {
        if(mInstance == null) {
            mInstance = Room.databaseBuilder(context, RecodeDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return mInstance;
    }




}
