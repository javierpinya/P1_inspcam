package com.javierpinya.p1_inspcam.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.javierpinya.p1_inspcam.db.dao.VehiculoDao;
import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

@Database(entities = {PrimerComponenteEntity.class}, version = 1, exportSchema = false)
public abstract class VehiculoRoomDatabase extends RoomDatabase {

    public abstract VehiculoDao vehiculoDao();
    private static volatile VehiculoRoomDatabase INSTANCE;

    public static VehiculoRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (VehiculoRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VehiculoRoomDatabase.class, "vehiculos_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
