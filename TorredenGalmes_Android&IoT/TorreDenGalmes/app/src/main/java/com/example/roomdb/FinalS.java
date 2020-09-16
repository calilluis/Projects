package com.example.roomdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalS extends AppCompatActivity {
    Button btnsor, btnenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_s);
        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);

        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(FinalS.this, EnhorabonaS.class);
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibsor = new Intent(FinalS.this, LogS.class);
                startActivity(ibsor);

            }
        });
    }
}
