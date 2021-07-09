package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.examenfinalandroid.Adapters.PokemonsAdapter;
import com.example.examenfinalandroid.Entidades.PokeCap;
import com.example.examenfinalandroid.Entidades.Pokemon;
import com.example.examenfinalandroid.Services.IPokemonService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPokemons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pokemons);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPokemonService servicioPokemon = retrofit.create(IPokemonService.class);


        Call<List<PokeCap>> llamadoPokemons=servicioPokemon.obtenerPokemons();

        llamadoPokemons.enqueue(new Callback<List<PokeCap>>() {

            @Override
            public void onResponse(Call<List<PokeCap>> call, Response<List<PokeCap>> response) {

                RecyclerView rv = findViewById(R.id.rvMisPokemon);

                rv.setLayoutManager(new LinearLayoutManager(ListaPokemons.this));


                PokemonsAdapter pokemonsAdapter = new PokemonsAdapter(response.body());

                rv.setAdapter(pokemonsAdapter);

            }

            @Override
            public void onFailure(Call<List<PokeCap>> call, Throwable t) {

            }

        });







    }
}









