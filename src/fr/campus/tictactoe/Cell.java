package fr.campus.tictactoe;

public class Cell {
    private String state;

    public Cell() {
        this.state = "   "; // cellule vide
    }

    public boolean isEmpty() {
        return state.equals("   ");
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRepresentation() {
        return state;
    }
}