package de.hdmstuttgart.parkspot.apirequests;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Map;

import de.hdmstuttgart.parkspot.Constants;
import de.hdmstuttgart.parkspot.activities.MainActivity;
import de.hdmstuttgart.parkspot.models.User;
import de.hdmstuttgart.parkspot.networking.Client;
import de.hdmstuttgart.parkspot.networking.TokenResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Map<String, Object> jsonParams = new ArrayMap<>();

        jsonParams.put("mail", mail);
        jsonParams.put("password", password);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

        Call<TokenResponse> call = Client
                .getInstance()
                .getApi()
                .login(body);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {

                        String accesstoken = response.body().getToken();
                        User.setAccesstoken(accesstoken);
                        User.setMail(mail);
                        User.setLOGGED_IN(true);

                        context.startActivity(new Intent(context, MainActivity.class));

                } else {
                    Log.d(Constants.BASE_ERROR_TITLE + "LOGIN", response.message());
                    Toast.makeText(context, "Server Error: " + response.code() + " -> " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
