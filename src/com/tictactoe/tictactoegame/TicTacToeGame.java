package com.tictactoe.tictactoegame;

import com.tictactoe.exceptions.TictactoeMismatchInputException;
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

    /**
     * Plays one round for current player (cell choice and symbol placement at the right place
     * + checks if game is over
     * @param player Object with attributes name and symbol
     * @return boolean at true if game is over (exaequo or winner)
     */
    public boolean oneRound(Player player){
        cellChoice();
        cellPlacement (player.symbol);
        System.out.println(this);
        return endOfGame(player);
    }

    /**
     * asks the player the number of his chosen cell
     * checks if number OK (if it's an Integer, (if it exists and if this cell isn't already filled) thanks to the validCoor()method)
     * calculates line and column
     */
    public void cellChoice() {
        var coorIsValid= false;

        do {
            System.out.println("Choisissez une case (entre 1 et 9): ");
            var scanner = new Scanner(System.in);
            try {
                String entry=scanner.nextLine();
                if (entry.equals("exit")){
                    System.out.println("Vous quittez la partie.");
                    System.exit(0);
                } else {
                    coor = Integer.parseInt(entry);

                }
                line =lineCalcFunc(coor);
                col=colCalcFunc(coor);

                coorIsValid=validCoor();
            }
            catch (NumberFormatException e){
                System.out.println("Attention, ce n'est pas un entier...");
            }
            catch (TictactoeMismatchInputException t){
                System.out.println(t.getMessage());
            }

        }while (!coorIsValid);

    }

    /**
     * checks if cells exists and isn't already filled
     * @return true if no problem
     * @throws TictactoeMismatchInputException (if celle doesn't exist or is filled)
     */
    public boolean validCoor() throws TictactoeMismatchInputException{
        if (coor<1||coor>9){
            throw new TictactoeMismatchInputException("Ceci n'est pas le numéro d'une case, vous ne pouvez pas la choisir.");
        }else {
            if (gameMap[line][col]=='X'||gameMap[line][col]=='O'){
                throw new TictactoeMismatchInputException("Cette case a déjà été choisie... Concentrez-vous un peu, que diable!");
            }
        }
        return true;
    }

    /**
     * places the right symbol in the chosen cell
     * @param symbol : current player's cell
     */
    public void cellPlacement(char symbol){
        gameMap[line][col]=symbol;
    }

    /**
     * checks if game is over by calling two methods victoryChecking() and exAequo();
     * @param player : Object with attributes symbol and name
     * @return true if game is over
     */
    public boolean endOfGame(Player player ) {
        if (victoryChecking(player.symbol)){
            System.out.println("Nous avons un winner! : "+ player.name+ " a gagné!");
        }
        return (victoryChecking(player.symbol)) || exAequo();
    }

    /**
     * Checks all the possible alignments
     * @param symbol : current player's symbol
     * @return true if there is at least one alignment OK (stops at the first found alignment)
     */
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

    /**
     * checks if it remains some empty cells
     * @return false as soon as it finds an empty cell, either true
     */
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
        return (int) Math.floor((double) (coor-1)/3);
   }

    public int colCalcFunc(int coor){
        return (coor-1)%3;
    }

    /**
     * checks diagonal alignments
     * @param whichDiagonal : specifies what diagonal is about
     * @param symbol : current player's symbol
     * @return total symbol number on the diagonal
     */
    public int countDiagonal(List<Integer> diagonalList, char symbol){
        List<Integer> diagonalList;
        int countSymbol=0;

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
