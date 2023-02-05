package com.itproger.kinopoisk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {

   private Context context;
   private List<FilmModel> films;
    private final String TAG = "DEV";


    public FilmsAdapter(Context context, List<FilmModel> films) {
        this.context = context;
        this.films = films;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.film_list_item, parent, false);
        return new FilmViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(films.get(position).getNameRu());
        holder.year.setText(films.get(position).getYear());
        //используем библиотеку Glide для отображения изображений
        Glide.with(context)
                .load(films.get(position).getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView year;
        ImageView img;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.filmName);
            year = itemView.findViewById(R.id.filmYear);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}