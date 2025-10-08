package fr.campus.tictactoe.game;

import fr.campus.tictactoe.*;
import fr.campus.tictactoe.player.HumanPlayer;
import fr.campus.tictactoe.player.Player;

import java.util.Random;

public abstract class Game {
    private int size;
    private Cell[][] board; // tableau de cellules
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private UserInteraction iu = new UserInteraction();
    private View view = new View();

    public Game(int size, Player player1, Player player2) { // constructeur
        this.size = size;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Cell[size][size];
        initializeBoard();
        view.displayBoard(size, board);
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
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

    public int[] getMoveFromPlayer(Player player) {
        if (player instanceof HumanPlayer) {
            return iu.askCellChoice(size, board);
        }
        // sinon : joueur artificiel
        Random rand = new Random();
        while (true) {
            int row = rand.nextInt(size); // entre 0 (inclus) et size (exclu)
            int col = rand.nextInt(size);
            if (board[row][col].isEmpty()) {
                System.out.println("L'IA joue en position " + row + " (ligne), " + col + (" (colonne)"));
                return new int[]{row, col};
            }
        }
    }

    public void play() {
        while (true) {
            System.out.println("\nTOUR DU JOUEUR " + currentPlayer.getRepresentation());
            int[] move = getMoveFromPlayer(currentPlayer);
            setOwner(move[0], move[1], currentPlayer);
            view.displayBoard(size, board);
            if (isBoardFull()) {
                System.out.println("Match nul ! Le plateau est plein.");
                break;
            }
            if (isWinner(currentPlayer)) {
                System.out.println("FIN DU JEU ! Le joueur " + currentPlayer.getRepresentation() + " a gagné !");
                break;
            };
            currentPlayer = (currentPlayer == player1) ? player2 : player1; // opérateur ternaire pour alterner
        }
    }
}
