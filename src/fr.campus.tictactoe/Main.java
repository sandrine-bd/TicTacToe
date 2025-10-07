package fr.campus.tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player[] players = setUpPlayers();
        TicTacToe ticTacToe = new TicTacToe(3, players[0], players[1]);
        ticTacToe.play();
    }

    public static Player[] setUpPlayers() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----CHOIX DE JEU ----");
        System.out.println("1. Deux joueurs humains");
        System.out.println("2. Un humain et un joueur artificiel");
        System.out.println("3. Deux joueurs artificiels");
        System.out.print("Votre choix : ");
        String choice = sc.next().trim();

        Player player1;
        Player player2;

        switch (choice) {
            case "1" -> {
                player1 = new HumanPlayer(" X ");
                player2 = new HumanPlayer(" O ");
            }
            case "2" -> {
                player1 = new HumanPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            case "3" -> {
                player1 = new ArtificialPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            default -> {
                System.out.println("Choix invalide, par défaut : deux joueurs humains.");
                player1 = new HumanPlayer(" X ");
                player2 = new HumanPlayer(" O ");
            }
        }
        return new Player[]{player1,player2};
    }
}