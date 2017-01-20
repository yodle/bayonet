package com.yodle.android.bayonet;

import android.support.annotation.StringRes;
import org.junit.Before;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import com.yodle.android.bayonet.dagger.RobolectricAppComponent;

@Config(constants = BuildConfig.class, sdk = 21, application = RobolectricMainApp.class)
public class BaseRobolectricTest {

    private RobolectricMainApp application;

    @Before
    public void setUp() {
        application = (RobolectricMainApp) RuntimeEnvironment.application;
        application.provider.init(this);
    }

    public RobolectricAppComponent getAppComponent() {
        return RobolectricMainApp.getAppComponent();
    }

    public String getString(@StringRes int stringResId) {
        return application.getString(stringResId);
    }
}
