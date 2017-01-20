package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

@Module
public class AppModule extends BaseAppModule {

    public AppModule(Application application) {
        super(application);
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return super.baseProvideApplication();
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return super.baseProvideApiClient();
    }

    @Provides
    @Singleton
    UserService provideUserService() {
        return super.baseProvideUserService();
    }
}
