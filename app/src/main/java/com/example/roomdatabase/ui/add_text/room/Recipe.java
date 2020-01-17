package com.example.roomdatabase.ui.add_text.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "feed")
public class Recipe implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "message")
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
