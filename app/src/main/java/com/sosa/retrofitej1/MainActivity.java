package com.sosa.retrofitej1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sosa.retrofitej1.io.post.CrearPost;
import com.sosa.retrofitej1.modelo.Post;

public class MainActivity extends AppCompatActivity {
    private TextView tvMostrar;
    private MainActivityViewModel mv;
    private Button irA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMostrar= findViewById(R.id.TVMostrar);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        irA = findViewById(R.id.BTIrA);
        mv.getPostMt().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                tvMostrar.setText(post.getBody()+"\n");
            }
        });
        mv.leerPost();
        irA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearPost.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}