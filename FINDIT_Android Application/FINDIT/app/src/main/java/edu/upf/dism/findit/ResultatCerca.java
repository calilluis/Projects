package edu.upf.dism.findit;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultatCerca extends AppCompatActivity {
    ListView login_status;
    ImageButton btn_settings;
    ImageView image_categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_cerca);
        image_categoria = findViewById(R.id.image_categoria);
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        else {
            String r = getIntent().getStringExtra("id_button").toString();
            doMySearch_byCategory(r);
            if (r.equals("Fruita")){
                image_categoria.setBackgroundResource(R.drawable.mapa_fruita);

            }
            else if (r.equals("Verdura")){
                image_categoria.setBackgroundResource(R.drawable.mapa_verdura);

            }
            else if (r.equals("Brioxeria")){
                image_categoria.setBackgroundResource(R.drawable.mapa_brioxeria);
            }
            else if (r.equals("poma")){
                image_categoria.setBackgroundResource(R.drawable.mapa_brioxeria);
            }


        }

        btn_settings = (ImageButton) findViewById(R.id.imageButton_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isettings = new Intent(ResultatCerca.this, Settings.class);
                startActivity(isettings);
            }
        });

    }
    protected void doMySearch(String str){
        //search
        ArrayList<String> result=((GlobalVariables) this.getApplication()).getresult(str);

        ListView list=(ListView) findViewById(R.id.resultlist);
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);
        list.setAdapter(adapter);
        //System.out.println(str);
//        list.setOnItemClickListener(new ArrayAdapter<>()); ......to display information before

    }
    protected void doMySearch_byCategory(String str){
        //search
        ArrayList<String> result=((GlobalVariables) this.getApplication()).getresult_byCategory(str);

        ListView list=(ListView) findViewById(R.id.resultlist);
        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, result);
        list.setAdapter(adapter);


//        list.setOnItemClickListener(new ArrayAdapter<>()); ......to display information before

    }
}

