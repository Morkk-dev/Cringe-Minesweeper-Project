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

    public boolean setMine() {
        isMine = true;
        return false;
    }

    public void incrementMineCount() {
        mineCount++;
    }

    public void flagCell(){
        isFlagged = !isFlagged;
    }

    public boolean openCell(){
        isShown = true;
        return false;
    }
}