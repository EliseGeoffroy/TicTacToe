package com.tictactoe.tictactoegame;

import java.util.Scanner;

public class Player {
    protected String name;
    protected char symbol;

    public Player(char symbol) {
        System.out.println("Choisissez votre pseudo");
        var scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }
}
