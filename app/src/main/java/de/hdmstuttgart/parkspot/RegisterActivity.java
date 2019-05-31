package de.hdmstuttgart.parkspot;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.hdmstuttgart.parkspot.usermanagement.UserRegister;


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
