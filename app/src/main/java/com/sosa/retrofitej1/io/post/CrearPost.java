package com.sosa.retrofitej1.io.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sosa.retrofitej1.R;
import com.sosa.retrofitej1.modelo.Post;

public class CrearPost extends AppCompatActivity {
    private CrearPostViewModel crearPostVM;
    private EditText titulo,cuerpo,userid;
    private Button guardar,siguiente;
    private TextView mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_post);
        crearPostVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CrearPostViewModel.class);
        inicializar();
        crearPostVM.getPost().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                mostrar.setText(post.getTitle());
            }
        });
    }
    public void inicializar(){
        mostrar = findViewById(R.id.TVMostrarPost);
        titulo = findViewById(R.id.ETTitulo);
        cuerpo = findViewById(R.id.ETCuerpo);
        userid = findViewById(R.id.ETUserId);
        guardar = findViewById(R.id.BTGuardar);
        siguiente = findViewById(R.id.BTSiguiente);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int user = Integer.parseInt(userid.getText().toString());
                crearPostVM.guardar(new Post(user,titulo.getText().toString(),cuerpo.getText().toString()));
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPostVM.siguiente();
            }
        });
    }
}