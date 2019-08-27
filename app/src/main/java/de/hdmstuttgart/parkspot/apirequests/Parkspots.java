package de.hdmstuttgart.parkspot.apirequests;

import android.util.Log;
import de.hdmstuttgart.parkspot.Constants;
import de.hdmstuttgart.parkspot.models.User;
import de.hdmstuttgart.parkspot.networking.Client;
import okhttp3.ResponseBody;
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

public class Parkspots {

    public void getParkspots () {

        Call<ResponseBody> call = Client
                .getInstance()
                .getApi()
                .parkspots(User.getAccesstoken());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {



                } else {
                    Log.d(Constants.BASE_ERROR_TITLE + "LOADPSPOTS", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(Constants.BASE_ERROR_TITLE + "LOADPSPOTS", t.getMessage());
            }
        });
    }
}
