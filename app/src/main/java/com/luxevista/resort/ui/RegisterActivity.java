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

public class RegisterActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput;
    private Button registerButton;
    private TextView loginLink;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.registerButton);
        loginLink = findViewById(R.id.loginLink);
        progressBar = findViewById(R.id.registerProgressBar);

        registerButton.setEnabled(false);

        nameInput.addTextChangedListener(registerTextWatcher);
        emailInput.addTextChangedListener(registerTextWatcher);
        passwordInput.addTextChangedListener(registerTextWatcher);

        registerButton.setOnClickListener(v -> {
            if (validateInputs()) {
                performRegistration();
            }
        });

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private final TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            registerButton.setEnabled(validateInputs());
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    private boolean validateInputs() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        boolean validName = !name.isEmpty();
        boolean validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean validPassword = password.length() >= 6;

        if (!validName) {
            nameInput.setError("Name cannot be empty");
        } else {
            nameInput.setError(null);
        }

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

        return validName && validEmail && validPassword;
    }

    private void performRegistration() {
        progressBar.setVisibility(View.VISIBLE);
        registerButton.setEnabled(false);

        // TODO: Implement real registration logic here (e.g., Firebase Auth)
        // For now, simulate success after delay
        nameInput.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            registerButton.setEnabled(true);
            Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 1500);
    }
}
