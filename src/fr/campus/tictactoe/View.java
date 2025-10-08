package fr.campus.tictactoe;

public class View {
    public void displayBoard(int[] size, Cell[][] board) {

        System.out.println("-".repeat(board[0].length*6));
        for (int i = 0; i < size[0]; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-".repeat(board[0].length*6));
        }
    }
}
