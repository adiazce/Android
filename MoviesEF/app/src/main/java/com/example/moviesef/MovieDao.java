package com.example.moviesef;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface  MovieDao {


    @Query("SELECT * FROM movie")
    List<Movie> getAll();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Movie... movies);
    @Delete
    void delete(Movie... movies);

}
