package edu.upf.dism.findit;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.collections.functors.AnyPredicate;
import org.json.JSONArray;


import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface APIService {
    /*
    @GET("login")
    Call<List<User>> getUsers();
    */

    //KOTLIN
    // @POST("login")
    /*public fun loginIntoAPI(@Field("grup") group:String,
                            @Field("motdepas") password:String)
        :Call<loginResponse>*/

    @POST("login")
    @FormUrlEncoded
    Call<User> loginIntoAPI(@Field("grup") String grup, @Field("motdepas") String password);

    @POST("registre")
    @FormUrlEncoded
    Call<User> registerIntoAPI(@Field("grup") String grup, @Field("motdepas") String password, @Field("nias[]") String[] n);
 /*
    @POST("registre")
    @FormUrlEncoded
    Call<RegisterResponse> registreIntoAPI(@Field("grup") String grup,
                                           @Field("motdepas") String password,
                                           @Field("nias") ArrayList NIAs) {
        return null;
    }
    */

}
