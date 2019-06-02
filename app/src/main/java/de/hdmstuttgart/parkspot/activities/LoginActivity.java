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
import de.hdmstuttgart.parkspot.usermanagement.UserLogin;

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
                    editTextMail.setError("E-Mail address requiered!");
                    editTextMail.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    editTextMail.setError("E-Mail address not valid!");
                    editTextMail.requestFocus();
                    return;
                }

                if(password.isEmpty()) {
                    editTextPassword.setError("Password requiered!");
                    editTextPassword.requestFocus();
                    return;
                }

                userLogin.LoginUser(mail, password, LoginActivity.this);

            }
        });
    }
}
