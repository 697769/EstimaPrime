/*
 * Copyright EstimaPrime(c) 2016 - By Victor Sodré 528803
 */

package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestHelper {

    public static final String BASE_URL = "http://172.28.15.17:8080/";

    public static rest.EstimaPrime retrofit(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd'T'hh:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(rest.EstimaPrime.class);

    }
}