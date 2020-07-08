package com.raj.application.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.raj.application.Repo.Repository;
import com.raj.application.db.AppDatabase;
import com.raj.application.db.User;

public class AddEditFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<User> insertUpdateUser= new MutableLiveData<>();
    private MutableLiveData<Integer> userId= new MutableLiveData<>();
    Context cntx;
    AppDatabase db;


    public LiveData userList = Transformations.map(insertUpdateUser, new Function() {
        @Override
        public Object apply(Object user) {
             new Repository().insertData(db,insertUpdateUser.getValue());
            return new Repository().getListData(db);
        }
    });

    public LiveData user = Transformations.map(userId, new Function() {
        @Override
        public Object apply(Object user) {

            return new Repository().getListData(db,userId.getValue());
        }
    });

    public AddEditFragmentViewModel(@NonNull Application application) {

        super(application);
        cntx=application.getApplicationContext();
        db= (AppDatabase) AppDatabase.getAppDatabase(cntx);
    }

    public void setUser(User mUser){

        if(insertUpdateUser==null){
            insertUpdateUser= new MutableLiveData<>();
        }
        insertUpdateUser.postValue(mUser);

    }


    public void setUserId(int id){

        if(userId==null){
            userId= new MutableLiveData<>();
        }
        userId.postValue(id);

    }

}
