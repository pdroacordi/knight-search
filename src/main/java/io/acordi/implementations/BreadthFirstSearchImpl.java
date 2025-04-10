package io.acordi.implementations;

import io.acordi.entities.State;
import io.acordi.entities.Knight;
import io.acordi.entities.LongHashSet;
import io.acordi.entities.Position;

import java.util.*;

public class BreadthFirstSearchImpl {

    private static int BOARD_SIZE = 4;
    private static long FULL_MASK;

    public static void setBoardSize(int size) {
        BOARD_SIZE = size;
        FULL_MASK = (1L << (BOARD_SIZE * BOARD_SIZE)) - 1;
    }

    public static List<Position> breadthFirstSearch(int originX, int originY) {
        Queue<State> queue = new ArrayDeque<>();
        LongHashSet visitedStates = new LongHashSet(1 << 16);

        int startPos = originX * BOARD_SIZE + originY;
        long initialMask = 1L << startPos;
        State initialState = new State(startPos, initialMask, null);
        queue.add(initialState);
        visitedStates.add(getStateHash(initialState));

        Knight knight = new Knight();

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.visitedMask == FULL_MASK) {
                return reconstructPath(current);
            }

            int currentX = current.pos / BOARD_SIZE;
            int currentY = current.pos % BOARD_SIZE;

            for (int[] move : knight.getMoves()) {
                int newX = currentX + move[0];
                int newY = currentY + move[1];

                if (!isValidPosition(newX, newY))
                    continue;

                int newPos = newX * BOARD_SIZE + newY;
                long bitMask = 1L << newPos;

                if ((current.visitedMask & bitMask) != 0)
                    continue;

                long newMask = current.visitedMask | bitMask;
                State newState = new State(newPos, newMask, current);
                long stateHash = getStateHash(newState);

                if (visitedStates.contains(stateHash))
                    continue;

                visitedStates.add(stateHash);
                queue.add(newState);
            }
        }

        return null;

    }

    private static long getStateHash(State state) {
        return (((long) state.pos) << (BOARD_SIZE * BOARD_SIZE)) | state.visitedMask;
    }

    private static List<Position> reconstructPath(State state) {
        List<Position> path = new ArrayList<>();

        while (state != null) {
            int x = state.pos / BOARD_SIZE;
            int y = state.pos % BOARD_SIZE;
            path.add(new Position(x, y));
            state = state.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static boolean isValidPosition(int x, int y){
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }


}
