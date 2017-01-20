package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import com.yodle.android.bayonet.RobolectricMainApp;
import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

@Module
public class RobolectricAppModule extends BaseAppModule {

    private final RobolectricMainApp application;

    public RobolectricAppModule(RobolectricMainApp application) {
        super(application);
        this.application = application;
    }

    private <T> T provide(T t) {
        return application.provider.provide(t);
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return super.baseProvideApplication();
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return provide(super.baseProvideApiClient());
    }

    @Provides
    @Singleton
    UserService provideUserService() {
        return provide(super.baseProvideUserService());
    }
}
