package com.example.roomdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import org.w3c.dom.Node;

public class FonaS extends AppCompatActivity {
    Button btnseg, btnsor, btnenda;
    ImageButton btnstart, btnstop, btnu;
    Chronometer crono;
    TextView scoreTxt;
    int score;
    boolean bul=false;

    ListView prova;

    public void onStart(final Node node)
    {
        Looper.prepare();
    }

    ArrayList<Item> items;
    public static MyDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        items= new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fona_s);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "myDao").allowMainThreadQueries().build();

        final Player player = myAppDatabase.myDao().getPlayers().get(0);
        score = player.getCurrentScore();

        btnenda = (Button)findViewById(R.id.buttonenda);
        btnsor = (Button)findViewById(R.id.buttonsor);
        //btnseg = (Button)findViewById(R.id.buttonseg);
        crono = (Chronometer) findViewById(R.id.crono);
        //btnstart = (ImageButton) findViewById(R.id.buttonStart);
       // btnstop = (ImageButton) findViewById(R.id.buttonStop);
       // btnu = (ImageButton) findViewById(R.id.buttonu);

        scoreTxt = findViewById(R.id.textPunts);
        scoreTxt.setText(String.valueOf(player.getCurrentScore()));

        //prova = findViewById(R.id.prova);
        //--
        crono.setBase(SystemClock.elapsedRealtime());
        crono.start();

        
        btnenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibenda = new Intent(FonaS.this, PasS.class );
                startActivity(ibenda);
            }
        });

        btnsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ibsor = new Intent(FonaS.this, LogS.class);
                startActivity(ibsor);

            }
        });

        setRepeatingAsyncTask(player);

        /*btnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = score + 1;
                player.setCurrentScore(score);
                scoreTxt.setText(String.valueOf(score));
                if(player.getCurrentScore() == 3)
                {
                    Intent ibsegu = new Intent(FonaS.this, EnhorabonaS.class);
                    startActivity(ibsegu);
                    //crono.stop();

                }
            }
        });*/

       /*
        btnseg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(player.getCurrentScore() == 3)
                {
                    Intent ibseg = new Intent(FonaS.this, EnhorabonaS.class);
                    startActivity(ibseg);
                    //crono.stop();
                }
            }
        });*/

        /*btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setRepeatingAsyncTask(player);
                //Log.d("tg", "drytjujjtutyuytfufu");
                setRepeatingAsyncTask(player);
                //crono.setBase(SystemClock.elapsedRealtime());
                //crono.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crono.stop();
            }
        });*/
    }



    private void setRepeatingAsyncTask(final Player player)
    {
        Timer timer = new Timer();

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                RetrieveFeed getXML = new RetrieveFeed();

                if(!bul) {
                    getXML.execute();
                    bul = true;
                }

                if(getXML.getStatus() != AsyncTask.Status.FINISHED)
                {
                    boolean flag = true;
                    while(flag)
                    {
                        if(getXML.getStatus() == AsyncTask.Status.FINISHED)
                            flag = false;
                    }
                }

                if(getXML.getStatus() == AsyncTask.Status.FINISHED)
                {
                    items=getXML.items();

                    if(items.size() != 0)
                    {
                        for(int j = 0; j < items.size(); j++)
                        {
                            Item item = items.get(j);
                            Item dbItem = FonaS.myAppDatabase.myDao().getItems().get(item.getItemID());

                            //String item_id = Integer.toString(item.getItemID());

                            switch (item.getPort())
                            {
                                case 1:
                                    if (player.getSpot() != 1)
                                        break;
                                    if (!dbItem.getIsActive())
                                        break;
                                    if (item.getItemID() == 1 || item.getItemID() == 2 || item.getItemID() == 3 || item.getItemID() == 4 || item.getItemID() == 5){
                                        score = score + 1;
                                        player.setCurrentScore(score);


                                        dbItem.setIsActive(false);
                                        dbItem.setPort(-1);

                                        if(player.getCurrentScore() == 3)
                                        {
                                            Intent ibseg = new Intent(FonaS.this, EnhorabonaS.class);
                                            startActivity(ibseg);
                                            //crono.stop();
                                        }
                                    }
                                    break;

                                case 2:
                                    if (player.getSpot() != 2)
                                        break;
                                    if (!dbItem.getIsActive())
                                        break;
                                    if (item.getItemID() == 6 || item.getItemID() == 7 || item.getItemID() == 8 || item.getItemID() == 9 || item.getItemID() == 10) {
                                        score = score + 1;
                                        player.setCurrentScore(score);


                                        dbItem.setIsActive(false);
                                        item.setPort(-1);

                                        if(player.getCurrentScore() == 3)
                                        {
                                            Intent ibseg = new Intent(FonaS.this, EnhorabonaS.class);
                                            startActivity(ibseg);
                                            //crono.stop();
                                        }
                                    }
                                    break;

                                case 3:
                                    if (dbItem.getIsActive())
                                        break;
                                    if (item.getItemID() == 1 || item.getItemID() == 2 || item.getItemID() == 3 || item.getItemID() == 4 || item.getItemID() == 5){
                                        dbItem.setIsActive(true);
                                        item.setPort(-1);
                                    }
                                    break;

                                case 4:
                                    if (dbItem.getIsActive())
                                        break;
                                    if (item.getItemID() == 6 || item.getItemID() == 7 || item.getItemID() == 8 || item.getItemID() == 9 || item.getItemID() == 10){
                                        dbItem.setIsActive(true);
                                        item.setPort(-1);
                                    }
                                    break;

                            }
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    scoreTxt.setText(String.valueOf(player.getCurrentScore()));
                                    //ArrayAdapter adapter = new ArrayAdapter(FonaS.this, android.R.layout.simple_list_item_1, items);
                                   // prova.setAdapter(adapter);
                                }
                            });
                            myAppDatabase.myDao().updatePlayer(player);
                            myAppDatabase.myDao().updateItem(dbItem);
                        }

                    }
                    bul=false;
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 5000);
    }

}

