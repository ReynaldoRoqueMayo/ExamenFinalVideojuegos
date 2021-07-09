package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenfinalandroid.Entidades.Pk;
import com.example.examenfinalandroid.Entidades.PokeCap;
import com.example.examenfinalandroid.Services.IPokemonService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad);




        Intent intent=getIntent();


        String pokeId=intent.getStringExtra("pokemon_id");

        String latitud=intent.getStringExtra("Latitud");

        String longitud=intent.getStringExtra("Longitud");

        String imagen=intent.getStringExtra("Imagen");
        String nombre=intent.getStringExtra("Nombre");
        String tipo=intent.getStringExtra("Tipo");

        Log.i("DETALLE ACTIVIDAD","SI SE ABRIO LA ACTIVIDAD");

        ImageView imgPok= findViewById(R.id.imgPokemonDetalle);
        Picasso.get().load(imagen).resize (200, 200).into(imgPok);

        TextView tvNom= findViewById(R.id.tvNombreDetalle);
        tvNom.setText(nombre);

        TextView tvTip= findViewById(R.id.tvTipoDetalle);
        tvTip.setText(tipo);





        Button btnCapturar= findViewById(R.id.btnCapturar);

        btnCapturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IPokemonService servicioPokemon = retrofit.create(IPokemonService.class);




                int pokemon_id=Integer.parseInt(pokeId);

                Pk pk=new Pk();


                pk.pokemon_id=pokemon_id;

                Call<Void> capturarPokem=servicioPokemon.capturarPoke(pk);

              capturarPokem.enqueue(new Callback<Void>() {
                  @Override
                  public void onResponse(Call<Void> call, Response<Void> response) {


                      Intent intento=new Intent(DetalleActividad.this,MainActivity.class);
                      startActivity(intento);

                  }

                  @Override
                  public void onFailure(Call<Void> call, Throwable t) {

                  }
              });






            }
        });






        Button btnUbicacion=findViewById(R.id.btnVerMapa);
        btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento = new Intent(DetalleActividad.this,MapsActivity.class);
                intento.putExtra("Latitud",latitud);
                intento.putExtra("Longitud",longitud);
                intento.putExtra("Nombre",nombre);

                startActivity(intento);


            }

        });








    }
}