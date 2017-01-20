package com.yodle.android.bayonet;

import android.content.Context;

import com.yodle.android.bayonet.dagger.AppComponent;
import com.yodle.android.bayonet.dagger.DaggerEspressoAppComponent;
import com.yodle.android.bayonet.dagger.EspressoAppComponent;
import com.yodle.android.bayonet.dagger.EspressoAppModule;

public class EspressoMainApp extends MainApp {

    public DaggerMockProvider provider = new DaggerMockProvider();

    public static EspressoAppComponent getAppComponent(Context context) {
        return (EspressoAppComponent) MainApp.getAppComponent(context);
    }

    @Override
    public AppComponent buildAppComponent() {
        return DaggerEspressoAppComponent.builder().espressoAppModule(new EspressoAppModule(this)).build();
    }
}
