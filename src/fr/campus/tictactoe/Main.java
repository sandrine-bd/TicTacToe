package fr.campus.tictactoe;

import fr.campus.tictactoe.game.TicTacToe;
import fr.campus.tictactoe.player.Player;

public class Main {
    public static void main(String[] args) {
        UserInteraction iu = new UserInteraction();
        // int size = iu.setUpBoardSize();
        int mode = iu.setUpGameMode();
        Player[] players = iu.setUpPlayers(mode);

        TicTacToe ticTacToe = new TicTacToe(3, players[0], players[1]);
        ticTacToe.play();
    }
}