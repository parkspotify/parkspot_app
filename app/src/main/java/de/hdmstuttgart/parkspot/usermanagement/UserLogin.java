package de.hdmstuttgart.parkspot.usermanagement;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import de.hdmstuttgart.parkspot.SharedPrefs;
import de.hdmstuttgart.parkspot.activities.MainActivity;
import de.hdmstuttgart.parkspot.models.User;
import de.hdmstuttgart.parkspot.networking.Client;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class UserLogin {

    User mUser = new User();

    public void loginUser(final String mail, String password, final Context context) {

        Call<ResponseBody> call = Client
                .getInstance()
                .getApi()
                .login(mail, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String resp = response.body().string();
                        Toast.makeText(context, resp, Toast.LENGTH_LONG).show();

                        //TODO: setCorrect userid
                        User.setUserid(1);

                        User.setMail(mail);
                        User.setLOGGED_IN(true);
                        
                        context.startActivity(new Intent(context, MainActivity.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Server Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
