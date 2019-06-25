package de.hdmstuttgart.parkspot.networking;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    //POST method to register a new user account
    @FormUrlEncoded
    @POST("user/register")
    Call<ResponseBody> userregister(
        @Field("mail") String mail,
        @Field("password") String password
    );

    //POST method to check login credentials
    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> userlogin(
            @Field("mail") String mail,
            @Field("password") String password
    );

    //POST method to send user location update
    @FormUrlEncoded
    @POST("user/locationupdate")
    Call<ResponseBody> userlocationupdate(
            @Field("userid") String userid,
            @Field("longitude") Float longitude,
            @Field("latitude") Float latitude
    );

    //POST method to register a new sensor (raspi)
    @FormUrlEncoded
    @POST("sensor/register")
    Call<ResponseBody> sensorregister(
            @Field("userid") String userid,
            @Field("sensorid") String sensorid,
            @Field("longitude") Float longitude,
            @Field("latitude") Float latitude
    );

    //POST method to get a list of all sensors of one user
    @FormUrlEncoded
    @POST("sensor/list")
    Call<ResponseBody> sensorlist(
            @Field("userid") String userid
    );

    //DELETE method to unregister an sensor
    @FormUrlEncoded
    @DELETE("sensor/delete")
    Call<ResponseBody> sensordelete(
            @Field("sensorid") String sensorid,
            @Field("userid") String userid
    );
}
