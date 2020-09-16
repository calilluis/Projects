package com.example.roomdb;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogS extends AppCompatActivity {
    private EditText UserName, UserTag;
    public static MyDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "myDao").allowMainThreadQueries().build();

        for(int i = 0; i < 10; i++)
        {
            Item item = new Item();
            item.setItemID(i);
            item.setType("Projectile");
            item.setIsActive(true);

            if(myAppDatabase.myDao().getItems().size() == i)
            {
                myAppDatabase.myDao().addItem(item);
            }
            else if(myAppDatabase.myDao().getItems().size() == 10)
            {
                myAppDatabase.myDao().updateItem(item);
            }

            //item = null;
        }

        Game game = new Game();
        game.setName("Slingers Shooting Range");
        game.setDescription("Antigament per anar a caçar utilitzàvem una fona, també l'utilitzàvem com a arma. És una eina consistent en una corda o pell que permet projectar objectes a llarga distància. Ara és el teu torn de provar-la.");
        game.setGameID(0);
        game.setLocation("Wall");
        game.setWinCondition(3);
        game.setInitialItem(myAppDatabase.myDao().getItems().get(0).getItemID());
        game.setFinalItem(myAppDatabase.myDao().getItems().get(9).getItemID());

        if(myAppDatabase.myDao().getGames().size() == 0)
            myAppDatabase.myDao().addGame(game);
        else
            myAppDatabase.myDao().updateGame(game);

        Button btnlogin;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_s);

        btnlogin  = (Button)findViewById(R.id.buttonlogin);

        UserName = findViewById(R.id.userName);
        UserTag = findViewById(R.id.userTag);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int userId = 0;
                String userName= UserName.getText().toString();
                int userTag = Integer.parseInt(UserTag.getText().toString());

                Player player =new Player();
                player.setId(userId);
                player.setName(userName);
                player.setTag(userTag);
                player.setCurrentScore(0);

                if(LogS.myAppDatabase.myDao().getPlayers().size() == 0)
                    LogS.myAppDatabase.myDao().addPlayer(player);
                else
                    LogS.myAppDatabase.myDao().updatePlayer(player);

                Intent iblogin = new Intent(LogS.this, WelcomeS.class);
                startActivity(iblogin);
            }
        });
    }


}
