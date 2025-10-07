package fr.campus.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private final int size; // taille du plateau
    private Cell[][] board; // tableau de cellules
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner sc;

    public TicTacToe(int size, Player player1, Player player2) { // constructeur
        this.size = size;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Cell[size][size];
        initializeBoard();
        this.sc = new Scanner(System.in);
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        System.out.println("-------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------------");
        }
    }

    public int[] getMoveFromPlayer(Player player) {
        while (true) {
            System.out.print("Entrez le numéro de la ligne : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : vous devez entrer un nombre !");
                sc.next(); // vide la mauvaise entrée
                continue;
            }
            int row = sc.nextInt();

            System.out.print("Entrez le numéro de la colonne : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : vous devez entrer un nombre !");
                sc.next();
                continue;
            }
            int col = sc.nextInt();

            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Erreur : coordonnées hors du plateau !");
                continue;
            }

            if (!board[row][col].isEmpty()) {
                System.out.println("Erreur : cette case est déjà prise !");
                continue;
            }
            return new int[]{row, col};
        }
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setState(player.getRepresentation());
    }

    public boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    return false; // s'il y a une case vide = plateau non plein
                }
            }
        } return true; // aucune case vidé trouvée = plateau plein
    }

    public boolean isWinner(Player player) {

        // lignes
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].getRepresentation().equals(player.getRepresentation()) &&
                    board[i][1].getRepresentation().equals(player.getRepresentation()) &&
                    board[i][2].getRepresentation().equals(player.getRepresentation())) {
                return true;
            }
        }

        // colonnes
        for (int j = 0; j < board.length; j++) {
            if (board[0][j].getRepresentation().equals(player.getRepresentation()) &&
                    board[1][j].getRepresentation().equals(player.getRepresentation()) &&
                    board[2][j].getRepresentation().equals(player.getRepresentation())) {
                return true;
            }
        }

        // diagonales
        if (board[0][0].getRepresentation().equals(player.getRepresentation()) &&
                board[1][1].getRepresentation().equals(player.getRepresentation()) &&
                board[2][2].getRepresentation().equals(player.getRepresentation())) {
            return true;
        }

        if (board[0][2].getRepresentation().equals(player.getRepresentation()) &&
                board[1][1].getRepresentation().equals(player.getRepresentation()) &&
                board[2][0].getRepresentation().equals(player.getRepresentation())) {
            return true;
        }

        return false;
    }

    public void play() {
        while (true) {
            display();

            System.out.println("\nTOUR DU JOUEUR " + currentPlayer.getRepresentation());

            int[] move = getMoveFromPlayer(currentPlayer);
            setOwner(move[0], move[1], currentPlayer);

            display();

            if (isBoardFull()) {
                System.out.println("Match nul ! Le plateau est plein.");
                break;
            }

            if (isWinner(currentPlayer)) {
                System.out.println("BRAVO ! Le joueur " + currentPlayer.getRepresentation() + " a gagné !");
                break;
            };

            currentPlayer = (currentPlayer == player1) ? player2 : player1; // opérateur ternaire pour alterner
        }
    }
}
