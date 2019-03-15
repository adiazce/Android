package pe.adiazc.googlemap.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.Bundle;


import java.util.List;

import pe.adiazc.googlemap.entidad.Marcador;
import pe.adiazc.googlemap.repository.MarcadorRepository;

public class MarcadorViewModel extends AndroidViewModel {

    private MarcadorRepository repository;

    private LiveData<List<Marcador>> allmarcador;

    public MarcadorViewModel(Application application) {
        super(application);

        this.repository = new MarcadorRepository(application);
        this.allmarcador = this.repository.getAllMarcador();

    }


    public LiveData<List<Marcador>> getAllmarcador() {
        return allmarcador;
    }

    public void Insert(Marcador marcador){this.repository.insert(marcador);}


}
