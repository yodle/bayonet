package com.yodle.android.bayonet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.inject.Inject;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.yodle.android.bayonet.service.UserService;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest extends BaseRobolectricTest {

    @Inject UserService userService;

    private MainActivity mainActivity;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView loginMessageTextView;
    private Button loginButton;
    private String username = "foo";
    private String password = "bar";

    @Before
    public void setUp() {
        super.setUp();
        getAppComponent().inject(this);

        mainActivity = Robolectric.setupActivity(MainActivity.class);
        usernameEditText = (EditText) mainActivity.findViewById(R.id.username);
        passwordEditText = (EditText) mainActivity.findViewById(R.id.password);
        loginMessageTextView = (TextView) mainActivity.findViewById(R.id.login_message);
        loginButton = (Button) mainActivity.findViewById(R.id.login);
    }

    @Test
    public void givenNoUsername_whenLogin_showValidationError() {
        assertEquals("", usernameEditText.getText().toString());
        assertNull(usernameEditText.getError());
        passwordEditText.setText(password);

        loginButton.performClick();

        assertEquals(getString(R.string.validation_error), usernameEditText.getError().toString());
    }

    @Test
    public void givenNoPassword_whenLogin_showValidationError() {
        assertEquals("", passwordEditText.getText().toString());
        assertNull(passwordEditText.getError());
        usernameEditText.setText(username);

        loginButton.performClick();

        assertEquals(getString(R.string.validation_error), passwordEditText.getError().toString());
    }

    @Test
    public void givenUsernameAndPassword_whenLogin_callUserService() {
        fillFormAndSubmit(username, password);

        verify(userService).login(username, password);
    }

    @Test
    public void givenInvalidUsernameAndPassword_whenLogin_showFailureToast() {
        when(userService.login(username, password)).thenReturn(false);

        fillFormAndSubmit(username, password);

        assertEquals(getString(R.string.login_failure), loginMessageTextView.getText().toString());
    }

    @Test
    public void givenValidUsernameAndPassword_whenLogin_showSuccessToast() {
        when(userService.login(username, password)).thenReturn(true);

        fillFormAndSubmit(username, password);

        assertEquals(getString(R.string.login_success), loginMessageTextView.getText().toString());
    }

    private void fillFormAndSubmit(String un, String pw) {
        usernameEditText.setText(un);
        passwordEditText.setText(pw);
        loginButton.performClick();
    }
}
