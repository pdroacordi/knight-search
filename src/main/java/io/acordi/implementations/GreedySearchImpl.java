package implementations;

import entities.Knight;
import entities.Position;
import entities.MoveOption;

import java.util.*;

public class GreedySearchImpl {

    private static int BOARD_SIZE = 4;
    private static long FULL_MASK;
    private static int exploredNodes = 0;

    public static void setBoardSize(int size) {
        BOARD_SIZE = size;
        FULL_MASK = (1L << (BOARD_SIZE * BOARD_SIZE)) - 1;
    }

    public static void resetExploredNodes() {
        exploredNodes = 0;
    }

    public static int getExploredNodes() {
        return exploredNodes;
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
            exploredNodes++;

            if (nextMove == null) {
                System.out.println("[Greedy] No more moves available after " + path.size() + " positions visited");
                break;
            }

            path.add(nextMove);

            int positionBit = nextMove.getX() * BOARD_SIZE + nextMove.getY();
            visitedMask |= (1L << positionBit);

            currentPosition = nextMove;
        }

        System.out.println("[Greedy] Search explored " + exploredNodes + " moves");
        System.out.println("[Greedy] Path length: " + path.size() + " out of " + (BOARD_SIZE * BOARD_SIZE) + " squares");

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

        return moveOptions.getFirst().position;
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