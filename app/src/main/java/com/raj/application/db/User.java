package com.raj.application.db;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @Bindable
    @ColumnInfo(name = "name")
    private String name="";

    @Bindable
    @ColumnInfo(name = "email")
    private String email="";


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
