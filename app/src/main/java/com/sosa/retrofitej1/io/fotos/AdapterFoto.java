package com.sosa.retrofitej1.io.fotos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sosa.retrofitej1.R;
import com.sosa.retrofitej1.modelo.Photos;

import java.io.InputStream;
import java.util.List;

public class AdapterFoto extends RecyclerView.Adapter<AdapterFoto.MiViewHolder> {
    private List<Photos> lista;
    private Context root;
    private LayoutInflater inflater;

    public AdapterFoto(List<Photos> lista, Context root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public AdapterFoto.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_ft, parent, false);
        return new AdapterFoto.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFoto.MiViewHolder holder, int position) {

        Photos foto = lista.get(position);
        holder.TVTitulo.setText(foto.getTitle());

        GlideUrl url = new GlideUrl(foto.getUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());
        Glide.with(root)//contexto

                .load(url)//url de la imagen
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .into(holder.imagen); // se encarga de setear la imagen

      /* Glide.with(holder.itemView.getContext()).load(foto.getUrl())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .error(R.drawable.ic_launcher_background)
                .override(200, 200) // resizing
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(holder.imagen);*/

    };


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView TVTitulo;
        private ImageView imagen;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            TVTitulo=itemView.findViewById(R.id.TVTituloCV);
            imagen=itemView.findViewById(R.id.ImagenCV);


        }
    }
}
