package com.example.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "games")
public class Game {

    @PrimaryKey
    private int gameID;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="location")
    private String location;

    @ColumnInfo(name="winCondition")
    private int winCondition;

    @ForeignKey(entity = Item.class, parentColumns = "itemID", childColumns = "initialItem", onDelete = CASCADE)
    @ColumnInfo (name = "initialItem")
    private int initialItem;

    @ForeignKey(entity = Item.class, parentColumns = "itemID", childColumns = "finalItem", onDelete = CASCADE)
    @ColumnInfo (name = "finalItem")
    private int finalItem;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }

    public int getInitialItem() {
        return initialItem;
    }

    public void setInitialItem(int item) {
        this.initialItem = item;
    }

    public int getFinalItem() {
        return finalItem;
    }

    public void setFinalItem(int item) {
        this.finalItem = item;
    }
}
