package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Victor on 20/04/2016.
 */
public class RestHelper {

    public static final String BASE_URL = "192.176.0.1";

    //public static RestHelper retrofit(){

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd'T'hh:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    //}
}