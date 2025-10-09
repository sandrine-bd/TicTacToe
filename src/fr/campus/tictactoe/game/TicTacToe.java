package fr.campus.tictactoe.game;

import fr.campus.tictactoe.player.Player;

public class TicTacToe extends Game {
    public TicTacToe(int[] size, Player player1, Player player2) {
        super(size, player1, player2);
    }

    @Override
    public boolean isWinner(Player player) {
        String symbol = player.getRepresentation();

        // lignes
        for (int i = 0; i < size[0]; i++) {
            boolean rowWin = true;
            for (int j = 0; j < size[1]; j++) {
                if (!board[i][j].getRepresentation().equals(symbol)) {
                    rowWin = false;
                    break;
                }
            } if (rowWin) return true;
        }

        // colonnes
        for (int j = 0; j < size[1]; j++) {
            boolean colWin = true;
            for (int i = 0; i < size[0]; i++) {
                if (!board[i][j].getRepresentation().equals(symbol)) {
                    colWin = false;
                    break;
                }
            } if (colWin) return true;
        }

        // diagonale droite (haut-gauche → bas-droit)
        boolean diag1Win = true;
        for (int i = 0; i < size[0]; i++) {
            if (!board[i][i].getRepresentation().equals(symbol)) {
                diag1Win = false;
                break;
            }
        } if (diag1Win) return true;

        // diagonale gauche (haut-droit → bas-gauche)
        boolean diag2Win = true;
        for (int i = 0; i < size[0]; i++) {
            if (!board[i][size[0] - 1 - i].getRepresentation().equals(symbol)) {
                diag2Win = false;
                break;
            }
        } if (diag2Win) return true;

        return false;
    }
}
