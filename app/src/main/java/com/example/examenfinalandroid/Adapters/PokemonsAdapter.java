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
import com.example.examenfinalandroid.Entidades.Pokemon;
import com.example.examenfinalandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonsAdapter.PokemonsViewHolder> {

    public List<PokeCap> pokemones= new ArrayList<>();

    public PokemonsAdapter(List<PokeCap> pokemones) {

        Collections.reverse(pokemones);
        this.pokemones = pokemones;
    }

    @Override
    public PokemonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_pokemon,parent,false);

        return new PokemonsViewHolder(view);



    }

    @Override
    public void onBindViewHolder( PokemonsAdapter.PokemonsViewHolder holder, int position) {

        View view =holder.itemView;


        PokeCap pokemonElement= pokemones.get(position);

        TextView nombre=view.findViewById(R.id.tvNombreL);
        TextView tipo=view.findViewById(R.id.tvTipoL);

        nombre.setText(pokemonElement.nombre);

        tipo.setText(pokemonElement.tipo);

        //imagen
        ImageView caratula=view.findViewById(R.id.imgPokemonL);

        String imagenPok=pokemonElement.url_imagen;
        if(pokemonElement.url_imagen.startsWith("/")){
            imagenPok="https://upn.lumenes.tk"+imagenPok;

        }

        Picasso.get().load(imagenPok).resize (70, 70).into(caratula);

        Button btnDetall= view.findViewById(R.id.btnDetalleL);
        btnDetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento= new Intent(v.getContext(), DetalleActividad.class);

                String imgP=pokemonElement.url_imagen;
                if(pokemonElement.url_imagen.startsWith("/")){
                    imgP="https://upn.lumenes.tk"+imgP;

                }

                intento.putExtra("Imagen",imgP);
                intento.putExtra("Nombre",pokemonElement.nombre);
                intento.putExtra("Tipo",pokemonElement.tipo);
                intento.putExtra("pokemon_id",String.valueOf(pokemonElement.id));
                intento.putExtra("Latitud",pokemonElement.latitude);
                intento.putExtra("Longitud",pokemonElement.longitude);




                v.getContext().startActivity(intento);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemones.size();
    }


    public class PokemonsViewHolder extends RecyclerView.ViewHolder {
        public PokemonsViewHolder( View itemView) {
            super(itemView);
        }
    }
}
