package fr.campus.tictactoe;

import java.util.Scanner;

public class UserInteraction {
    private final static Scanner sc = new Scanner(System.in);

    public int setUpBoardSize() {
        System.out.println("Entrez la taille du plateau (ex : 3 pour 3x3) : ");
        int size = 0;
        while (size < 3) {
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : entrez un nombre entier (minimum 3) !");
                sc.next();
                continue;
            }
            size = sc.nextInt();
            if (size < 3) {
                System.out.println("Taille trop petite : le plateau doit être d'au moins 3x3.");
            }
        }
        return size;
    }

    public int setUpGameMode() {
        System.out.println("----CHOIX DE JEU ----");
        System.out.println("1. Deux joueurs humains");
        System.out.println("2. Un humain et un joueur artificiel");
        System.out.println("3. Deux joueurs artificiels");
        System.out.print("Votre choix : ");

        int choice = 0;
        while (choice < 1 || choice > 3) {
            if (!sc.hasNextInt()){
                System.out.println("Erreur : vous devez entrer un nombre entre 1 et 3 !");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Erreur : le choix doit être 1, 2 ou 3 !");
            }
        }
        return choice;
    }

    public Player[] setUpPlayers(int mode) {
        Player player1;
        Player player2;

        switch (mode) {
            case 1 -> {
                player1 = new HumanPlayer(" X ");
                player2 = new HumanPlayer(" O ");
            }
            case 2 -> {
                player1 = new HumanPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            case 3 -> {
                player1 = new ArtificialPlayer(" X ");
                player2 = new ArtificialPlayer(" O ");
            }
            default -> {
                System.out.println("Choix invalide, par défaut : deux joueurs humains.");
                player1 = new HumanPlayer(" X ");
                player2 = new HumanPlayer(" O ");
            }
        }
        return new Player[]{player1, player2};
    }

    public int[] askCellChoice(int size, Cell[][] board) {
        int row, col;
        while (true) {
            System.out.print("Entrez le numéro de ligne (0, 1 ou 2) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : entrez un nombre !");
                sc.next();
                continue;
            }
            row = sc.nextInt();

            System.out.print("Entrez le numéro de colonne (0, 1 ou 2) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : entrez un nombre !");
                sc.next();
                continue;
            }
            col = sc.nextInt();

            if (row < 0 || col < 0 || row >= size || col >= size) {
                System.out.println("Erreur : coordonnées hors du plateau !");
                continue;
            }
            return new int[] {row, col};
        }
    }
}
