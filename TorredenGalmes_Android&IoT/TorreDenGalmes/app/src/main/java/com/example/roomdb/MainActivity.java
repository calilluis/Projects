/*package com.example.roomdb;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static MyDatabase myAppDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        myAppDatabase= Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "userdb").allowMainThreadQueries().build();
        if(findViewById(R.id.fragment_container) != null)
        {
            if(savedInstanceState != null)
                return;

            fragmentManager.beginTransaction().add(R.id.fragment_container, new LogS()).commit();
        }
    }
}
*/