package com.sosa.retrofitej1.io.fotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sosa.retrofitej1.R;
import com.sosa.retrofitej1.modelo.Photos;

import java.util.List;

public class FotoRV extends AppCompatActivity {
    private FotoRVViewModel fotoRVVM;
    private RecyclerView ReciclerViewFoto;
    private AdapterFoto adapterFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_rv);
        ReciclerViewFoto = findViewById(R.id.RVFoto);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        fotoRVVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(FotoRVViewModel.class);
        fotoRVVM.getPhotos().observe(this, new Observer<List<Photos>>() {
            @Override
            public void onChanged(List<Photos> photos) {
                ReciclerViewFoto.setLayoutManager(linearLayoutManager);
                adapterFoto = new AdapterFoto(photos,getApplicationContext(),getLayoutInflater());

                ReciclerViewFoto.setAdapter(adapterFoto);
            }
        });
        fotoRVVM.cargar();
    }
}