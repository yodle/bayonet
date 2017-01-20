package com.yodle.android.bayonet.dagger;

import javax.inject.Singleton;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import com.yodle.android.bayonet.EspressoMainApp;
import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

@Module
public class EspressoAppModule extends BaseAppModule {

    private final EspressoMainApp application;

    public EspressoAppModule(EspressoMainApp application) {
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
