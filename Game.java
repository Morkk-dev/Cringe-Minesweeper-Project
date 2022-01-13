package com.company;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.*;
public class Game {

    //creates 2D array using cell class, this will be the board
    public static Cell[][] createBoard(int size){
        Cell[][] board = new Cell[size][size];
        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                board[row][column] = new Cell();
            }
        }
        return board;
    }

    //randomly places and scans mines on the board
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

    //renders the board and coordinates
    public static void renderBoard(Cell[][] board){
        System.out.print("  ");
        for (int i = 65; i <= 64 + board.length; i++){
            System.out.print((char)i + " ");
        }
        System.out.println();
        for (int row = 0; row < board.length; row++){
            System.out.print((char)(row + 65) + " ");
            for (int column = 0; column < board.length; column++){
                if (!board[row][column].isShown && !board[row][column].isFlagged){
                    System.out.print("█ ");
                }
                else if (board[row][column].isFlagged){
                    System.out.print("F ");
                }
                else if (board[row][column].isMine){
                    System.out.print("X ");
                }
                else{
                    System.out.print(board[row][column].mineCount + " ");
                }
            }
            System.out.println();
        }
    }

    //function takes player input and board and returns 3 int long array
    //the three ints represent command (quit (0), opening (1) or falgging (2) a mine, or invalid (3) ), X, and Y
    public static int[] playerInput(String input){
        
        char[] inputArray = input.toCharArray();
        int[] intInputArray = {0, 0, 0};
        int[] parsedInput = {0, 0, 0};

        for (int i = 0; i < inputArray.length; i++){
            intInputArray[i] = (int)inputArray[i];
        }

        if ((inputArray[0] == 'q') || (inputArray[0] == 'Q')){
            parsedInput[0] = 0;
        }
        else if ((inputArray[0] == 'o') || (inputArray[0] == 'O')){
            parsedInput[0] = 1;
        }
        else if ((inputArray[0] == 'f') || (inputArray[0] == 'F')){
            parsedInput[0] = 2;
        }
        else{
            parsedInput[0] = 3;
        }

        if ((parsedInput[2] = intInputArray[1] - 65) > 25){
            parsedInput[2] = intInputArray[1] - 97;
        }
        else{
            parsedInput[2] = intInputArray[1] - 65;
        }

        if ((parsedInput[1] = intInputArray[2] - 65) > 25){
            parsedInput[1] = intInputArray[2] - 97;
        }
        else{
            parsedInput[1] = intInputArray[2] - 65;
        }

        return parsedInput;
    }

    public static Cell[][] updateBoard(int[] parsedInput, Cell[][] board) {

        Cell[][] boardUpdate = board;

        int X = parsedInput[1];
        int Y = parsedInput[2];


        if (parsedInput[0] == 1) {

            boardUpdate[X][Y].openCell();

            if (boardUpdate[X][Y].mineCount == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        if (!(dx == 0 && dy == 0)) {
                            try {
                                if (!boardUpdate[X + dx][Y + dy].isShown) {
                                    boardUpdate = updateBoard(new int[]{1, X + dx, Y + dy}, boardUpdate);
                                }
                            }catch (Exception e) {}
                        }
                    }
                }
            }
        }
        else if (parsedInput[0] == 2) {
            boardUpdate[X][Y].flagCell();
        }

        return boardUpdate;
    }

    /*public static int checkWin(Cell[][] board){

        Cell[][] checkBoard = board;
        int[] winCondition = {0};

        for (int dx = -1; dx <= 1; dx++){
            for (int dy = -1; dy <= 1; dy++){
                if (!(dx == 0 && dy == 0)){
                    try {
                        if (checkBoard[dx][dy].openCell() && checkBoard[dx][dy].isMine)){
                            winCondition[0] = 0;
                        }
                    }
                    catch(Exception e){}
                }
            }
        }

        if (){

        }


    }*/

    //runs the game
    public static void main(String[] args) {

        //creates and instance of the scanner and creates a 2D array of cells called board
        Scanner scan = new Scanner(System.in);
        Cell[][] board = createBoard(0);
        boolean menu = true;

        //displays the menu
        System.out.print("Welcome to Minesweeper");
//        Thread.sleep(3000);
        System.out.println("\r" + "Please select a difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        //menu select
       while (menu){
            switch (scan.next()) {
                case "1":
                    System.out.println("Easy" + "\n");
                    board = createBoard(9);
                    randomizeMines(board, 10);
                    renderBoard(board);
                    menu = false;
                    break;
                case "2":
                    System.out.print("Medium" + "\n");
                    board = createBoard(16);
                    randomizeMines(board, 40);
                    renderBoard(board);
                    menu = false;
                    break;
                case "3":
                    System.out.print("Hard" + "\n");
                    board = createBoard(24);
                    randomizeMines(board, 99);
                    renderBoard(board);
                    menu = false;
                    break;
                case "sticky":
                    System.out.print("sticky" + "\n");
                    board = createBoard(24);
                    randomizeMines(board, 575);
                    renderBoard(board);
                    break;
                default:
                    System.out.print("Invalid number, please select a difficulty:");
                    break;
            }
       }

        System.out.println();
        System.out.println("To select a tile, type in your action then your X and Y, letter indicated, coordinates" + "\n");
        System.out.println("Your actions include:" + "\n" + "Opening a tile (o)" + "\n" + "Flagging a tile (f)" + "\n" + "Quiting (quit or q)");
        System.out.println("\n" + "To unflag a tile, please flag that same tile again" + "\n");

        boolean run = true;

        while (run){

            renderBoard(board);

            String input = scan.next();
            int[] parsedInput = playerInput(input);

            if (parsedInput[0] == 0) {
                System.out.println(" Thank you for playing");
                run = false;
            }

            Cell[][] boardUpdate = updateBoard(parsedInput, board);
            board = boardUpdate;

            //checkWin(board);



        }
    }
}
