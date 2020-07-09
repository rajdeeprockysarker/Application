package com.raj.application.di;

import android.app.Application;

import com.raj.application.db.AppDatabase;
import com.raj.application.view.MainActivity;


import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity authActivity();

    @Provides
    static String getValue(){
        return "123";
    };

    @Provides
    static AppDatabase getDB(Application application){
        return (AppDatabase) AppDatabase.getAppDatabase(application);
    };
}
