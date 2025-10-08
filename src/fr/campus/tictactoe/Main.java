package fr.campus.tictactoe;

import fr.campus.tictactoe.game.Game;
import fr.campus.tictactoe.game.Gomoku;
import fr.campus.tictactoe.game.Puissance4;
import fr.campus.tictactoe.game.TicTacToe;
import fr.campus.tictactoe.player.Player;

public class Main {
    public static void main(String[] args) {
        UserInteraction iu = new UserInteraction();
        int choice = iu.gameChoice();
        int mode = iu.setUpGameMode();
        Player[] players = iu.setUpPlayers(mode);
        if (choice == 1) {
            Game game = new TicTacToe(new int[]{3,3}, players[0], players[1]);
            game.play();
        }
        if (choice == 2){
            Game game = new Gomoku(new int[]{15,15}, players[0], players[1]);
            game.play();
        }
        if (choice == 3){
            Game game = new Puissance4(new int[]{6,7}, players[0], players[1]);
            game.play();
        }
    }
}