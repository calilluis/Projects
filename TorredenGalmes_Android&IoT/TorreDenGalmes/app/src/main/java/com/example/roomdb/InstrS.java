package com.example.roomdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InstrS extends AppCompatActivity {
    Button btnsor, btnenda, btnjug;
    public static MyDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "myDao").allowMainThreadQueries().build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instr_s);
        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);
        btnjug = (Button)findViewById(R.id.buttonJugar) ;

        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(InstrS.this, WelcomeS.class);
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(InstrS.this, LogS.class);
                startActivity(ibenda);
            }
        });
        btnjug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = myAppDatabase.myDao().getPlayers().get(0);
                player.setGameID(0);
                player.setCurrentScore(0);

                Intent ibjug = new Intent (InstrS.this, PasS.class);
                startActivity(ibjug);
            }
        });

    }
}
