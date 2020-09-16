package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_reg, btn_face, btn_int, btn_gm;
    EditText u, p;
    TextView omitir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        omitir = (TextView)findViewById(R.id.textView9);
        btn_login = (Button)findViewById(R.id.button_login);
        btn_reg = (Button)findViewById(R.id.button3);
        btn_face = (Button)findViewById(R.id.button4);
        btn_int = (Button)findViewById(R.id.button6);
        btn_gm = (Button)findViewById(R.id.button5);
        u = (EditText) findViewById(R.id.text_usuari);
        /* p =(EditText)findViewById(R.id.text_contrasenya); */
        btn_login.setOnClickListener(new View.OnClickListener() {
            //Toast.makeText(this, "Login Button Pressed", Toast.LENGTH_SHORT).show();
            @Override
            public void onClick(View v) {
                //getUsers(u.getText().toString(),p.getText().toString());
                Intent isi = new Intent(MainActivity.this, Mapa.class);
                startActivity(isi);
//                setContentView(R.layout.activity_mapa);
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent ireg = new Intent(MainActivity.this, Register.class);

                startActivity(ireg);
            }
        });
        btn_face.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });
        btn_int.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });
        btn_gm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });
        omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isi = new Intent(MainActivity.this, Mapa.class);
                startActivity(isi);
//                setContentView(R.layout.activity_mapa);
            }
        });
    }
    public void getUsers(String g, String p){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upf.angeldiaz.es/aism2019_20/ws/autenticacio/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<User> call = service.loginIntoAPI(g, p);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                String r = "";
                boolean aux = false;
                if(!response.isSuccessful()){
                    return;

                }
                User user=response.body();
                if(user.getStatus().getSuccess()!=null){
                    r=user.getStatus().getData().toString();
                    aux = true;
                }
                else if(user.getStatus().getError()!=null){
                    if(user.getStatus().getError()==1){
                        r="Falten paràmetres per omplir";
                    }
                    else if(user.getStatus().getError()==2){
                        r="Nom d'usuari invàlid";
                    }
                    else if(user.getStatus().getError()==3){
                        r="Contrasenya invàlida";
                    }
                    else if(user.getStatus().getError()==4){
                        r="Error en el servidor";
                    }

                }
                if (aux == true) {
                Intent ilogin = new Intent(MainActivity.this, MenuSuper.class);
                ilogin.putExtra("status", r);
                startActivity(ilogin);
                }else{
                    Toast.makeText(getApplicationContext(), r, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }


        });
    }

}
