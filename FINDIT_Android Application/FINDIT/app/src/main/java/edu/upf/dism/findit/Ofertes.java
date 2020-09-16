package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Ofertes extends AppCompatActivity {

    ImageButton btn_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertes);
        btn_settings = (ImageButton) findViewById(R.id.imageButton_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isettings = new Intent(Ofertes.this, Settings.class);
                startActivity(isettings);
            }
        });

    }}