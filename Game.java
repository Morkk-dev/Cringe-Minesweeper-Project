package com.company;
import java.util.Random;
import java.util.Scanner;
public class Game {

//  creates 2D array using cell class, this will be the board
    public static Cell[][] createBoard(int size){
        Cell[][] board = new Cell[size][size];
        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                board[row][column] = new Cell();
            }
        }
        return board;
    }

//  randomly places and scans mines on the board
    public static void randomizeMines(Cell[][] board, int mineTotal){
        Random rd = new Random();
        for (int mineCount = 0; mineCount < mineTotal; mineCount++){
            int row = rd.nextInt(board.length);
            int column = rd.nextInt(board.length);
            if (board[row][column].isMine){
                mineCount--;
            }
            else {
                board[row][column].setMine();
                for (int dx = -1; dx <= 1; dx++){
                    for (int dy = -1; dy <= 1; dy++){
                        if (!(dx == 0 && dy == 0)){
                         try {
                             board[row + dx][column + dy].incrementMineCount();
                         }
                         catch(Exception e){}
                        }
                    }
                }
            }
        }
    }

//  renders the board and coordinates
    public static void renderBoard(Cell[][] board){
        System.out.print("  ");
        for (int i = 65; i <= 64 + board.length; i++){
            System.out.print((char)i + " ");
        }
        System.out.println();
        for (int row = 0; row < board.length; row++){
            System.out.print((char)(row + 65) + " ");
            for (int column = 0; column < board.length; column++){
                if (!board[row][column].isShown){
                    System.out.print("█ ");
                }
                else if (board[row][column].isMine){
                    System.out.print("X ");
                }
                else {
                    System.out.print(board[row][column].mineCount + " ");
                }

            }
            System.out.println();
        }
    }

//    public static void playerInput(){
//
//    }


//  runs the game
    public static void main(String[] args) {

//      creates and instance of the scanner and creates a 2D array called board
        Scanner scan = new Scanner(System.in);
        Cell[][] board;

//      displays the menu
        System.out.print("Welcome to Minesweeper");
//        Thread.sleep(3000);
        System.out.println("\r" + "Please select a difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

//      menu select
        switch (scan.next()) {
            case "1":
                System.out.println("Easy" + "\n" + "\n");
                board = createBoard(9);
                randomizeMines(board, 10);
                renderBoard(board);
                break;
            case "2":
                System.out.print("Medium" + "\n" + "\n");
                board = createBoard(16);
                randomizeMines(board, 40);
                renderBoard(board);
                break;
            case "3":
                System.out.print("Hard" + "\n" + "\n");
                board = createBoard(24);
                randomizeMines(board, 99);
                renderBoard(board);
                break;
            case "sticky":
                System.out.print("sticky" + "\n" + "\n");
                board = createBoard(24);
                randomizeMines(board, 575);
                renderBoard(board);
                break;
            default:
                System.out.print("Invalid number, please select a difficulty:");
                break;
        }



//        Cell[][] board = createBoard(9);
//        randomizeMines(board, 10);
//        renderBoard(board);


    }

}
