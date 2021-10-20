package com.sosa.retrofitej1.io.post;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sosa.retrofitej1.io.fotos.FotoRV;
import com.sosa.retrofitej1.modelo.Post;
import com.sosa.retrofitej1.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPostViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Post> post;
    public CrearPostViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Post> getPost(){
        if(post==null){
            post = new MutableLiveData<>();
        }
        return post;
    }
    public void siguiente(){
        Intent intent = new Intent(context, FotoRV.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    public void guardar(Post postNuevo){
        if(postNuevo!=null) {
            Call<Post> callPost = ApiClient.getMyApiClient().crearPost(postNuevo);
            callPost.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.isSuccessful()) {
                        post.postValue(response.body());
                        Toast.makeText(context, "Se Guardo Con Exito"+response.body().getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(context, "No se pudo guardar\n"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
