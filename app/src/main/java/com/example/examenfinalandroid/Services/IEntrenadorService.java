package com.example.examenfinalandroid.Services;

import com.example.examenfinalandroid.Entidades.Entrenador;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IEntrenadorService {


    @GET("entrenador/N00182804")
    Call<Entrenador> obtenerEntrenador();


    @POST("entrenador/N00182804")
    Call<Void> registrarEntrenador(@Body Entrenador entrenador);


}
