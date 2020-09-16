package com.example.roomdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasS extends AppCompatActivity {
    Button btnsor, btnenda, btnjug;
    private EditText playerSpot;
    public static MyDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "myDao").allowMainThreadQueries().build();
        final Player player = myAppDatabase.myDao().getPlayers().get(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_s);
        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);
        btnjug = (Button)findViewById(R.id.buttonJugar) ;

        playerSpot = findViewById(R.id.Pas);

        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(PasS.this, InstrS.class);
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(PasS.this, LogS.class);
                startActivity(ibenda);
            }
        });
        btnjug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setSpot(Integer.parseInt(playerSpot.getText().toString()));
                myAppDatabase.myDao().updatePlayer(player);
                Intent ibenda = new Intent(PasS.this, FonaS.class);
                startActivity(ibenda);
            }
        });
    }
}
