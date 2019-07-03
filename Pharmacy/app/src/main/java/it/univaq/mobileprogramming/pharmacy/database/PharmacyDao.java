package it.univaq.mobileprogramming.pharmacy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import it.univaq.mobileprogramming.pharmacy.model.Pharmacy;

@Dao
public interface PharmacyDao {

    @Insert
    public void save(List<Pharmacy> pharmacy);

    @Delete
    public void delete(Pharmacy pharmacy);

    @Query("DELETE FROM pharmacies")
    public void deleteAll();

    @Update
    public void update(Pharmacy pharmacy);

    @Query("SELECT * FROM pharmacies WHERE descrCom LIKE :descrCom ")
    public abstract List<Pharmacy> getAllPharmacies(String descrCom);

    @Query("SELECT * FROM pharmacies WHERE latitude LIKE :lat AND longitude LIKE :lon")
    public Pharmacy getLatLon(String lat, String lon);

}
