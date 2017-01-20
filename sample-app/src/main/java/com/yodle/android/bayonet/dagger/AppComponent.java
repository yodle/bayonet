package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import dagger.Component;

import com.yodle.android.bayonet.MainActivity;
import com.yodle.android.bayonet.MainApp;
import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainApp mainApp);

    /**
     * Activities
     */
    void inject(MainActivity mainActivity);

    /**
     * Services
     */
    void inject(UserService userService);

    void inject(ApiClient apiClient);
}
