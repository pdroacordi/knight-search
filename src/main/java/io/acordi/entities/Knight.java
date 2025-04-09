package io.acordi.entities;

public class Knight {

    private int[][] moves = {
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };

    public int[][] getMoves() {
        return moves;
    }
}
