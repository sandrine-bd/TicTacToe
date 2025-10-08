package fr.campus.tictactoe;

public class Main {
    public static void main(String[] args) {
        UserInteraction iu = new UserInteraction();
        int size = iu.setUpBoardSize();
        int mode = iu.setUpGameMode();
        Player[] players = iu.setUpPlayers(mode);

        TicTacToe ticTacToe = new TicTacToe(size, players[0], players[1]);
        ticTacToe.play();
    }
}