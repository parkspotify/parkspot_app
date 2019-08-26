package de.hdmstuttgart.parkspot.usermanagement;

import android.content.Context;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;
import de.hdmstuttgart.parkspot.SharedPrefs;
import de.hdmstuttgart.parkspot.activities.MainActivity;
import de.hdmstuttgart.parkspot.models.User;
import de.hdmstuttgart.parkspot.networking.Client;
import de.hdmstuttgart.parkspot.networking.ResponseParser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

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

public class UserLogin {

    public void loginUser(final String mail, String password, final Context context) {

        Call<ResponseParser> call = Client
                .getInstance()
                .getApi()
                .login(mail, password);

        call.enqueue(new Callback<ResponseParser>() {
            @Override
            public void onResponse(Call<ResponseParser> call, Response<ResponseParser> response) {
                if (response.isSuccessful()) {

                        String accesstoken = response.body().getToken();
                        User.setAccesstoken(accesstoken);
                        User.setMail(mail);
                        User.setLOGGED_IN(true);

                        context.startActivity(new Intent(context, MainActivity.class));

                } else {
                    Log.d("PSPOT_LOGIN_ERROR", response.message());
                    Toast.makeText(context, "Server Error: " + response.code() + " -> " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseParser> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
