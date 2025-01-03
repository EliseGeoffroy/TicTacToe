import com.tictactoe.tictactoegame.TicTacToeGame;

public class Main {
    public static void main(String[] args) {

        var game = new TicTacToeGame();
        System.out.println(game);

        boolean endOfGame=false;
        int nbRound=0;
        char symbol;

        while (!endOfGame){
            if (nbRound%2==0) {
                System.out.println("Joueur 1, à vous de jouer! Que les jeux soient avec vous!");
                symbol='X';

            }else {
                System.out.println("Joueur 2, à vous de jouer! Que les jeux soient avec vous!");
                symbol='O';
            }
            endOfGame=game.oneRound(symbol);
            nbRound++;
        }

    }
}