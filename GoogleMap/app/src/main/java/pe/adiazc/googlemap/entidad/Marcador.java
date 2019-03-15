package pe.adiazc.googlemap.entidad;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "marcador")
public class Marcador {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_id")
    private   int id  ;
    @NonNull
    @ColumnInfo(name = "nombre")
    private   String nombre ;
    @NonNull
    @ColumnInfo(name = "descripcion")
    private   String descripcion ;
    @NonNull
    @ColumnInfo(name = "latitud")
    private   String latitud;
    @NonNull
    @ColumnInfo(name = "longitud")
    private   String longitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull String latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull String longitud) {
        this.longitud = longitud;
    }
}
