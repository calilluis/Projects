package com.example.roomdb;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeS extends AppCompatActivity {
    private TextView WelcomeText;
    Button btnseg, btnsor, btnenda;
    public static MyDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "myDao").allowMainThreadQueries().build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_s);

        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);
        btnseg = (Button)findViewById(R.id.buttonseg);

        WelcomeText = findViewById(R.id.textWelc);
        Player player = myAppDatabase.myDao().getPlayers().get(0);
        WelcomeText.setText("Hola " + player.getName()+"! Jo sóc l'Àlex Pons. Ens trobem a un poblat Talaiòtic, a la Torre d'en Galmés. Jo he viscut aquí durant anys i avui et vull portar amb mi. Segueix les indicacions per descobrir els secrets que amaga el meu poblat.");

        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(WelcomeS.this, LogS.class);
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

       btnseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent ibenda = new Intent(WelcomeS.this, InstrS.class);
                startActivity(ibenda);

            }
        });


    }
}
