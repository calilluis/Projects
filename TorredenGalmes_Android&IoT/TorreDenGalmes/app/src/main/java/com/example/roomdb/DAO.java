package com.example.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAO {

    //Players
    @Insert
    public void addPlayer(Player player);

    @Query("select * from players")
    public List<Player> getPlayers();

    @Delete
    public void deletePlayer(Player player);

    @Update
    public void updatePlayer(Player player);

    //Games
    @Insert
    public void addGame(Game game);

    @Query("select * from games")
    public List<Game> getGames();

    @Delete
    public void deleteGame(Game game);

    @Update
    public void updateGame(Game game);

    //Items
    @Insert
    public void addItem(Item item);

    @Query("select * from items")
    public List<Item> getItems();

    @Delete
    public void deleteItem(Item item);

    @Update
    public void updateItem(Item item);
}