package com.example.examenfinalandroid.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalandroid.DetalleActividad;
import com.example.examenfinalandroid.Entidades.PokeCap;
import com.example.examenfinalandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokemonsAdapterC extends RecyclerView.Adapter<PokemonsAdapterC.PokemonsViewHolderC> {

    public List<PokeCap> pokemonesC= new ArrayList<>();

    public PokemonsAdapterC(List<PokeCap> pokemones) {

        Collections.reverse(pokemones);
        this.pokemonesC = pokemones;
    }

    @Override
    public PokemonsViewHolderC onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_pokemonc,parent,false);

        return new PokemonsViewHolderC(view);



    }

    @Override
    public void onBindViewHolder( PokemonsAdapterC.PokemonsViewHolderC holder, int position) {

        View view =holder.itemView;


        PokeCap pokemonElement= pokemonesC.get(position);

        TextView nombre=view.findViewById(R.id.tvNombreC);

        TextView tipo=view.findViewById(R.id.tvTipoC);

        nombre.setText(pokemonElement.nombre);

        tipo.setText(pokemonElement.tipo);

        //imagen
        ImageView caratula=view.findViewById(R.id.imgPokemonC);

        String imagenPok=pokemonElement.url_imagen;
        if(pokemonElement.url_imagen.startsWith("/")){
            imagenPok="https://upn.lumenes.tk"+imagenPok;

        }

        Picasso.get().load(imagenPok).resize (80, 80).into(caratula);



    }

    @Override
    public int getItemCount() {
        return pokemonesC.size();
    }


    public class PokemonsViewHolderC extends RecyclerView.ViewHolder {
        public PokemonsViewHolderC( View itemView) {
            super(itemView);
        }
    }
}
