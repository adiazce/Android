package com.example.moviesef;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Movie.class}, version = 1)
public abstract class DataBase extends RoomDatabase {


    public abstract MovieDao movieDao();
    private static volatile DataBase INSTANCE;

    private static String DB_NAME = "database1";
    public static DataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, DB_NAME).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
