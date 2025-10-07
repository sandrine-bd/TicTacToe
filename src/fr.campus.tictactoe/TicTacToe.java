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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        System.out.println("-------------------");
        for (int i = 0; i < size; i++) {
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
        String symbol = player.getRepresentation();

        // lignes
        for (int i = 0; i < size; i++) {
            boolean rowWin = true;
            for (int j = 0; j < size; j++) {
                if (!board[i][j].getRepresentation().equals(symbol)) {
                    rowWin = false;
                    break;
                }
            } if (rowWin) return true;
        }

        // colonnes
        for (int j = 0; j < size; j++) {
            boolean colWin = true;
            for (int i = 0; i < size; i++) {
                if (!board[i][j].getRepresentation().equals(symbol)) {
                   colWin = false;
                   break;
                }
            } if (colWin) return true;
        }

        // diagonale principale (haut-gauche → bas-droit)
        boolean diag1Win = true;
        for (int i = 0; i < size; i++) {
            if (!board[i][i].getRepresentation().equals(symbol)) {
                diag1Win = false;
                break;
            }
        } if (diag1Win) return true;

        // diagonale secondaire (haut-droit → bas-gauche)
        boolean diag2Win = true;
        for (int i = 0; i < size; i++) {
            if (!board[i][size - 1 - i].getRepresentation().equals(symbol)) {
                diag2Win = false;
                break;
            }
        } if (diag2Win) return true;

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
