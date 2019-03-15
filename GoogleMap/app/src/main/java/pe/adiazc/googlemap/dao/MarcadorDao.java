package pe.adiazc.googlemap.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import pe.adiazc.googlemap.entidad.Marcador;


@Dao
public interface MarcadorDao {

    @Query("SELECT * FROM marcador")
    List<Marcador> getAll();

    @Query("SELECT * from marcador ORDER BY _id ASC")
    LiveData<List<Marcador>> getAllmarcador();

    @Query("SELECT * FROM marcador WHERE _id IN (:marcadorIds)")
    List<Marcador> loadAllByIds(int[] marcadorIds);

    @Query("SELECT * FROM marcador WHERE nombre LIKE :nombre AND " +
            "descripcion LIKE :descripcion LIMIT 1")
    Marcador findByName(String nombre, String descripcion);

    @Insert
    void insertAll(Marcador... marcadors);

    @Delete
    void delete(Marcador marcador);

}
