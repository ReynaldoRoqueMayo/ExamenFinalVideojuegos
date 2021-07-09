package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.examenfinalandroid.Entidades.Entrenador;
import com.example.examenfinalandroid.Entidades.ImageUtil;
import com.example.examenfinalandroid.Entidades.Pokemon;
import com.example.examenfinalandroid.Services.IEntrenadorService;
import com.example.examenfinalandroid.Services.IPokemonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPokemonActivity extends AppCompatActivity {

    Bitmap bmImagen;
    ImageView ivPoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pokemon);



        solicitarPermisos();



        ivPoke=findViewById(R.id.imvPrevPoke);
        Button btnCrear=findViewById(R.id.btnCrearP);
        Button btnGalleria=findViewById(R.id.btnAddPokeImage);



        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IPokemonService servicio = retrofit.create(IPokemonService.class);


                Pokemon pokemon=new Pokemon();




                EditText edtNombre= findViewById(R.id.edtNombreP);
                EditText edtTipo= findViewById(R.id.edtTipoP);
                EditText edtLat= findViewById(R.id.edtLatP);
                EditText edtLon= findViewById(R.id.edtLongP);

                pokemon.imagen= ImageUtil.convert(bmImagen);
                pokemon.nombre=edtNombre.getText().toString();
                pokemon.tipo=edtTipo.getText().toString();
                pokemon.latitude= Float.parseFloat(edtLat.getText().toString());
                pokemon.longitude=Float.parseFloat(edtLon.getText().toString());


                Call<Void> setPokemon =servicio.crearPokemon(pokemon);

                setPokemon.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("CREAR_POKEMON_ACT","SE CREO EL POKEMON");

                        Intent intento = new Intent(CrearPokemonActivity.this,MainActivity.class);
                        startActivity(intento);

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        });








        btnGalleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,103);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==103 && resultCode==RESULT_OK){

            Uri imagenSeleccionada = data.getData();
            String[] pathArchivo= {MediaStore.Images.Media.DATA};
            Cursor cursor= getContentResolver().query(imagenSeleccionada,pathArchivo,null,null,null);
            cursor.moveToFirst();
            int indiceColumna=cursor.getColumnIndex(pathArchivo[0]);
            String pathImagen= cursor.getString(indiceColumna);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(pathImagen);

            //redimensionar la imagen para



            bmImagen=bitmap;

            ivPoke.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 120, 120, false));


            //



        }

    }





    public void solicitarPermisos() {
        //verificamos si el permiso no fue entregado el permiso para la camara
        if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED ){
            requestPermissions(new String[]{Manifest.permission.CAMERA},1001);

        }

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED ){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1002);

        }
    }



}