import com.tictactoe.tictactoegame.TicTacToeGame;
import com.tictactoe.tictactoegame.Player;

public class Main {
    public static void main(String[] args) {

        System.out.println ("Joueur 1, présentez-vous");
        var player1 = new Player('X');

        System.out.println ("Joueur 2, présentez-vous");
        var player2= new Player('O');

        Player player;

        var game = new TicTacToeGame();
        System.out.println(game);


        boolean endOfGame=false;
        int nbRound=0;

        while (!endOfGame){
            if (nbRound%2==0) {
                player=player1;
            }else {
               player=player2;
            }
            System.out.println(player.getName()+", à vous de jouer! Que les jeux soient avec vous!");
            endOfGame=game.oneRound(player);
            nbRound++;
        }

    }
}