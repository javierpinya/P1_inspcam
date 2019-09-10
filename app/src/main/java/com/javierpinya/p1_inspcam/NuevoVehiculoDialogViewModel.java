package com.javierpinya.p1_inspcam;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.javierpinya.p1_inspcam.db.entity.PrimerComponenteEntity;

import java.util.List;

public class NuevoVehiculoDialogViewModel extends AndroidViewModel {

    private LiveData<List<PrimerComponenteEntity>> allTractoras;
    private VehiculoRepository vehiculoRepository;


    public NuevoVehiculoDialogViewModel(@NonNull Application application) {
        super(application);

        vehiculoRepository = new VehiculoRepository(application);
        allTractoras = vehiculoRepository.getAll();

    }
    // TODO: Implement the ViewModel

    public LiveData<List<PrimerComponenteEntity>> getAllTractoras(){
        return allTractoras;
    }

    public void insertTractora(PrimerComponenteEntity tractoraEntity){
        vehiculoRepository.insert(tractoraEntity);
    }

    public void updateTractora(PrimerComponenteEntity tractoraEntity){
        vehiculoRepository.update(tractoraEntity);
    }
}
