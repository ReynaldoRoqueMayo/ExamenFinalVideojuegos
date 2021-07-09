package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinalandroid.Entidades.Entrenador;
import com.example.examenfinalandroid.Entidades.ImageUtil;
import com.example.examenfinalandroid.Entidades.Pokemon;
import com.example.examenfinalandroid.Services.IEntrenadorService;
import com.example.examenfinalandroid.Services.IPokemonService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IEntrenadorService servicio = retrofit.create(IEntrenadorService.class);


        Call<Entrenador> getEntrenador =servicio.obtenerEntrenador();

        getEntrenador.enqueue(new Callback<Entrenador>() {
            @Override
            public void onResponse(Call<Entrenador> call, Response<Entrenador> response) {
                Entrenador entrenador=response.body();
                if(entrenador!=null){


                    ImageView imagenEntrenador= findViewById(R.id.imvEntrenador);

                    Picasso.get().load(entrenador.imagen).resize (150, 150).into(imagenEntrenador);

                    TextView tvNombres= findViewById(R.id.tvNombres);
                    TextView tvPueblo= findViewById(R.id.tvPueblo);

                    tvNombres.setText(entrenador.nombres);
                    tvPueblo.setText(entrenador.pueblo);
                }else{
                    Intent intento = new Intent(MainActivity.this,RegistrarEntrenadorActivity.class);
                    startActivity(intento);
                }












            }

            @Override
            public void onFailure(Call<Entrenador> call, Throwable t) {

                Intent intento = new Intent(MainActivity.this,RegistrarEntrenadorActivity.class);
                startActivity(intento);

            }
        });


        Button btnCrearPoke= findViewById(R.id.btnCrearPoke);
        Button btnVerPoke= findViewById(R.id.btnVerPokes);
        Button btnCapturados= findViewById(R.id.btnVerCapturados);


        btnCapturados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intento= new Intent(MainActivity.this,ListaCapturadosActivity.class);
                startActivity(intento);


            }
        });




        btnCrearPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento = new Intent(MainActivity.this,CrearPokemonActivity.class);
                startActivity(intento);
            }
        });


        btnVerPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intento = new Intent (MainActivity.this,ListaPokemons.class);
                startActivity(intento);




            }
        });



    }
}