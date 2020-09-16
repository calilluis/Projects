package edu.upf.dism.findit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;


//TODO: map of <Key, Value>, where Key is the product and Value is a tuple of (category, localitzation) DONE
//TODO: display the categories correctly JUDIT
//TODO: implement a searcher with the previous created map DONE
//TODO: we will probably need some type of global variables where we store the csv processed! DONE


public class MenuSuper extends AppCompatActivity {
    Button prova1, mapaglobal, cerca, button_ofertes;
    LinearLayout layoutb, layoutb2;
    // String x;
    ImageButton btn_settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_super);
        //x = ((GlobalVariables) this.getApplication()).getCategories().toArray()[0].toString();

        layoutb = (LinearLayout) findViewById(R.id.layout_buttons);
        layoutb2 = (LinearLayout) findViewById(R.id.layout_buttons2);
        cerca = (Button) findViewById(R.id.cerca);
        Drawable imgc = cerca.getContext().getResources().getDrawable( R.drawable.lupablanca );
        cerca.setCompoundDrawablesWithIntrinsicBounds( imgc , null,null , null);
        button_ofertes = (Button) findViewById(R.id.button_ofertes);
        button_ofertes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iof = new Intent(MenuSuper.this, Ofertes.class);
                startActivity(iof);
            }
        });



        //afegirbuttons(5,layoutb);

        Button button = findViewById(R.id.cerca);
        mapaglobal = (Button)findViewById(R.id.button_mapag);

        mapaglobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img = new Intent(MenuSuper.this, mapa_global.class);
                startActivity(img);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSearchRequested();
            }
        });


        boolean firstline=true;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().openRawResource(R.raw.demo_super)));//Specify asset file name
            String[] nextLine;
            int i=0;
            if(((GlobalVariables)this.getApplication()).isAlready_filled()!= true) {
                while ((nextLine = reader.readNext()) != null) {
                    if (!firstline) {//ignore first line
                        i++;
                        //set of categories
                        String s = nextLine[0];

                        String[] splitted = s.split(";");
                        ((GlobalVariables) this.getApplication()).addCategory(splitted[0]);

                        //all products
                        ((GlobalVariables) this.getApplication()).addProduct(splitted);



                    }
                    firstline = false;
                }
                ((GlobalVariables)this.getApplication()).setAlready_filled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }


        System.out.println(((GlobalVariables) this.getApplication()).getCategories()); //DONE
        System.out.println(((GlobalVariables) this.getApplication()).getProducts()); //DONE

        int sizee = ((GlobalVariables) this.getApplication()).getCategories().size();


        for (int i=0; i<sizee; i++){
            Button btt = new Button(getApplicationContext());
            //btt.setText("Botó número:"+(i+1));
            String x = ((GlobalVariables) this.getApplication()).getCategories().toArray()[i].toString();
            btt.setText(x);
            btt.setDuplicateParentStateEnabled(true);
            btt.setClickable(false);
            //btt.setBackgroundColor(Color.rgb(	74, 35, 67));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,  //amplada
                    ViewGroup.LayoutParams.WRAP_CONTENT   //alçada
            );
            param.setMargins(10, 5, 10, 5);
            btt.setLayoutParams(param);
            btt.setTextColor(Color.rgb(255,255,255));
            btt.setBackgroundResource(R.drawable.button_rodo);
            btt.setPadding(30,10,10,10);
            Drawable imgf = btt.getContext().getResources().getDrawable( R.drawable.fruita );
            Drawable imgv = btt.getContext().getResources().getDrawable( R.drawable.verdura );
            Drawable imgb = btt.getContext().getResources().getDrawable( R.drawable.brioxeria );


            if (x.equals("Fruita")){
                btt.setCompoundDrawablesWithIntrinsicBounds( imgf , null,null , null);

            }
            else if (x.equals("Verdura")){
                btt.setCompoundDrawablesWithIntrinsicBounds( imgv, null, null, null);

            }
            else if (x.equals("Brioxeria")){
                btt.setCompoundDrawablesWithIntrinsicBounds( imgb, null, null, null);
            }

            //img.setBounds( 20, 20, 20, 20 );


            //btt.setBackground(Drawable.createFromPath("@drawable/button_rodo"));

            if ((i%2==0)){
                layoutb.addView(btt);
            }
            else{
                layoutb2.addView(btt);
            }

        }
        for(int i=0; i<layoutb.getChildCount(); i++){
            String id = ((Button)layoutb.getChildAt(i)).getText().toString();
            layoutb.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent img = new Intent(MenuSuper.this, ResultatCerca.class);
                    img.putExtra("id_button", id);
                    startActivity(img);
                }
            });
        }
        for(int i=0; i<layoutb2.getChildCount(); i++){
            String id = ((Button)layoutb2.getChildAt(i)).getText().toString();
            layoutb2.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent img = new Intent(MenuSuper.this, ResultatCerca.class);
                    img.putExtra("id_button", id);
                    startActivity(img);
                }
            });
        }

        btn_settings = (ImageButton) findViewById(R.id.imageButton_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent isettings = new Intent(MenuSuper.this, Settings.class);
                startActivity(isettings);
            }
        });

    }

}
