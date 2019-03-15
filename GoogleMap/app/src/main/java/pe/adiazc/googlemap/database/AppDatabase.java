package pe.adiazc.googlemap.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import pe.adiazc.googlemap.dao.MarcadorDao;
import pe.adiazc.googlemap.entidad.Marcador;

@Database(entities = {Marcador.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    public abstract MarcadorDao marcadorDao();


    private static volatile AppDatabase INSTANCE;



    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database").build();
                }
            }
        }
        return INSTANCE;
    }


}
