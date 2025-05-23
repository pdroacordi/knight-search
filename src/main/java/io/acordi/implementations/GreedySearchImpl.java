package io.acordi.implementations;

import io.acordi.entities.Knight;
import io.acordi.entities.MoveOption;
import io.acordi.entities.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedySearchImpl {

    private static int BOARD_SIZE = 4;
    private static long FULL_MASK;

    public static void setBoardSize(int size) {
        BOARD_SIZE = size;
        FULL_MASK = (1L << (BOARD_SIZE * BOARD_SIZE)) - 1;
    }

    public static List<Position> greedySearch(int originX, int originY) {
        List<Position> path = new ArrayList<>();
        path.add(new Position(originX, originY));

        int startPositionBit = originX * BOARD_SIZE + originY;
        long visitedMask = 1L << startPositionBit;

        Position currentPosition = new Position(originX, originY);

        Knight knight = new Knight();

        while (visitedMask != FULL_MASK) {
            Position nextMove = findNextMoveWarnsdorff(currentPosition, visitedMask, knight);

            if (nextMove == null) {
                break;
            }

            path.add(nextMove);

            int positionBit = nextMove.getX() * BOARD_SIZE + nextMove.getY();
            visitedMask |= (1L << positionBit);

            currentPosition = nextMove;
        }

        return path;
    }

    private static Position findNextMoveWarnsdorff(Position current, long visitedMask, Knight knight) {
        List<MoveOption> moveOptions = new ArrayList<>();

        for (int[] move : knight.getMoves()) {
            int newX = current.getX() + move[0];
            int newY = current.getY() + move[1];

            if (!isValidPosition(newX, newY)) continue;

            int positionBit = newX * BOARD_SIZE + newY;
            if ((visitedMask & (1L << positionBit)) != 0) continue;

            int accessibilityScore = countAccessibleMoves(newX, newY, visitedMask, knight);

            moveOptions.add(new MoveOption(new Position(newX, newY), accessibilityScore));
        }

        if (moveOptions.isEmpty()) {
            return null;
        }

        Collections.sort(moveOptions);

        return moveOptions.get(0).position;
    }

    private static int countAccessibleMoves(int x, int y, long visitedMask, Knight knight) {
        int count = 0;

        for (int[] move : knight.getMoves()) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (!isValidPosition(newX, newY)) continue;

            int positionBit = newX * BOARD_SIZE + newY;
            if ((visitedMask & (1L << positionBit)) == 0) {
                count++;
            }
        }

        return count;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

}