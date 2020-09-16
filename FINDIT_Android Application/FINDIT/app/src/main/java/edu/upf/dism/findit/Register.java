package edu.upf.dism.findit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    Button btn_entrar;
    EditText u, p, n1, n2, n3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btn_entrar = (Button)findViewById(R.id.button_login);
        u = (EditText) findViewById(R.id.text_usuari);
        p = (EditText)findViewById(R.id.text_contrasenya);
        //n1 = (EditText)findViewById(R.id.text_nia1);
        //n2 = (EditText)findViewById(R.id.text_nia2);
       // n3 = (EditText)findViewById(R.id.text_nia3);
        btn_entrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                getUsers(u.getText().toString(),p.getText().toString(),
                        n1.getText().toString(),n2.getText().toString(), n3.getText().toString() );

            }
        });

    }

    private void getUsers(String g, String p, String n1, String n2, String n3){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upf.angeldiaz.es/aism2019_20/ws/autenticacio/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        String[] n = new String[3];
        n[0]=n1;
        n[1]=n2;
        n[2]=n3;
        System.out.println("----------------------------------");
        System.out.println(n);
        Call<User> call = service.registerIntoAPI(g, p, n);
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
                    r=user.getStatus().getSuccess().toString();
                    aux =true;
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
                        r="Nias invàlids";
                    }
                    else if(user.getStatus().getError()==5){
                        r="Aquest grup ja està registrat";
                    }

                    else if(user.getStatus().getError()==6){
                        r="Errors en la comanda SQL";
                    }
                    else if(user.getStatus().getError()==7){
                        r="Error en el servidor";
                    }



                }
                Toast.makeText(getApplicationContext(), r, Toast.LENGTH_SHORT).show();
                if (aux == true) {
                    Intent ilogin = new Intent(Register.this, MainActivity.class);
                    startActivity(ilogin);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }


        });
    }

}