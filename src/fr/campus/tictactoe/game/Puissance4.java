package fr.campus.tictactoe.game;

import fr.campus.tictactoe.player.Player;

public class Puissance4 extends Game {
    public Puissance4(int[] size, Player player1, Player player2) {
        super(size, player1, player2);
    }

    @Override
    public void setOwner(int row, int col, Player player) {
        // on ignore row
        for (int r = size[0] - 1 ; r >= 0; r--) { // on part dans la colonne du bas vers le haut
            if (board[r][col].isEmpty()) { // cherche la 1ère case vide
                board[r][col].setState(player.getRepresentation()); // on pose le pion
                return;
            }
        }
        System.out.println("Colonne " + col + "pleine ! Choisissez-en une autre."); // si aucune case vide
    }

    @Override
    public boolean isWinner(Player player) {
        String symbol = player.getRepresentation();
        int needed = 4; // nombre de symboles pour gagner

        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                // Si la case correspond au symbole du joueur, on vérifie dans les 4 directions
                if (board[i][j].getRepresentation().equals(symbol)) {

                    // Vérifie à droite + non débordement du tableau (sinon erreur ArrayIndexOutOfBoundsException)
                    if (j + needed <= size[1] && checkDirection(i, j, 0, 1, symbol, needed))
                        return true;

                    // Vérifie en bas
                    if (i + needed <= size[0] && checkDirection(i, j, 1, 0, symbol, needed))
                        return true;

                    // Vérifie diagonale droite
                    if (i + needed <= size[0] && j + needed <= size[1] && checkDirection(i, j, 1, 1, symbol, needed))
                        return true;

                    // Vérifie diagonale gauche
                    if (i + needed <= size[0] && j - needed + 1 >= 0 && checkDirection(i, j, 1, -1, symbol, needed))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int startRow, int startCol, int deltaRow, int deltaCol, String symbol, int needed) {
        for (int k = 0; k < needed; k++) {
            int row = startRow + k * deltaRow;
            int col = startCol + k * deltaCol;
            if (!board[row][col].getRepresentation().equals(symbol)) {
                return false; // une case différente annule la victoire
            }
        }
        return true;
    }
}