package com.sosa.retrofitej1.request;

import com.google.gson.*;
import com.sosa.retrofitej1.modelo.Comment;
import com.sosa.retrofitej1.modelo.Photos;
import com.sosa.retrofitej1.modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiClient {
    private static final String UrlBase="https://jsonplaceholder.typicode.com/";
    private static PostInterface postInterface;

    public static PostInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }

    public interface PostInterface{
        @GET("posts")
        Call<List<Post>> obtenerPosts();


        @GET("posts/{id}")
        Call<Post> obtenerPost(@Path("id") int idPost);

        @GET("comments?postId")
        Call<List<Comment>> obtenerComments(@Query("postId") int idPost);

        @POST("posts")
        Call<Post> crearPost(@Body Post post);

        @GET("photos?albumId")
        Call<List<Photos>> obtenerPhotos(@Query("albumId") int albumId);

    }

}
