package de.hdmstuttgart.parkspot.networking;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("auth/register/")
    Call<ResponseBody> register(
        @Field("mail") String mail,
        @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/signin/")
    Call<ResponseBody> login(
            @Field("mail") String mail,
            @Field("password") String password
    );
}
