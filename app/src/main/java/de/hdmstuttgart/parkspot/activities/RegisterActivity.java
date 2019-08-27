package de.hdmstuttgart.parkspot.activities;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.hdmstuttgart.parkspot.R;
import de.hdmstuttgart.parkspot.SharedPrefs;
import de.hdmstuttgart.parkspot.apirequests.UserRegister;
import de.hdmstuttgart.parkspot.models.User;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextMail, editTextPassword, editTextPasswordConfirm;
    private Button buttonRegister;

    UserRegister userRegister = new UserRegister();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextMail = findViewById(R.id.editTextMail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = editTextMail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();

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
                } else if (password.length() < 8) {
                    editTextPassword.setError("Password needs to be at least 8 chars long!");
                    editTextPassword.requestFocus();
                    return;
                }

                if(!password.equals(passwordConfirm)) {
                    editTextPasswordConfirm.setError("Passwords not matching!");
                    editTextPasswordConfirm.requestFocus();
                    return;
                }

                userRegister.registerNewUser(mail, password, RegisterActivity.this);


            }
        });
    }
}
