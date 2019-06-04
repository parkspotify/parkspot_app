package de.hdmstuttgart.parkspot.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static final String BASE_URL = "http://cryptec.tech/parkspot/";

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
