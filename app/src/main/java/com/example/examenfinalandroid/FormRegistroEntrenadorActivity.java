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
import com.example.examenfinalandroid.Services.IEntrenadorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormRegistroEntrenadorActivity extends AppCompatActivity {


    ImageView ivEntrenador;
    Bitmap bImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registro_entrenador);


        solicitarPermisos();

        ivEntrenador=findViewById(R.id.imvAddEntrenador);
        Button btnTomarFoto=findViewById(R.id.btnEntrenadorCamara);
        Button btnGalleria=findViewById(R.id.btnEntrenadorGaleria);

        Button btnRegistro= findViewById(R.id.btnCrearEntrenador);


        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,101);

            }
        });

        btnGalleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,102);

            }
        });




        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://upn.lumenes.tk/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IEntrenadorService servicio = retrofit.create(IEntrenadorService.class);

                Entrenador entrenador = new Entrenador();

                EditText edtNombres= findViewById(R.id.edtNombreEntrenador);
                EditText edtPueblo = findViewById(R.id.edtPuebloEntrenador);

                entrenador.imagen= ImageUtil.convert(bImagen);
                entrenador.nombres=edtNombres.getText().toString();
                entrenador.pueblo=edtPueblo.getText().toString();

                Call<Void> setEntrenador= servicio.registrarEntrenador(entrenador);

                setEntrenador.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("FORM_REGISTRO_ENTRENADOR","SE REGISTRO EL ENTRENADOR");

                        Intent intento= new Intent(FormRegistroEntrenadorActivity.this,MainActivity.class);

                        startActivity(intento);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });






            }
        });






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK){
            Bundle extras= data.getExtras();
            Bitmap btm = (Bitmap) extras.get("data");

            //redimensionar la imagen


            bImagen=btm;
            ivEntrenador.setImageBitmap(Bitmap.createScaledBitmap(btm, 150, 150, false));
        }
        if(requestCode==102 && resultCode==RESULT_OK){

            Uri imagenSeleccionada = data.getData();
            String[] pathArchivo= {MediaStore.Images.Media.DATA};
            Cursor cursor= getContentResolver().query(imagenSeleccionada,pathArchivo,null,null,null);
            cursor.moveToFirst();
            int indiceColumna=cursor.getColumnIndex(pathArchivo[0]);
            String pathImagen= cursor.getString(indiceColumna);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(pathImagen);

            //redimensionar la imagen para




            bImagen=bitmap;

            ivEntrenador.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 150, 150, false));


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