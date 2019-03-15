package pe.adiazc.googlemap.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pe.adiazc.googlemap.dao.MarcadorDao;
import pe.adiazc.googlemap.database.AppDatabase;
import pe.adiazc.googlemap.entidad.Marcador;

public class MarcadorRepository {

        private MarcadorDao marcadordao ;
        private LiveData<List<Marcador>> allmarcador;

        public MarcadorRepository(Application application){
            AppDatabase db = AppDatabase.getDatabase(application);
            marcadordao = db.marcadorDao();
            allmarcador = marcadordao.getAllmarcador();
        }


        public LiveData<List<Marcador>> getAllMarcador()
        {
            return  allmarcador;
        }

        public void insert(Marcador marcador){
             new insertAsyncTask(marcadordao).execute(marcador);
        }


    private class insertAsyncTask extends AsyncTask<Marcador, Void, Void> {

        private  MarcadorDao marcadorDao;
        insertAsyncTask(MarcadorDao marcadordao) {
            this.marcadorDao = marcadordao;
        }

        @Override
        protected Void doInBackground(Marcador... marcadors) {

            this.marcadorDao.insertAll( marcadors);
            return null;
        }
    }
}
