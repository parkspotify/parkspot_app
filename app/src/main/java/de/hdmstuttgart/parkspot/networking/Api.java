package de.hdmstuttgart.parkspot.networking;

import com.google.gson.JsonObject;

import java.util.Map;

import de.hdmstuttgart.parkspot.models.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


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


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    //POST method to register a new user account
    @POST("auth/register/")
    Call<TokenResponse> register(@Body RequestBody params
    );

    //POST method to check login credentials
    @POST("auth/signin/")
    Call<TokenResponse> login(@Body RequestBody params
    );

    //POST method to get a list of all sensors of one user
    @POST("sensor/list/")
    Call<RaspberryListResponse> sensorlist(
            @Header("AUTHORIZATION") String accesstoken
    );

    //GET method for parkspot status list
    @POST("parkspots/")
    Call<ParkspotsResponse> parkspots(
            @Header("AUTHORIZATION") String accesstoken
    );

    //POST method to send user location update
    @POST("user/locationupdate")
    Call<ResponseBody> userlocationupdate(
            @Field("userid") String userid,
            @Field("longitude") Float longitude,
            @Field("latitude") Float latitude
    );

    //POST method to register a new sensor (raspi)
    @POST("sensor/register")
    Call<ResponseBody> sensorregister(
            @Field("userid") String userid,
            @Field("sensorid") String sensorid,
            @Field("longitude") Float longitude,
            @Field("latitude") Float latitude
    );

    //DELETE method to unregister an sensor
    @DELETE("sensor/delete")
    Call<ResponseBody> sensordelete(
            @Field("sensorid") String sensorid,
            @Field("userid") String userid
    );

}
