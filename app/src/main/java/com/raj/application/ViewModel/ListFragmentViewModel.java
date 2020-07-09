package com.raj.application.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.raj.application.Repo.Repository;
import com.raj.application.db.AppDatabase;
import com.raj.application.db.User;

import javax.inject.Inject;

public class ListFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<User> insertUser = new MutableLiveData<>();
    private MutableLiveData<Boolean> getinitialData = new MutableLiveData<>();
    Context cntx;
    AppDatabase db;


    public LiveData userList = Transformations.map(insertUser, new Function() {
        @Override
        public Object apply(Object user) {
            new Repository().insertData(db, insertUser.getValue());
            return new Repository().getListData(db);
        }
    });


    public LiveData userInitialList = Transformations.map(getinitialData, new Function() {
        @Override
        public Object apply(Object user) {

            return new Repository().getListData(db);
        }
    });

    public ListFragmentViewModel(@NonNull Application application) {

        super(application);
        cntx = application.getApplicationContext();
        // db= (AppDatabase) AppDatabase.getAppDatabase(cntx);
    }

    public void getInitialData(Boolean initialData, AppDatabase db) {
        if (getinitialData == null) {
            getinitialData = new MutableLiveData<>();
        }
        this.db = db;
        getinitialData.postValue(initialData);
    }

}
