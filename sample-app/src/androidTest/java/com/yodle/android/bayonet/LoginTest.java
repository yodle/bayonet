package com.yodle.android.bayonet;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.mockito.Mockito.*;

import javax.inject.Inject;

import android.support.test.espresso.core.deps.guava.collect.Lists;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yodle.android.bayonet.service.ApiClient;
import com.yodle.android.bayonet.service.UserService;

@RunWith(AndroidJUnit4.class)
public class LoginTest extends BaseEspressoTest {

    @RealClass @Inject UserService userService;
    @Inject ApiClient apiClient;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    private String username = "foo";
    private String password = "bar";

    @Before
    public void setUp() {
        super.setUp();
        getAppComponent().inject(this);

        mainActivityRule.launchActivity(null);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        when(apiClient.authenticate(username, password)).thenReturn(Lists.newArrayList("ADMIN"));

        onView(withId(R.id.username)).perform(typeText(username));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.login_message)).check(matches(isDisplayed()));
        onView(withId(R.id.login_message)).check(matches(withText(R.string.login_success)));
    }

    @Test
    public void testLoginFailure() throws Exception {
        when(apiClient.authenticate(username, password)).thenReturn(Lists.newArrayList("USER"));

        onView(withId(R.id.username)).perform(typeText(username));
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.login_message)).check(matches(isDisplayed()));
        onView(withId(R.id.login_message)).check(matches(withText(R.string.login_failure)));
    }
}
