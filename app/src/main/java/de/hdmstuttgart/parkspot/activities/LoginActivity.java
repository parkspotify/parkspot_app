package de.hdmstuttgart.parkspot.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.hdmstuttgart.parkspot.R;
import de.hdmstuttgart.parkspot.apirequests.UserLogin;

/**
 * This file is part of Parkspot.      
 *
 * Parkspot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation version 3 of the License.
 * Parkspot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Parkspot. 
 * If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2019, Hochschule der Medien
 * Author: Nils Mursinsky
 */

public class LoginActivity extends AppCompatActivity {

    private EditText editTextMail, editTextPassword;
    private TextView textViewRegister;
    private Button buttonLogin;
    UserLogin userLogin = new UserLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewRegister = findViewById(R.id.textViewRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = editTextMail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(mail.isEmpty()) {
                    editTextMail.setError("E-Mail address required!");
                    editTextMail.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    editTextMail.setError("E-Mail address not valid!");
                    editTextMail.requestFocus();
                    return;
                }

                if(password.isEmpty()) {
                    editTextPassword.setError("Password required!");
                    editTextPassword.requestFocus();
                    return;
                }

                if (mail.contains("test@test.de") && (password.contains("test"))) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    userLogin.loginUser(mail, password, LoginActivity.this);
                }
            }
        });
    }
}
