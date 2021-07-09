package com.example.examenfinalandroid.Services;

import com.example.examenfinalandroid.Entidades.Entrenador;
import com.example.examenfinalandroid.Entidades.Pk;
import com.example.examenfinalandroid.Entidades.PokeCap;
import com.example.examenfinalandroid.Entidades.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPokemonService {


    @POST("pokemons/N00182804/crear")
    Call<Void> crearPokemon(@Body Pokemon pokemon);

    @GET("pokemons/N00182804")
    Call<List<PokeCap>> obtenerPokemons();


    @GET("pokemones/{id}")
    Call<Pokemon> obtenerPokemon(@Path("id") int id);



    @POST("entrenador/N00182804/pokemon")
    Call<Void> capturarPoke(@Body Pk pokemon_id);


    @GET("entrenador/N00182804/pokemones")
    Call<List<PokeCap>> obtenerCapturados();


}
