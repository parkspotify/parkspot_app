package de.hdmstuttgart.parkspot.networking;

import de.hdmstuttgart.parkspot.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

public class Client {

    private final String BASE_URL = Constants.BASE_URL;

    private static Client mRetrofitInstance;
    private Retrofit retrofit;

    private Client() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized Client getInstance() {
        if(mRetrofitInstance == null) {
            mRetrofitInstance = new Client();
        }
        return mRetrofitInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
