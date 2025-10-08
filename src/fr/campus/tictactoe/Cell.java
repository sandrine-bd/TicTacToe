package fr.campus.tictactoe;

public class Cell {
    private String state;

    public Cell() {
        this.state = "   "; // cellule vide
    }

    public String getRepresentation() {
        return state;
    }

    @Override
    public String toString() {
        return this.getRepresentation();
    }

    public boolean isEmpty() {
        return state.equals("   ");
    }

    public void setState(String state) {
        this.state = state;
    }
}