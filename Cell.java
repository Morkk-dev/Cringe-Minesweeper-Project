package com.company;

public class Cell {

    public boolean isMine;
    public boolean isShown;
    public boolean isFlagged;
    public int mineCount;

    public Cell() {
        isMine = false;
        isShown = false;
        isFlagged = false;
        mineCount = 0;
    }

    public void setMine() {
        isMine = true;
    }

    public void incrementMineCount() {
        mineCount++;
    }
}