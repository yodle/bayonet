package com.yodle.android.bayonet.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.app.Application;

import com.yodle.android.bayonet.MainApp;

public class ApiClient {

    @Inject
    public ApiClient(Application application) {
        MainApp.getAppComponent(application).inject(this);
    }

    public List<String> authenticate(String username, String password) {
        // Some external network call to be mocked in tests
        return new ArrayList<>();
    }
}
