package com.sosa.retrofitej1;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sosa.retrofitej1.modelo.Post;
import com.sosa.retrofitej1.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Post> postMT;



    public LiveData<Post> getPostMt(){
        if(postMT == null){
            postMT = new MutableLiveData<>();
        }
        return postMT;
    }
    public void leerPost(){
        Call<Post> callPost = ApiClient.getMyApiClient().obtenerPost(1);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    postMT.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            Log.d("Error",t.getMessage());
            }
        });

    }
}
