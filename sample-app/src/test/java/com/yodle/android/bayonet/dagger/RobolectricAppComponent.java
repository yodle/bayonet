package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import dagger.Component;

import com.yodle.android.bayonet.MainActivityTest;
import com.yodle.android.bayonet.RobolectricMainApp;
import com.yodle.android.bayonet.service.UserServiceTest;

@Singleton
@Component(modules = RobolectricAppModule.class)
public interface RobolectricAppComponent extends AppComponent {

    void inject(RobolectricMainApp robolectricMainApp);

    /*
     * Tests
     */
    void inject(MainActivityTest mainActivityTest);

    void inject(UserServiceTest userServiceTest);
}
