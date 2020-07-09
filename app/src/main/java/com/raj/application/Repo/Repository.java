package com.raj.application.Repo;

import com.raj.application.db.AppDatabase;
import com.raj.application.db.User;

import java.util.ArrayList;
import java.util.List;

public class Repository {


    public List<User> getListData(AppDatabase db) {

        return db.userDao().getAll();
    }

    public void insertData(AppDatabase db, User mUser) {
        db.userDao().insertAll(mUser);
    }


    public User getListData(AppDatabase db, int id) {

        return db.userDao().findUserByUID(id);
    }

}
