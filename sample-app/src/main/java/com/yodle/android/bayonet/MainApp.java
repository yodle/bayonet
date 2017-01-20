package com.yodle.android.bayonet;

import android.app.Application;
import android.content.Context;

import com.yodle.android.bayonet.dagger.AppComponent;
import com.yodle.android.bayonet.dagger.AppModule;
import com.yodle.android.bayonet.dagger.DaggerAppComponent;

public class MainApp extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = buildAppComponent();
        appComponent.inject(this);
    }

    // This method can be overridden to add test modules, see RobolectricMainApp and EspressoMainApp
    public AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((MainApp) context.getApplicationContext()).appComponent;
    }
}
