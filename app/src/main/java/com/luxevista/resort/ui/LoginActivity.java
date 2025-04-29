package com.luxevista.resort.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.luxevista.resort.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton;
    private TextView registerLink;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink);
        progressBar = findViewById(R.id.loginProgressBar);

        loginButton.setEnabled(false);

        emailInput.addTextChangedListener(loginTextWatcher);
        passwordInput.addTextChangedListener(loginTextWatcher);

        loginButton.setOnClickListener(v -> {
            if (validateInputs()) {
                performLogin();
            }
        });

        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private final TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            loginButton.setEnabled(validateInputs());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    private boolean validateInputs() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        boolean validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean validPassword = password.length() >= 6;

        if (!validEmail) {
            emailInput.setError(getString(R.string.invalid_email));
        } else {
            emailInput.setError(null);
        }

        if (!validPassword) {
            passwordInput.setError(getString(R.string.invalid_password));
        } else {
            passwordInput.setError(null);
        }

        return validEmail && validPassword;
    }

    private void performLogin() {
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setEnabled(false);

        // TODO: Implement real authentication logic here (e.g., Firebase Auth)
        // For now, simulate success after delay
        emailInput.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            loginButton.setEnabled(true);
            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 1500);
    }
}
