package fr.campus.tictactoe;

public class View {
    public void displayBoard(int size, Cell[][] board) {
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
}
