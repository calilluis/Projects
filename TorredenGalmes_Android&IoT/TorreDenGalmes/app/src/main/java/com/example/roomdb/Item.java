package com.example.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey
    private int itemID;
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    @ColumnInfo(name="isActive")
    private boolean isActive;
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @ColumnInfo(name="type")
    private String type;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @ColumnInfo(name="port")
    private int port;
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    @ColumnInfo(name="timestamp")
    private String timestamp;
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        String content= "Timestamp: " + timestamp+ " \n" + "EPC: " + itemID + " \n" + "Port: " + port;
        return content;
    }
}
