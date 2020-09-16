package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mapa extends AppCompatActivity {
    Button btn_si, btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        btn_si = (Button)findViewById(R.id.button_si);
        btn_no = (Button)findViewById(R.id.button_no);
        btn_si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isi = new Intent(Mapa.this, MenuSuper.class);
                startActivity(isi);
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isi = new Intent(Mapa.this, MarketsList.class);
                startActivity(isi);
            }
        });


    }
}
