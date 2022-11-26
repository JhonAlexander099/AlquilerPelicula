package com.example.alquilerpelicula;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.ViewHolder> implements Filterable {

    private List<Pelicula> misdatos;
    private List<Pelicula> misdatosfull;


    public AdaptadorPelicula(List<Pelicula> misdatos) {
        this.misdatos = misdatos;
        misdatosfull = new ArrayList<>(misdatos);
    }


    @NonNull
    @Override
    public AdaptadorPelicula.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.muestrapelicula,parent,false);

        ViewHolder miViewHolder = new AdaptadorPelicula.ViewHolder(vista);
        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula pelicula = misdatos.get(position);
        Glide.with(holder.imgPoster.getContext()).load(pelicula.getPoster()).into(holder.imgPoster);
        holder.tvTitulo.setText(pelicula.getTitulo());
        holder.tvGenero.setText(pelicula.getGenero());
        holder.tvSinopsis.setText(pelicula.getSinopsis());
        holder.position = position;
        holder.pelicula = pelicula;
    }

    @Override
    public int getItemCount() {
        return misdatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitulo, tvGenero,tvSinopsis;

        public int position;
        Pelicula pelicula;
        ConstraintLayout btnpelicula;

        public ViewHolder(@NonNull View vista) {
            super(vista);
            imgPoster = vista.findViewById(R.id.imgPoster);
            tvTitulo = vista.findViewById(R.id.tvTitulo);
            tvGenero = vista.findViewById(R.id.tvGenero);
            tvSinopsis = vista.findViewById(R.id.tvSinopsis);
            btnpelicula = vista.findViewById(R.id.muestrapeli);

            btnpelicula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(vista.getContext(), mostrarpelicula.class);
                    intent.putExtra("id",pelicula.getId());
                    vista.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pelicula> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(misdatosfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Pelicula item : misdatosfull) {
                    if (item.getTitulo().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            misdatos.clear();
            misdatos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public Filter getFilter2() {
        return exampleFilter2;
    }

    private Filter exampleFilter2 = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Pelicula> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(misdatosfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Pelicula item : misdatosfull) {
                    if (item.getGenero().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            misdatos.clear();
            misdatos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
