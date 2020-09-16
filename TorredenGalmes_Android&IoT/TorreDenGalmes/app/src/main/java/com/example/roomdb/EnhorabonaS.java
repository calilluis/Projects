package com.example.roomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnhorabonaS extends AppCompatActivity {
    Button btnseg, btnsor, btnenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhorabona_s);
        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);
        btnseg = (Button)findViewById(R.id.buttonseg);

        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(EnhorabonaS.this, FonaS.class);
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibsor = new Intent(EnhorabonaS.this, LogS.class);
                startActivity(ibsor);

            }
        });

        btnseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(EnhorabonaS.this, FinalS.class);
                startActivity(ibenda);

            }
        });
    }
}
