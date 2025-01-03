package com.tictactoe.tictactoegame;

import com.tictactoe.stringconstants.StringConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TicTacToeGame {

    private final char[][] gameMap={{'1','2','3'},{'4','5','6'},{'7','8','9'}};
    private int coor;
    private int line;
    private int col;
    private static final List<Integer> coorFirstDiagPossible= new ArrayList(){{
        add(1);
        add(5);
        add(9);
    }};
    private static final List<Integer> coorSecondDiagPossible= new ArrayList(){{
        add(3);
        add(5);
        add(7);
    }};


    public boolean oneRound(char symbol){
        cellChoice();
        cellPlacement (symbol);
        System.out.println(this);
        return endOfGame(symbol);
    }

    public void cellChoice() {
        var coorIsValid= false;

        do {
            System.out.println("Choisissez une case (entre 1 et 9): ");
            var scanner = new Scanner(System.in);
            try {
                coor = scanner.nextInt();
                line =lineCalcFunc(coor);
                col=colCalcFunc(coor);

                coorIsValid=validCoor();
            }
            catch(Exception e){
                System.out.println("Ce n'est pas un entier...");
            }

        }while (!coorIsValid);

    }

    public boolean validCoor() {
        if (coor<1||coor>9){
            System.out.println("Ceci n'est pas le numéro d'une case, vous ne pouvez pas la choisir.");
            return false;
        }else {
            if (gameMap[line][col]=='X'||gameMap[line][col]=='O'){
                System.out.println("Cette case a déjà été choisie... Concentrez-vous un peu, que diable!");
                return false;
            }
        }
        return true;
    }

    public void cellPlacement(char symbol){
        gameMap[line][col]=symbol;
    }
    public boolean endOfGame(char symbol) {
        if (victoryChecking(symbol)){
            System.out.println("Bravo! Vous avez gagné!");
        }
        return (victoryChecking(symbol)) || exAequo();
    }

    public boolean victoryChecking(char symbol){
        int countSymbol;
        //horizontal
        countSymbol=0;
        for (char charac : gameMap[line]){
            if (charac==symbol){
                countSymbol++;
            }
        }
        if (countSymbol==3){
            return true;
        }
        //vertical
        countSymbol=0;
        for (char [] line : gameMap){
            if (line[col]==symbol){
                countSymbol++;
            }
        }
        if (countSymbol==3){
            return true;
        }
        //first diagonal
        if (countDiagonal("first",symbol)==3){
            return true;
        }

        //second diagonal
        return(countDiagonal("second",symbol)==3);
    }

    public boolean exAequo(){
        for (char[] line : gameMap){
            for (char charac : line){
                if ((charac!='X')&&(charac!='O')){
                    return false;
                }
            }
        }
        System.out.println ("Unis dans la défaite! Personne n'a gagné, mais personne n'a perdu non plus ;)");
        return true;
    }

   public int lineCalcFunc(int coor){
       int lineCalc = (int) Math.floor((double) coor/3);

       //multiple of 3 case
       if (coor%3==0){
           lineCalc--;
       }

       return lineCalc;
   }

    public int colCalcFunc(int coor){

        int colCalc = (coor%3)-1;

        //multiple of 3 case
        if (coor%3==0){

            colCalc=2;
        }

        return colCalc;
    }

    public int countDiagonal(String whichDiagonal, char symbol){
        List<Integer> diagonalList;
        int countSymbol=0;

        if (whichDiagonal.equals("first")){
            diagonalList=coorFirstDiagPossible;
        }else {
            diagonalList=coorSecondDiagPossible;
        }

        if (diagonalList.contains(coor)) {
            for (int coorDiag : diagonalList){
                if (gameMap[lineCalcFunc(coorDiag)][colCalcFunc(coorDiag)]==symbol){
                    countSymbol++;
                }
            }
        }
        return countSymbol;
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        builder.append("Grille du Morpion : ").append(StringConstants.LINE_SEPARATOR);
        for (char[] line : gameMap) {
            for (char cell : line) {
                builder.append(StringConstants.SPACE).append(cell).append(StringConstants.SPACE);
            }
            builder.append(StringConstants.LINE_SEPARATOR);
        }
        return builder.toString();
    }

}
