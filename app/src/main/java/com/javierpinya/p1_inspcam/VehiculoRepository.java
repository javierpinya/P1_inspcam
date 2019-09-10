package com.javierpinya.p1_inspcam;

import android.app.Application;
import android.app.ListActivity;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.PrimaryKey;

import com.javierpinya.p1_inspcam.db.VehiculoRoomDatabase;
import com.javierpinya.p1_inspcam.db.dao.VehiculoDao;
import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

import java.util.List;

public class VehiculoRepository {
    private VehiculoDao vehiculoDao;
    private LiveData<List<PrimerComponenteEntity>> allVehiculos;
    private LiveData<List<PrimerComponenteEntity>> allVehiculosActivos;

    public VehiculoRepository(Application application){
        VehiculoRoomDatabase db = VehiculoRoomDatabase.getDatabase(application);
        vehiculoDao = db.vehiculoDao();
        allVehiculos = vehiculoDao.getAllTractoras();
        //allVehiculosActivos = vehiculoDao.getAllActivas();
    }

    public LiveData<List<PrimerComponenteEntity>> getAll(){
        return allVehiculos;
    }

    public LiveData<List<PrimerComponenteEntity>> getAllActivos(){
        return allVehiculosActivos;
    }

    public void insert(PrimerComponenteEntity vehiculo){
        new insertAsyncTask(vehiculoDao).execute(vehiculo);
    }

    private static class insertAsyncTask extends AsyncTask<PrimerComponenteEntity, Void, Void>{
        private VehiculoDao vehiculoDaoAsyncTask;

        insertAsyncTask(VehiculoDao dao){
            vehiculoDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(PrimerComponenteEntity... primerComponenteEntities) {
            vehiculoDaoAsyncTask.insertTractora(primerComponenteEntities[0]);
            return null;
        }
    }

    public void update(PrimerComponenteEntity vehiculo){
        new updateAsyncTask(vehiculoDao).execute(vehiculo);
    }

    private static class updateAsyncTask extends AsyncTask<PrimerComponenteEntity, Void, Void>{
        private VehiculoDao vehiculoDaoAsyncTask;

        updateAsyncTask(VehiculoDao dao){
            vehiculoDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(PrimerComponenteEntity... primerComponenteEntities) {
            vehiculoDaoAsyncTask.updateTractora(primerComponenteEntities[0]);
            return null;
        }
    }
}
