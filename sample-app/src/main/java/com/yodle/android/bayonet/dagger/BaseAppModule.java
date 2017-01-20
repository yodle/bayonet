package com.yodle.android.bayonet.dagger;

import android.app.Application;

import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

public class BaseAppModule {

    public final Application application;

    BaseAppModule(Application application) {
        this.application = application;
    }

    Application baseProvideApplication() {
        return application;
    }

    ApiClient baseProvideApiClient() {
        return new ApiClient(application);
    }

    UserService baseProvideUserService() {
        return new UserService(application);
    }
}
