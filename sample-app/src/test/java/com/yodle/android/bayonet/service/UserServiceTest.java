package com.yodle.android.bayonet.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.yodle.android.bayonet.BaseRobolectricTest;
import com.yodle.android.bayonet.RealClass;

@RunWith(RobolectricTestRunner.class)
public class UserServiceTest extends BaseRobolectricTest {

    // Class under test, injected as real class
    @RealClass @Inject UserService userService;

    @Inject ApiClient apiClient;

    private String username = "mario";
    private String password = "peach123";

    @Before
    public void setUp() {
        super.setUp();
        getAppComponent().inject(this);
    }

    @Test
    public void givenNoPermissions_whenLogin_returnFalse() throws Exception {
        when(apiClient.authenticate(username, password)).thenReturn(Lists.<String>newArrayList());
        assertFalse(userService.login(username, password));
    }

    @Test
    public void givenUserPermission_whenLogin_returnFalse() throws Exception {
        when(apiClient.authenticate(username, password)).thenReturn(Lists.newArrayList("USER"));
        assertFalse(userService.login(username, password));
    }

    @Test
    public void givenAdminPermission_whenLogin_returnTrue() throws Exception {
        when(apiClient.authenticate(username, password)).thenReturn(Lists.newArrayList("ADMIN"));
        assertTrue(userService.login(username, password));
    }
}