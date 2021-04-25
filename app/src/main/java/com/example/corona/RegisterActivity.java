package com.example.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.corona.Validation.isEmailValid;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText usernameET = findViewById(R.id.username);
        EditText emailET = findViewById(R.id.email);
        EditText passwordET = findViewById(R.id.password);
        Button RegisterBT = findViewById(R.id.register);


        RegisterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                String usernameStr = usernameET.getText().toString();
                String emailStr = emailET.getText().toString();
                String passwordStr = passwordET.getText().toString();

                usernameET.setError(null);
                emailET.setError(null);
                passwordET.setError(null);

                if (Validation.isUsernameValid(usernameStr) && Validation.isEmailValid(emailStr) &&
                        Validation.isPasswordValid(passwordStr)) {

                    Toast.makeText(RegisterActivity.this, "Prisijungimo vardas: " +
                            usernameStr + "\n" + "El.paštas: " + emailStr + "\n" + "Slaptažodis: " +
                            passwordStr, Toast.LENGTH_LONG).show();

                    Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToLoginActivity);

                } else {
                    if (!Validation.isUsernameValid(usernameStr)) {
                        usernameET.setError(getResources().getString(R.string.register_invalid_username));
                        usernameET.requestFocus();
                    }
                    if (!Validation.isEmailValid(emailStr)) {
                        emailET.setError(getResources().getString(R.string.register_invalid_email));
                        emailET.requestFocus();
                    }
                    if (!Validation.isPasswordValid(passwordStr)) {
                        passwordET.setError(getResources().getString(R.string.register_invalid_password));
                        passwordET.requestFocus();
                    }
                }
            }
        });

    }
}
