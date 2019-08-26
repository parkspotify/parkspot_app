package de.hdmstuttgart.parkspot.networking;

import de.hdmstuttgart.parkspot.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.net.UnknownServiceException;

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

public interface Api {

    String accesstoken = User.getAccesstoken();

    //POST method to register a new user account
    @FormUrlEncoded
    @POST("auth/register/")
    Call<ResponseBody> register(

        @Field("mail") String mail,
        @Field("password") String password
    );

    //POST method to check login credentials
    @FormUrlEncoded
    @POST("auth/signin/")
    Call<ResponseParser> login(
            @Field("mail") String mail,
            @Field("password") String password
    );

    //POST method to get a list of all sensors of one user
    @FormUrlEncoded
    @POST("sensor/list")
    Call<ResponseBody> sensorlist(
            @Header("AUTHORIZATION") String accesstoken,
            @Field("userid") String userid
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

    //DELETE method to unregister an sensor
    @FormUrlEncoded
    @DELETE("sensor/delete")
    Call<ResponseBody> sensordelete(
            @Field("sensorid") String sensorid,
            @Field("userid") String userid
    );

}
