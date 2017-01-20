package com.yodle.android.bayonet.service;

import java.util.List;

import javax.inject.Inject;

import android.app.Application;

import com.yodle.android.bayonet.MainApp;

public class UserService {

    @Inject ApiClient apiClient;

    @Inject
    public UserService(Application application) {
        MainApp.getAppComponent(application).inject(this);
    }

    public boolean login(String username, String password) {
        List<String> permissions = apiClient.authenticate(username, password);
        return permissions.contains("ADMIN");
    }
}
