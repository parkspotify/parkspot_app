package de.hdmstuttgart.parkspot.usermanagement;

import android.content.Context;
import android.widget.Toast;
import de.hdmstuttgart.parkspot.networking.Client;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class UserLogin {

    public void LoginUser(String mail, String password, final Context context) {

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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Server Error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
