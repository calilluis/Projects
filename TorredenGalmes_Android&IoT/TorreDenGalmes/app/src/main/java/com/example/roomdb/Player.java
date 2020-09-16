package com.example.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "players")
public class Player {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo (name="name")
    private String name;

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    @ColumnInfo (name="currentScore")
    private int currentScore;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    @ForeignKey(entity = Game.class, parentColumns = "gameID", childColumns = "id", onDelete = CASCADE)
    @ColumnInfo (name = "gameID")
    private int gameID;

    @ColumnInfo (name = "spot")
    private int spot;
    public int getSpot() {
        return spot;
    }
    public void setSpot(int spot) {
        this.spot = spot;
    }

    @ColumnInfo (name = "tag")
    private int tag;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}