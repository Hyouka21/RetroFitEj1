package com.sosa.retrofitej1.io.fotos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sosa.retrofitej1.modelo.Photos;
import com.sosa.retrofitej1.modelo.Post;
import com.sosa.retrofitej1.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotoRVViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Photos>> photos;
    public FotoRVViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<Photos>> getPhotos(){
        if(photos==null){
            photos = new MutableLiveData<>();
        }
        return photos;
    }

    public void cargar(){

            Call<List<Photos>> callPhotos = ApiClient.getMyApiClient().obtenerPhotos(1);
        callPhotos.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if(response.isSuccessful()) {
                photos.postValue(response.body());
                    Toast.makeText(context, "Se obtuvo con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Toast.makeText(context, "Algo fallo"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        }
    }

