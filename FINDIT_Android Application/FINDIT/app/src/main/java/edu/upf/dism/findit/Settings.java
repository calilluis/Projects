package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;


public class Settings extends AppCompatActivity {
    Button button_eliminar,button_logout, button_canviar_contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        button_eliminar = (Button)findViewById(R.id.button_eliminar);
        button_logout = (Button)findViewById(R.id.button_logout);
        button_canviar_contrasenya = (Button)findViewById(R.id.button_canviar_contrasenya);


        button_eliminar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });
        button_canviar_contrasenya.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
