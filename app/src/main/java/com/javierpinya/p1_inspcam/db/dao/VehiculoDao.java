package com.javierpinya.p1_inspcam.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

import java.util.List;

@Dao
public interface VehiculoDao {

    @Insert
    void insertTractora(PrimerComponenteEntity tractora);

    @Update
    void updateTractora(PrimerComponenteEntity tractora);

    @Query("DELETE FROM tacprco")
    void deleteAll();

    @Query("DELETE FROM tacprco WHERE id = :idTractora")
    void deleteById(int idTractora);

    @Query("SELECT * FROM tacprco ORDER BY matricula ASC")
    LiveData<List<PrimerComponenteEntity>> getAllTractoras();

    /*
    @Query("SELECT * FROM tacprco WHERE fechaBaja is NULL")
    LiveData<List<PrimerComponenteEntity>> getAllActivas();

     */
}
