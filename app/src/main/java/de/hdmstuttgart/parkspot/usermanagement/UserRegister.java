package de.hdmstuttgart.parkspot.usermanagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import de.hdmstuttgart.parkspot.activities.LoginActivity;
import de.hdmstuttgart.parkspot.networking.Client;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;

public class UserRegister {

    public void registerNewUser(String mail, String password, final Context context) {

        Call<ResponseBody> call = Client
                .getInstance()
                .getApi()
                .userregister(mail, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    try {
                        String resp = response.body().string();
                        Toast.makeText(context, resp, Toast.LENGTH_LONG).show();

                        context.startActivity(new Intent(context, LoginActivity.class));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("PSPOT_REGISTER_ERROR", response.message());
                    Toast.makeText(context, "Server Error: " + response.code() + " -> " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
