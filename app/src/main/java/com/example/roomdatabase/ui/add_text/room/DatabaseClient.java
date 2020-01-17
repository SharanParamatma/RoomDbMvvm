package com.example.roomdatabase.ui.add_text.room;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient mInstance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
       // appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "allData").build();
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "allData").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
