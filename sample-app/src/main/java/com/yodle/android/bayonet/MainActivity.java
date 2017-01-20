package com.yodle.android.bayonet;

import javax.inject.Inject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yodle.android.bayonet.service.UserService;

public class MainActivity extends AppCompatActivity {

    @Inject UserService userService;

    private EditText username;
    private EditText password;
    private TextView loginMessage;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainApp.getAppComponent(this).inject(this);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginMessage = (TextView) findViewById(R.id.login_message);
        loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View focus = getCurrentFocus();
                if (focus != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focus.getWindowToken(), 0);
                }
                if (!validate()) {
                    return;
                }
                handleLogin();
            }
        });
    }

    public void handleLogin() {
        boolean isAuthenticated = userService.login(username.getText().toString(), password.getText().toString());
        if (isAuthenticated) {
            loginMessage.setText(getString(R.string.login_success));
            loginMessage.setTextColor(Color.GREEN);
        } else {
            loginMessage.setText(getString(R.string.login_failure));
            loginMessage.setTextColor(Color.RED);
        }
        loginMessage.setVisibility(View.VISIBLE);
    }

    public boolean validate() {
        if (username.getText().toString().isEmpty()) {
            showValidationError(username);
            return false;
        }

        if (password.getText().toString().isEmpty()) {
            showValidationError(password);
            return false;
        }

        return true;
    }

    public void showValidationError(EditText editText) {
        editText.setError(getString(R.string.validation_error));
        editText.requestFocus();
    }
}