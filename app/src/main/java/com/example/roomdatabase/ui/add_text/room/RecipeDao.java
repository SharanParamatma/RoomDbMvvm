package com.example.roomdatabase.ui.add_text.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM feed ORDER BY id ASC")
    List<Recipe> getAll();

//    @Query("SELECT * FROM feed ORDER BY id DESC")
//    List<Recipe> getAll();

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete(Recipe model);

    @Query("DELETE FROM feed WHERE id = :id")
    void delete(int id);

    @Query("UPDATE feed SET message=:message WHERE id = :id")
    void update(String message, int id);


}
