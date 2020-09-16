package edu.upf.dism.findit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button btn_si;
    TextView login_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        String r = getIntent().getStringExtra("status");
        login_status = (TextView)findViewById(R.id.textView3);
        login_status.setText(r);
    }

}