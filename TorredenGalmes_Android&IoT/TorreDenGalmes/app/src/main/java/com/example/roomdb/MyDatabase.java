package com.example.roomdb;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;

@Database(entities = {Player.class, Game.class, Item.class}, version = 2, exportSchema = false)

public abstract class MyDatabase extends RoomDatabase
{
    public abstract DAO myDao();
}
