package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class mapa_global extends AppCompatActivity {
    ImageButton btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_global);
        btn_settings = (ImageButton) findViewById(R.id.imageButton_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isettings = new Intent(mapa_global.this, Settings.class);
                startActivity(isettings);
            }
        });




    }
}

