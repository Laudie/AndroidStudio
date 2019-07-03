package it.univaq.mobileprogramming.pharmacy.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;


@androidx.room.Database(entities ={ Pharmacy.class }, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase
{
    public abstract PharmacyDao getPharmacyDao();

    private static Database instance = null;

    public Database(){}

    public static Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context,
                    Database.class,
                    "myRoomDatabase").build();
        }
        return instance;
    }
}
