package com.yodle.android.bayonet;

import android.support.annotation.StringRes;
import android.support.test.InstrumentationRegistry;
import org.junit.Before;

import com.yodle.android.bayonet.dagger.EspressoAppComponent;

public class BaseEspressoTest {

    private EspressoMainApp application;

    @Before
    public void setUp() {
        application = (EspressoMainApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        application.provider.init(this);
    }

    public EspressoAppComponent getAppComponent() {
        return EspressoMainApp.getAppComponent(application);
    }

    public String getString(@StringRes int stringResId) {
        return application.getString(stringResId);
    }
}
