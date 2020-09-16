package edu.upf.dism.findit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MarketsList extends AppCompatActivity {
    LinearLayout favorites, nears, others;
    View.OnClickListener image_favorites, image_nears, image_others;
    SearchView sv, searchView3;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketslist);
        int ind1=0, ind2=0, ind3=0;
        View.OnClickListener text = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img = new Intent(MarketsList.this, MenuSuper.class);
                startActivity(img);
            }
        };
        int finalInd1 = ind1;
        image_favorites = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)((RelativeLayout)favorites.getChildAt(finalInd1)).getChildAt(1)).setImageResource(R.mipmap.white_star);
                RelativeLayout aux=((RelativeLayout)favorites.getChildAt(finalInd1));
                aux.getChildAt(1).setOnClickListener(image_others);
                favorites.removeView(((RelativeLayout)favorites.getChildAt(finalInd1)));
                others.addView( aux);
            }
        };
        int finalInd = ind2;
        image_nears = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)((RelativeLayout)nears.getChildAt(finalInd)).getChildAt(1)).setImageResource(R.mipmap.yellow_star);
                RelativeLayout aux=((RelativeLayout)nears.getChildAt(finalInd));
                aux.getChildAt(1).setOnClickListener(image_favorites);
                nears.removeView(((RelativeLayout)nears.getChildAt(finalInd)));
                favorites.addView( aux);

            }
        };
        int finalInd2 = ind3;
        image_others = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)((RelativeLayout)others.getChildAt(finalInd2)).getChildAt(1)).setImageResource(R.mipmap.yellow_star);
                RelativeLayout aux=((RelativeLayout)others.getChildAt(finalInd2));
                aux.getChildAt(1).setOnClickListener(image_favorites);
                others.removeView(((RelativeLayout)others.getChildAt(finalInd2)));
                favorites.addView( aux);
            }
        };

        favorites = (LinearLayout) findViewById(R.id.favorites);
        for(int i=0; i<favorites.getChildCount(); i++){
            ind1 = i;
            ((RelativeLayout)favorites.getChildAt(i)).getChildAt(0).setOnClickListener(text);
            ((RelativeLayout)favorites.getChildAt(i)).getChildAt(1).setOnClickListener(image_favorites);
        }
        nears = (LinearLayout) findViewById(R.id.nears);
        for(int i=0; i<nears.getChildCount(); i++){
            ind2 = i;
            ((RelativeLayout)nears.getChildAt(i)).getChildAt(0).setOnClickListener(text);
            ((RelativeLayout)nears.getChildAt(i)).getChildAt(1).setOnClickListener(image_nears);
        }
        others = (LinearLayout) findViewById(R.id.others);
        for(int i=0; i<others.getChildCount(); i++){
            ind3 = i;
            ((RelativeLayout)others.getChildAt(i)).getChildAt(0).setOnClickListener(text);
            ((RelativeLayout)others.getChildAt(i)).getChildAt(1).setOnClickListener(image_others);
        }


        sv = (SearchView) findViewById(R.id.searchView3);
        sv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
            }
        });

        /*sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), "Funcionalitat actualment no disponible", Toast.LENGTH_SHORT).show();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });*/

    }
}
