package fr.campus.tictactoe;

import java.util.Random;
import java.util.stream.IntStream;

public class ArtificialPlayer extends Player {

    public ArtificialPlayer(String representation) {
        super(representation);
    }

    @Override
    public String getRepresentation() {
        return super.getRepresentation();
    }

    private Random secureRandom;
    IntStream intStream = secureRandom.ints(1, 0, 3); // 1  chiffre entre 0 inclus et 3 exclus
}
