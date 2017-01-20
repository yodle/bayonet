package com.yodle.android.bayonet;

import org.robolectric.RuntimeEnvironment;

import com.yodle.android.bayonet.dagger.AppComponent;
import com.yodle.android.bayonet.dagger.DaggerRobolectricAppComponent;
import com.yodle.android.bayonet.dagger.RobolectricAppComponent;
import com.yodle.android.bayonet.dagger.RobolectricAppModule;

public class RobolectricMainApp extends MainApp {

    public DaggerMockProvider provider = new DaggerMockProvider();

    public static RobolectricAppComponent getAppComponent() {
        return (RobolectricAppComponent) MainApp.getAppComponent(RuntimeEnvironment.application);
    }

    @Override
    public AppComponent buildAppComponent() {
        return DaggerRobolectricAppComponent.builder().robolectricAppModule(new RobolectricAppModule(this)).build();
    }
}
