package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import dagger.Component;

import com.yodle.android.bayonet.EspressoMainApp;
import com.yodle.android.bayonet.LoginTest;

@Singleton
@Component(modules = EspressoAppModule.class)
public interface EspressoAppComponent extends AppComponent {

    void inject(EspressoMainApp espressoMainApp);

    /**
     * Tests
     */
    void inject(LoginTest loginTest);
}
